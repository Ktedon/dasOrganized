package controllers

import play.api.libs.json._
import play.api.libs.functional.syntax._

case class Message(chatroomId: Int, message: String)
