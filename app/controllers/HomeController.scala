package controllers

import javax.inject._
import play.api._
import play.api.mvc._

import play.api.data._
import play.api.data.Forms._
import play.api.data.validation.Constraints._

import play.api.db.slick.DatabaseConfigProvider
import scala.concurrent.ExecutionContext
import play.api.db.slick.HasDatabaseConfigProvider
import slick.jdbc.JdbcProfile
import slick.jdbc.PostgresProfile.api._

import scala.concurrent.Future

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
 @Singleton
 class HomeController @Inject() (
     protected val dbConfigProvider: DatabaseConfigProvider,
     controllerComponents: ControllerComponents
 )(implicit ec: ExecutionContext)
     extends AbstractController(controllerComponents)
     with HasDatabaseConfigProvider[JdbcProfile]
     with play.api.i18n.I18nSupport {

  private val contactModel = new models.ContactModel(db)(ec)
  private val memberModel = new models.MemberModel(db)(ec)

  def index() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.index())
  }

  def blog() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.blog())
  }

  def contact() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.contact())
  }

  def contactPost() = Action.async { implicit request: Request[AnyContent] =>
    ContactForm.form.bindFromRequest.fold(
      formWithErrors => {
        Future.successful(
          BadRequest(
            views.html.error("Message Failed to Send!", "Something wen't wrong when you submitted the form.")
          )
        )
      },
      formData => {
        contactModel.create(formData, 0).map {
          if (_)
            Ok("worked")
          else
            Ok(views.html.error("Message Failed to Send!", "Our backend failed to submit the form. A bug report has been filed. Try again later."))
        }
      }
    )
  }

  def signUpPost() = Action.async { implicit request: Request[AnyContent] =>
    SignupForm.form.bindFromRequest.fold(
      formWithErrors => {
        Future.successful(
          BadRequest(
            (views.html.error("Your Account Was Not Created!", "Something wen't wrong when you submitted the form."))
          )
        )
      },
      formData => {
        memberModel.create(formData, 0).map {
          if (_)
            Ok("worked")
          else
            Ok(views.html.error("Your Account Was Not Created!", "It seems something wen't super duper wrong. A report has been filed. Try again later."))
        }
      }
    )
  }

  def signUp() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.signup())
  }

  def signIn() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.signin())
  }

  def signInPost() = Action.async { implicit request: Request[AnyContent] =>
    SigninForm.form.bindFromRequest.fold(
      formWithErrors => {
        Future.successful(
          BadRequest(
            views.html.error("Form Failed!", "Something wen't wrong when you submitted the form.")
          )
        )
      },
      formData => {
        memberModel.validateUser(formData.email, formData.pwd).map { user =>
          if (user.nonEmpty)
            Redirect(routes.HomeController.home()).withSession(
              "email" -> formData.email,
              "pwd" -> formData.pwd
            )
          else
            Ok(views.html.error("Validation Failed!", "It seems your email or password are incorrect."))
        }
      }
    )
  }

  def forgotPassword() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.forgotPassword())
  }

  def home() = Action.async { implicit request: Request[AnyContent] =>
    val email: String = request.session.get("email") getOrElse ""
    val pwd: String = request.session.get("pwd") getOrElse ""

    memberModel.validateUser(email, pwd).map {
      _ match {
        case Some(user) => Ok(views.html.home())
        case None =>  Redirect(routes.HomeController.index())
      }
    }
  }

  def profileView() = Action.async { implicit request: Request[AnyContent] =>
    val email: String = request.session.get("email") getOrElse ""
    val pwd: String = request.session.get("pwd") getOrElse ""

    memberModel.validateUser(email, pwd).map {
      _ match {
        case Some(user) => Ok(views.html.profile())
        case None =>  Redirect(routes.HomeController.index())
      }
    }
  }
}
