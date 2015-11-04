package domain

import akka.actor.{Props, ActorRef, Actor}
import scala.collection.mutable

/**
  * Supervisor for rooms.
  *
  * Ensures that there exists one and only one room for each name.
  */
class RoomSupervisor extends Actor {
  private val pathToRefMap = mutable.Map[String, ActorRef]()

  override def receive: Receive = {
    case GetOrCreate(roomName) => sender() ! getOrCreate(roomName)
  }

  def getOrCreate(roomName: String) = pathToRefMap.getOrElse(roomName, {
    val path = s"room_$roomName"
    val roomRef = context.actorOf(Props(classOf[Room], roomName), path)
    pathToRefMap.put(path, roomRef)
    roomRef
  })


}
