package controllers

import play.api.data._
import play.api.data.Forms._
import play.api.data.validation.Constraints._

case class ChatroomForm(json: String)

object ChatroomForm {
	val form: Form[ChatroomForm] = Form(
		mapping(
			  "json" -> text
		)(ChatroomForm.apply)(ChatroomForm.unapply)
	)
}
