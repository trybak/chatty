package domain

import akka.actor.{ActorRef, Actor}
import scala.collection.mutable

/**
  * Combination of user and room that represents a single instance of a user connected to a room.
  */
abstract class Connection(user: User, room: ActorRef) extends Actor {
  private val messages = mutable.Map[User, mutable.Set[Message]]()

  override def receive: Receive = {
    case Message(content, from) => messageReceived(content, from)
    case UserJoined(user: User) => userJoined(user)
  }

  def messageReceived(content: String, from: User)

  def userJoined(user: User)
}
