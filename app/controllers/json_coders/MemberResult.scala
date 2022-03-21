package controllers

import play.api.libs.json._
import play.api.libs.functional.syntax._

import model.Tables._

object MemberResult {
  implicit val locationWrites = new Writes[MemberRow] {
    def writes(member: MemberRow) = Json.obj(
      "id"  -> member.id,
      "icon" -> member.profileIcon,
      "banner" -> member.banner,
      "name" -> member.name,
      "isArchived" -> member.isArchived
    )
  }
}
