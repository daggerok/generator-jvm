package daggerok

import akka.actor.{Actor, ActorSystem, Props}

case class MyMessage(body: String)

class MyActor extends Actor {
  override def receive: Receive = {
    case MyMessage(body) => println(s"received message: '$body'")
  }
}

object App {
  def main(args: Array[String]): Unit = {
    val system = ActorSystem("my-actor-system")
    val actor = system.actorOf(Props[MyActor], "my-actor")
    actor ! MyMessage("Hello, Akka!")
    Thread.sleep(1000)
    system.terminate()
  }
}
