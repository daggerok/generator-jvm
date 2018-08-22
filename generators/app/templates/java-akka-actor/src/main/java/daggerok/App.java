package daggerok;

import akka.actor.*;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import scala.Option;
import scala.PartialFunction;
import scala.concurrent.Future;
import scala.util.Try;

import java.util.concurrent.CountDownLatch;

import static lombok.AccessLevel.PRIVATE;

@Slf4j
public class App {

  @RequiredArgsConstructor(staticName = "with")
  public static class Init { public final int amount; }

  public enum Ping { INSTANCE }
  public enum Pong { INSTANCE }
  public enum Done { INSTANCE }

  public static class UntypedPingPongActor extends AbstractActor {

    private int timeout = 5;
    private CountDownLatch countDownLatch;

    @SneakyThrows
    private void init(final int amount) {
      if (amount > 0) timeout = amount;
      countDownLatch = new CountDownLatch(timeout);
      log.info("Choose {}! So let's get started!", timeout);
    }

    @SneakyThrows
    private void pingPong(final Object msg) {
      Thread.sleep(timeout);
      log.info("{} {}", msg.getClass().getSimpleName(), countDownLatch.getCount());
      if (countDownLatch.getCount() < 1) {
        //sender().tell(Done.INSTANCE, self());
        self().forward(Done.INSTANCE, context());
      }
      else {
        countDownLatch.countDown();
        //self().tell(msg, self());
        self().forward(msg, context());
      }
    }

    @Override public Receive createReceive() {
      return
          receiveBuilder()
              .match(Init.class, input -> {
                init(input.amount);
                pingPong(Ping.INSTANCE);
              })
              .matchEquals(Ping.INSTANCE, msg -> pingPong(Pong.INSTANCE))
              .matchEquals(Pong.INSTANCE, msg -> pingPong(Ping.INSTANCE))
              .matchEquals(Done.INSTANCE, msg -> {
                log.info("Done.");
                context().system().terminate();
              })
              .matchAny(o -> {
                log.warn("Unexpected.");
                context().system().terminate();
              })
              .build();
    }
  }

  @SneakyThrows
  public static void main(String[] args) {

    final Config config = ConfigFactory.load(App.class.getClassLoader(), "/application.conf");
    final ActorSystem actorSystem = ActorSystem.create("ping-pong-system", config);
    final ActorRef actorRef = actorSystem.actorOf(Props.create(UntypedPingPongActor.class), "ping-pong-actor");

    actorRef.tell(Init.with(30), actorRef);
  }
}
