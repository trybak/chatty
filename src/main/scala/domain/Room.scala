package domain

import akka.actor.{ActorRef, Actor}

import scala.collection.mutable

/**
  * An chat room that supports many users.
  *
  * This chat room supports duplicate joins, but expects a unique combination of user and connection.
  */
class Room(val name: String) extends Actor {
  private val members = mutable.Set[(User, ActorRef)]()

  override def receive = {
    case Join(user, connection) => sender() ! join(user, connection)
    case Message(content, from) => message(content, from)
  }

  private def join(user: User, connection: ActorRef) = {
    members.add((user, connection))
    members.toSet
  }

  private def message(content: String, from: User) = {
    members.filter(_.ne(from)).foreach(_._2 ! Message(content, from) )
  }
}
