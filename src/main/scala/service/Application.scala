package service

import akka.actor.{ActorRef, ActorPath, Props, ActorSystem}
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{HttpResponse, HttpRequest}
import akka.stream.ActorMaterializer
import akka.http.scaladsl.server.Directives._
import akka.stream.scaladsl.Flow
import akka.util.Timeout
import scala.concurrent.duration._
import domain._
import akka.pattern._

import scala.concurrent.Future

object Application extends App {
  implicit val system = ActorSystem("WebLayer")
  implicit val materializer = ActorMaterializer()
  implicit val timeout = Timeout(5 seconds)
  import system.dispatcher

  val roomSupervisor = system.actorOf(Props(classOf[RoomSupervisor]))

  val route = {
    post {
      path("connection") {
        parameters('username, 'room) { (username, room) =>
          complete(createConnection(username, room))
        }
      }

    }
  }

  val binding = Http().bindAndHandle(route, "localhost", 8080)
  println("Listening at http://localhost:8080")

  def createConnection(username: String, room: String): Future[String] = {
    roomSupervisor ? GetOrCreate(room) map {
      case roomRef: ActorRef =>
        val roomPath = roomRef.path.name

        roomPath
        // TODO: create and return a connection that can later be queried for messages

    }
  }


}
