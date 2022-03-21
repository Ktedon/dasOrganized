package controllers

import play.api.data._
import play.api.data.Forms._
import play.api.data.validation.Constraints._

case class SignupForm(name: String, email: String, pwd: String)

object SignupForm {
	val form: Form[SignupForm] = Form(
		mapping(
			  "name" -> nonEmptyText(1, 150)
			, "email" -> nonEmptyText(1, 150)
      , "pwd" -> nonEmptyText(1, 150)
		)(SignupForm.apply)(SignupForm.unapply)
	)
}
