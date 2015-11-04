package domain

import akka.actor.{ActorRef, Actor}
import scala.collection.{mutable, immutable}

case class GetQueuedMessages()
case class Messages(messages: immutable.Set[Message])

/**
  * Combination of user and room that represents a single instance of a user connected to a room.
  */
abstract class Connection(user: User, room: ActorRef) extends Actor {
  override def receive: Receive = {
    case Message(content, from) => messageReceived(content, from)
  }

  /**
    * Implement this method to receive messages
    * @param content contents of the message
    * @param from author of message
    */
  def messageReceived(content: String, from: User)
}

/**
  * An implemention of Connection that puts all received messages in a list and provides this list on demand.
  */
class PollingConnection(user: User, room: ActorRef) extends Connection(user, room) {
  private val messages = mutable.ArrayBuffer.empty[Message]
  override def messageReceived(content: String, from: User): Unit = {
    messages += Message(content, from)
  }

  override def receive: Receive = {
    super.receive orElse {
      case GetQueuedMessages() => sender() ! getQueuedMessages
    }
  }

  private def getQueuedMessages = {
    val queuedMessages = messages.toSet
    messages.clear()
    queuedMessages
  }

}
