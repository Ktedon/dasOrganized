package models

import slick.jdbc.PostgresProfile.api._

import scala.concurrent.ExecutionContext
import scala.concurrent.Future

import model.Tables._

class MemberModel(db: Database)(implicit ec: ExecutionContext)
    extends models.Model[controllers.SignupForm, MemberRow] {

  def create(data: controllers.SignupForm, userId: Int): Future[Boolean] =
    db.run(Member += MemberRow(-1, data.name, data.email, data.pwd, s"https://avatars.dicebear.com/api/avataaars/${scala.util.Random.nextInt}.svg", "banner18.jpg", false)).map(_ > 0)

  def read(id: Int):   Future[Seq[MemberRow]] =
    db.run(Member.filter(_.id === id).result).map(j => j)

  def update(data: MemberRow): Future[Boolean] =
    db.run(Member.filter(member => member.id === data.id)
      .update(data)).map(_ > 0)

  def delete(id: Int): Future[Boolean] =
    db.run(Member.filter(_.id === id).delete).map(_ > 0)

  def validateUser(email: String, pwd: String): Future[Option[MemberRow]] =
    db.run(Member.filter(m => m.email === email && m.pwd === pwd).result)
      .map(_.headOption)

  def allButMe(id: Int): Future[Seq[MemberRow]] =
    db.run(Member.filter(_.id =!= id).result).map(j => j)
}
