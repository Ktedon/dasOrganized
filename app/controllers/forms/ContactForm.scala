package controllers

import play.api.data._
import play.api.data.Forms._
import play.api.data.validation.Constraints._

case class ContactForm(name: String, email: String, subject: String, message: String)

object ContactForm {
	val form: Form[ContactForm] = Form(
		mapping(
			  "name" -> nonEmptyText(1, 150)
			, "email" -> nonEmptyText(1, 150)
      , "subject" -> nonEmptyText(1, 400)
      , "message" -> text
		)(ContactForm.apply)(ContactForm.unapply)
	)
}
