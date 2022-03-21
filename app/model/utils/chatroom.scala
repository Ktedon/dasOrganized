package models

import slick.jdbc.PostgresProfile.api._

import scala.concurrent.ExecutionContext
import scala.concurrent.Future

import model.Tables._

case class ChatroomMembers(member: Int, chatroom: Int)
case class ChatroomData(room: controllers.AddChatroom, owner: Int)

class ChatroomModel(db: Database)(implicit ec: ExecutionContext)
    extends models.Model[ChatroomData, ChatroomRow] {

  def create(data: ChatroomData, userId: Int): Future[Boolean] =
    db.run(Chatroom += ChatroomRow(-1, data.room.name, data.owner)).map(_ > 0)

  def read(id: Int): Future[Seq[ChatroomRow]] =
    db.run(Chatroom.filter(_.id === id).result).map(j => j)

  def update(data: ChatroomRow): Future[Boolean] =
    db.run(
      Chatroom
        .filter(room => room.id === data.id)
        .update(data)
    ).map(_ > 0)

  def delete(id: Int): Future[Boolean] =
    db.run(Chatroom.filter(_.id === id).delete).map(_ > 0)

  def createChatroom(
      name: String,
      members: Seq[Int],
      owner: Int
  ): Future[Boolean] =
    db.run(Chatroom += ChatroomRow(-1, name, owner)).flatMap { v =>
      db.run(
        Chatroom
          .filter(room => room.owner === owner && room.name === name)
          .result
      ).flatMap {
        _.headOption match {
          case Some(roomFound) =>
            db.run(
              ChatMember ++= members
                .map(mem => ChatMemberRow(-1, mem, roomFound.id))
            ).map(_.getOrElse(0) > 0)
          case None => Future.successful(false)
        }
      }
    }
}
