import akka.actor.ActorRef

package object domain {

  /**
    * A user of the system
    */
  case class User(username: String)

  /**
    * Message sent when a user wants to join a room given the actor ref of a connection
    * @param user the user that is joining
    * @param connection the connection for this user that is bound for the room
    */
  case class Join(user: User, connection: ActorRef)

  /**
    * A message from a user
    * @param content content of the message
    * @param from the user that sent the message
    */
  case class Message(content: String, from: User)

  /**
    * Generic message to ask a supervisor to get or create a child by id
    * @param id id of the child to get or create
    */
  case class GetOrCreate(id: String)

  /**
    * Generic message to ask a supervisor to get an existing child by id
    * @param id if of the child to get
    */
  case class Get(id: String)

  /**
    * Generic message to ask a supervisor to create a child by id
    * @param id id of child to create
    */
  case class Create(id: String)

}
