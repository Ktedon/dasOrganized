package controllers

import play.api.libs.json._
import play.api.libs.functional.syntax._

import model.Tables._

case class AddChatroom(name: String, members: Seq[Int])

object AddChatroom {

  implicit val chatroomWrites: Reads[AddChatroom] = (
    (JsPath \ "name").read[String] and
      (JsPath \ "members").read[Seq[Int]]
  )(AddChatroom.apply _)
}
