package models

import slick.jdbc.PostgresProfile.api._

import scala.concurrent.ExecutionContext
import scala.concurrent.Future

import model.Tables._

// class TwoPersonChatModel(db: Database)(implicit ec: ExecutionContext)
//     extends models.Model[controllers.TwoPersonChatForm, TwoPersonChatRow] {
//
//   def create(data: controllers.TwoPersonChatForm, userId: Int): Future[Boolean] =
//     db.run(TwoPersonChat += TwoPersonChatRow(-1, userId, data.toId)).map(_ > 0)
//
//   def read(id: Int):   Future[Seq[TwoPersonChatRow]] =
//     db.run(TwoPersonChat.filter(r => r.memberOne === id || r.memberTwo === id).result).map(j => j)
//
//   def update(data: TwoPersonChatRow): Future[Boolean] =
//     db.run(TwoPersonChat.filter(r => r.id === data.id)
//       .update(data)).map(_ > 0)
//
//   def delete(id: Int): Future[Boolean] =
//     db.run(TwoPersonChat.filter(_.id === id).delete).map(_ > 0)
// }
