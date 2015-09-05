import akka.actor.{Props, ActorSystem}
import akka.stream.actor.{ActorSubscriber, ActorPublisher}

object StreamApp extends App {

  start

  def start: ActorSystem = {
    val system = ActorSystem("system")

    val publisher = ActorPublisher[String](system.actorOf(Props(new PubActor)))
    val subscriber = ActorSubscriber[String](system.actorOf(Props(new SubActor)))

    publisher.subscribe(subscriber)

    system
  }

  def stop(system: ActorSystem) = {
    system.shutdown
  }
}
