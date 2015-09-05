import akka.stream.actor.{WatermarkRequestStrategy, ActorSubscriber}
import akka.stream.actor.ActorSubscriberMessage.{OnComplete, OnError, OnNext}

class SubActor extends ActorSubscriber {

  val requestStrategy = WatermarkRequestStrategy(5)

  def receive = {
    case OnNext(msg: String) =>
      println(s"[SubActor] - Received: ${msg}")
      Thread.sleep(1000)
    case OnError(err: Exception) =>
      println(s"[SubActor] - OnError!!")
      //context.stop(self)
    case OnComplete =>
      println(s"[SubActor] - OnComplete!!")
      //context.stop(self)
    case _ => println("[SubActor] - Unknown message received")
  }

}
