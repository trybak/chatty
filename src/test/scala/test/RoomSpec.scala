package test

import akka.actor.{ActorRef, Props}
import domain._

import scala.collection.immutable
import scala.concurrent.duration._
import scala.language.postfixOps

class TestConnection(user: User, room: ActorRef) extends Connection(user, room) {
  override def messageReceived(content: String, from: User): Unit = {}
}

class RoomSpec extends UnitSpec {

  override def afterAll: Unit = {
    system.terminate()
  }

  val room = system.actorOf(Props(classOf[Room], "room"))
  val user1 = User("user1")
  val connection1 = system.actorOf(Props(classOf[TestConnection], user1, room))
  val user2 = User("user2")
  val connection2 = system.actorOf(Props(classOf[TestConnection], user2, room))
  val connection3 = system.actorOf(Props(classOf[TestConnection], user2, room))

  "A user " should {
    "succesfully join a room" in {
      within(500 millis) {
        room ! Join(user1, connection1)
        expectMsg(immutable.Set((user1, connection1)))
      }
    }
  }

  "A 2nd user" should {
    "successfully join a room and see the first user" in {
      within(500 millis) {
        room ! Join(user2, connection2)
        expectMsg(immutable.Set((user1, connection1), (user2, connection2)))
      }
    }
  }

  "An existing user-connection combination joining a room" should {
    "be a no-op" in {
      within(500 millis) {
        room ! Join(user2, connection2)
        expectMsg(immutable.Set((user1, connection1), (user2, connection2)))
      }
    }
  }

  "A user" should {
    "be able to join with a 2nd connection" in {
      within(500 millis) {
        room ! Join(user2, connection3)
        expectMsg(immutable.Set((user1, connection1), (user2, connection2), (user2, connection3)))
      }
    }
  }

}

object RoomSpec {
  val config =
  """
  akka {
    logLevel = "WARNING"
  }
  """
}