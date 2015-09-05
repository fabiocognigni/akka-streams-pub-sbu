import akka.stream.actor.ActorPublisher
import akka.stream.actor.ActorPublisherMessage.Request

class PubActor extends ActorPublisher[String] {

  def receive = {
    case Request(numMsgs) => {
      println(s"[PubActor] - Request for ${numMsgs} received.")
      (1 to numMsgs.toInt) map { i =>
        if (isActive && totalDemand > 0) {
          onNext(s"Message ${i}")
        }
      }
    }
  }
}
