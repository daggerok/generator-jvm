package daggerok

import akka.actor.{Actor, ActorSystem, Props}

case class MyMessage(body: String)

class MyActor extends Actor {
  override def receive: Receive = {
    case MyMessage(body) => println(s"Hello, $body!")
  }
}

object Application extends App {

  val mySystem = ActorSystem("my-actor-system")
  val myActor = mySystem.actorOf(Props[MyActor], "my-actor")

  myActor ! MyMessage("Akka")
  Thread.sleep(1000)
  mySystem.terminate()
}
