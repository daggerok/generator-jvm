package daggerok

import akka.actor.{ActorRef, ActorSystem, Props}
import akka.persistence.PersistentActor
import com.typesafe.config.ConfigFactory

case object IncrementCommand

case object IncrementedEvent

class CounterActor extends PersistentActor {
  override def persistenceId: String = "counter"

  var state = 0

  var receiveCommand: Receive = {
    case IncrementCommand => persist(IncrementedEvent) { event =>
      println(s"received: $event (current state: $state)")
      state += 1
      println(s"$event state: $state")
    }
  }

  var receiveRecover: Receive = {
    case IncrementedEvent => {
      println(s"reconstructing a state (current state: $state)")
      state += 1
      println(s"state reconstructed: $state")
    }
  }
}

object App {
  def main(args: Array[String]) {
    val config = ConfigFactory.load()
    val system = ActorSystem("counter-system")
    val counter = system.actorOf(Props[CounterActor], "counter-actor")
    val amount = if (args.length > 0) args.length else 1

    for (_ <- 0 to amount) {
      counter ! IncrementCommand
    }

    Thread.sleep(1000)
    system.terminate()
  }
}
