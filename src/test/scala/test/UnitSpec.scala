package test

import akka.actor.ActorSystem
import akka.testkit.{ImplicitSender, DefaultTimeout, TestKit}
import com.typesafe.config.ConfigFactory
import org.scalatest._

abstract class UnitSpec extends TestKit(ActorSystem("TestSystem", ConfigFactory.parseString(RoomSpec.config)))
with DefaultTimeout
with ImplicitSender
with WordSpecLike
with Matchers
with BeforeAndAfterAll