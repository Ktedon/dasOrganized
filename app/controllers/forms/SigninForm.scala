package controllers

import play.api.data._
import play.api.data.Forms._
import play.api.data.validation.Constraints._

case class SigninForm(email: String, pwd: String)

object SigninForm {
	val form: Form[SigninForm] = Form(
		mapping(
			  "email" -> nonEmptyText(1, 150)
      , "pwd" -> nonEmptyText(1, 150)
		)(SigninForm.apply)(SigninForm.unapply)
	)
}
