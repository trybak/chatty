import akka.actor.ActorRef

package object domain {
  case class Join(user: User, connection: ActorRef)
  case class Message(content: String, from: User)
  case class User(username: String)

  case class UserJoined(user: User)

}
