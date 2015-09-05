import akka.stream.actor.{ZeroRequestStrategy, OneByOneRequestStrategy, WatermarkRequestStrategy, ActorSubscriber}
import akka.stream.actor.ActorSubscriberMessage.{OnComplete, OnError, OnNext}

class SubActor extends ActorSubscriber {

  val delay = 10000
  //val requestStrategy = WatermarkRequestStrategy(5)
  val requestStrategy = OneByOneRequestStrategy
  //val requestStrategy = ZeroRequestStrategy

  def receive = {
    case OnNext(msg: String) =>
      println(s"[SubActor] - Received: ${msg}")
      Thread.sleep(delay)
      //request(50)  // when using ZeroRequestStrategy
    case OnError(err: Exception) =>
      println(s"[SubActor] - Error from publisher!!")
      context.stop(self)
    case OnComplete =>
      println(s"[SubActor] - Stream finished!!")
      //context.stop(self)
    case _ => println("[SubActor] - Unknown message received")
  }

}
