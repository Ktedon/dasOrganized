package models

import slick.jdbc.PostgresProfile.api._

import scala.concurrent.ExecutionContext
import scala.concurrent.Future

import model.Tables._

class ChatroomMembersModel(db: Database)(implicit ec: ExecutionContext)
    extends models.Model[ChatroomMembers, ChatMemberRow] {

  def create(data: ChatroomMembers, userId: Int): Future[Boolean] =
    db.run(ChatMember += ChatMemberRow(-1, data.member, data.chatroom))
      .map(_ > 0)

  def read(id: Int): Future[Seq[ChatMemberRow]] =
    db.run(ChatMember.filter(_.id === id).result).map(j => j)

  def update(data: ChatMemberRow): Future[Boolean] =
    db.run(
      ChatMember
        .filter(members => members.id === data.id)
        .update(data)
    ).map(_ > 0)

  def delete(id: Int): Future[Boolean] =
    db.run(ChatMember.filter(_.id === id).delete).map(_ > 0)
}
