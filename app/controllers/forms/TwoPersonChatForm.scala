package controllers

import play.api.data._
import play.api.data.Forms._
import play.api.data.validation.Constraints._

case class TwoPersonChatForm(toId: Int)

object TwoPersonChatForm {
	val form: Form[TwoPersonChatForm] = Form(
		mapping(
			  "toId" -> number
		)(TwoPersonChatForm.apply)(TwoPersonChatForm.unapply)
	)
}
