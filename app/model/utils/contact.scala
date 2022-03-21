package models

import slick.jdbc.PostgresProfile.api._

import scala.concurrent.ExecutionContext
import scala.concurrent.Future

import model.Tables._

class ContactModel(db: Database)(implicit ec: ExecutionContext)
    extends models.Model[controllers.ContactForm, ContactRow] {

  def create(data: controllers.ContactForm, userId: Int): Future[Boolean] =
    db.run(Contact += ContactRow(-1, data.name, data.email, data.subject, data.message)).map(_ > 0)

  def read(id: Int):   Future[Seq[ContactRow]] =
    db.run(Contact.filter(_.id === id).result).map(j => j)

  def update(data: ContactRow): Future[Boolean] =
    db.run(Contact.filter(contact => contact.id === data.id)
      .update(data)).map(_ > 0)

  def delete(id: Int): Future[Boolean] =
    db.run(Contact.filter(_.id === id).delete).map(_ > 0)
}
