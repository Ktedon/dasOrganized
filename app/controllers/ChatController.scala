package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import play.api.libs.streams.ActorFlow
import javax.inject.Inject
import akka.actor.ActorSystem
import akka.stream.Materializer

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
 class ChatController @Inject() (
     protected val dbConfigProvider: DatabaseConfigProvider,
     controllerComponents: ControllerComponents
 )(implicit ec: ExecutionContext, system: ActorSystem, mat: Materializer)
     extends AbstractController(controllerComponents)
     with HasDatabaseConfigProvider[JdbcProfile]
     with play.api.i18n.I18nSupport {

  private val contactModel = new models.ContactModel(db)(ec)
  private val memberModel = new models.MemberModel(db)(ec)

  
}
