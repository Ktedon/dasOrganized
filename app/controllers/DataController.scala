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

import play.api.libs.json._
import play.api.libs.functional.syntax._

import MemberResult._
import AddChatroom._

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's data.
 */
 @Singleton
 class DataController @Inject() (
     protected val dbConfigProvider: DatabaseConfigProvider,
     controllerComponents: ControllerComponents
 )(implicit ec: ExecutionContext)
     extends AbstractController(controllerComponents)
     with HasDatabaseConfigProvider[JdbcProfile]
     with play.api.i18n.I18nSupport {

  private val contactModel = new models.ContactModel(db)(ec)
  private val memberModel = new models.MemberModel(db)(ec)
  private val chatroomModel = new models.ChatroomModel(db)(ec)

  def profileSearch(query: String = "") = Action.async { implicit request: Request[AnyContent] =>
    val email: String = request.session.get("email") getOrElse ""
    val pwd: String = request.session.get("pwd") getOrElse ""

    memberModel.validateUser(email, pwd).flatMap {
      _ match {
        case Some(user) =>
          memberModel.allButMe(user.id).map { users =>
            val filteredUsers = users.filter(_.name.toUpperCase.contains(query.toUpperCase))
            Ok(Json.toJson(filteredUsers))
          }
        case None =>  Future.successful(Ok("{}"))
      }
    }
  }

  def createChatroom() = Action.async { implicit request: Request[AnyContent] =>
    ChatroomForm.form.bindFromRequest.fold(
      formWithErrors => {
        Future.successful(
          BadRequest(
            views.html.error("Form Failed!", "Something went wrong when you submitted the form.")
          )
        )
      },
      formData => {

        val email: String = request.session.get("email") getOrElse ""
        val pwd: String = request.session.get("pwd") getOrElse ""

        memberModel.validateUser(email, pwd).flatMap { user =>
          if (user.nonEmpty)
            Json.parse(formData.json).validate[AddChatroom] match {
              case s: JsSuccess[AddChatroom] =>
                chatroomModel.createChatroom(s.get.name, s.get.members, user.get.id).map {
                  if (_)
                    Ok("{success: true}")
                  else
                    Ok("{success: false}")
                }
              case e: JsError =>
                Future.successful(
                  BadRequest(
                    views.html.error("Form Failed!", "Something went wrong when you submitted the form.")
                  )
                )
            }

          else
            Future.successful(
              Ok(
                views.html.error("Validation Failed!", "It seems your email or password are incorrect.")
              )
            )
        }
      }
    )
  }
}
