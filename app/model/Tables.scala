package model
// AUTO-GENERATED Slick data model
/** Stand-alone Slick data model for immediate use */
object Tables extends {
  val profile = slick.jdbc.PostgresProfile
} with Tables

/** Slick data model trait for extension, choice of backend or usage in the cake pattern. (Make sure to initialize this late.) */
trait Tables {
  val profile: slick.jdbc.JdbcProfile
  import profile.api._
  import slick.model.ForeignKeyAction
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import slick.jdbc.{GetResult => GR}

  /** DDL for all tables. Call .create to execute. */
  lazy val schema: profile.SchemaDescription = ChatMember.schema ++ Chatroom.schema ++ Contact.schema ++ Member.schema
  @deprecated("Use .schema instead of .ddl", "3.0")
  def ddl = schema

  /** Entity class storing rows of table ChatMember
   *  @param id Database column id SqlType(serial), AutoInc, PrimaryKey
   *  @param memberId Database column member_id SqlType(int4)
   *  @param chatroomId Database column chatroom_id SqlType(int4) */
  case class ChatMemberRow(id: Int, memberId: Int, chatroomId: Int)
  /** GetResult implicit for fetching ChatMemberRow objects using plain SQL queries */
  implicit def GetResultChatMemberRow(implicit e0: GR[Int]): GR[ChatMemberRow] = GR{
    prs => import prs._
    ChatMemberRow.tupled((<<[Int], <<[Int], <<[Int]))
  }
  /** Table description of table chat_member. Objects of this class serve as prototypes for rows in queries. */
  class ChatMember(_tableTag: Tag) extends profile.api.Table[ChatMemberRow](_tableTag, "chat_member") {
    def * = (id, memberId, chatroomId) <> (ChatMemberRow.tupled, ChatMemberRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(id), Rep.Some(memberId), Rep.Some(chatroomId))).shaped.<>({r=>import r._; _1.map(_=> ChatMemberRow.tupled((_1.get, _2.get, _3.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(serial), AutoInc, PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column member_id SqlType(int4) */
    val memberId: Rep[Int] = column[Int]("member_id")
    /** Database column chatroom_id SqlType(int4) */
    val chatroomId: Rep[Int] = column[Int]("chatroom_id")
  }
  /** Collection-like TableQuery object for table ChatMember */
  lazy val ChatMember = new TableQuery(tag => new ChatMember(tag))

  /** Entity class storing rows of table Chatroom
   *  @param id Database column id SqlType(serial), AutoInc, PrimaryKey
   *  @param name Database column name SqlType(varchar), Length(100,true)
   *  @param owner Database column owner SqlType(int4) */
  case class ChatroomRow(id: Int, name: String, owner: Int)
  /** GetResult implicit for fetching ChatroomRow objects using plain SQL queries */
  implicit def GetResultChatroomRow(implicit e0: GR[Int], e1: GR[String]): GR[ChatroomRow] = GR{
    prs => import prs._
    ChatroomRow.tupled((<<[Int], <<[String], <<[Int]))
  }
  /** Table description of table chatroom. Objects of this class serve as prototypes for rows in queries. */
  class Chatroom(_tableTag: Tag) extends profile.api.Table[ChatroomRow](_tableTag, "chatroom") {
    def * = (id, name, owner) <> (ChatroomRow.tupled, ChatroomRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(id), Rep.Some(name), Rep.Some(owner))).shaped.<>({r=>import r._; _1.map(_=> ChatroomRow.tupled((_1.get, _2.get, _3.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(serial), AutoInc, PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column name SqlType(varchar), Length(100,true) */
    val name: Rep[String] = column[String]("name", O.Length(100,varying=true))
    /** Database column owner SqlType(int4) */
    val owner: Rep[Int] = column[Int]("owner")
  }
  /** Collection-like TableQuery object for table Chatroom */
  lazy val Chatroom = new TableQuery(tag => new Chatroom(tag))

  /** Entity class storing rows of table Contact
   *  @param id Database column id SqlType(serial), AutoInc, PrimaryKey
   *  @param name Database column name SqlType(varchar), Length(150,true)
   *  @param email Database column email SqlType(varchar), Length(150,true)
   *  @param subject Database column subject SqlType(varchar), Length(400,true)
   *  @param message Database column message SqlType(text) */
  case class ContactRow(id: Int, name: String, email: String, subject: String, message: String)
  /** GetResult implicit for fetching ContactRow objects using plain SQL queries */
  implicit def GetResultContactRow(implicit e0: GR[Int], e1: GR[String]): GR[ContactRow] = GR{
    prs => import prs._
    ContactRow.tupled((<<[Int], <<[String], <<[String], <<[String], <<[String]))
  }
  /** Table description of table contact. Objects of this class serve as prototypes for rows in queries. */
  class Contact(_tableTag: Tag) extends profile.api.Table[ContactRow](_tableTag, "contact") {
    def * = (id, name, email, subject, message) <> (ContactRow.tupled, ContactRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(id), Rep.Some(name), Rep.Some(email), Rep.Some(subject), Rep.Some(message))).shaped.<>({r=>import r._; _1.map(_=> ContactRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(serial), AutoInc, PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column name SqlType(varchar), Length(150,true) */
    val name: Rep[String] = column[String]("name", O.Length(150,varying=true))
    /** Database column email SqlType(varchar), Length(150,true) */
    val email: Rep[String] = column[String]("email", O.Length(150,varying=true))
    /** Database column subject SqlType(varchar), Length(400,true) */
    val subject: Rep[String] = column[String]("subject", O.Length(400,varying=true))
    /** Database column message SqlType(text) */
    val message: Rep[String] = column[String]("message")
  }
  /** Collection-like TableQuery object for table Contact */
  lazy val Contact = new TableQuery(tag => new Contact(tag))

  /** Entity class storing rows of table Member
   *  @param id Database column id SqlType(serial), AutoInc, PrimaryKey
   *  @param name Database column name SqlType(varchar), Length(150,true)
   *  @param email Database column email SqlType(varchar), Length(150,true)
   *  @param pwd Database column pwd SqlType(varchar), Length(150,true)
   *  @param profileIcon Database column profile_icon SqlType(varchar), Length(250,true)
   *  @param banner Database column banner SqlType(varchar), Length(20,true)
   *  @param isArchived Database column is_archived SqlType(bool) */
  case class MemberRow(id: Int, name: String, email: String, pwd: String, profileIcon: String, banner: String, isArchived: Boolean)
  /** GetResult implicit for fetching MemberRow objects using plain SQL queries */
  implicit def GetResultMemberRow(implicit e0: GR[Int], e1: GR[String], e2: GR[Boolean]): GR[MemberRow] = GR{
    prs => import prs._
    MemberRow.tupled((<<[Int], <<[String], <<[String], <<[String], <<[String], <<[String], <<[Boolean]))
  }
  /** Table description of table member. Objects of this class serve as prototypes for rows in queries. */
  class Member(_tableTag: Tag) extends profile.api.Table[MemberRow](_tableTag, "member") {
    def * = (id, name, email, pwd, profileIcon, banner, isArchived) <> (MemberRow.tupled, MemberRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(id), Rep.Some(name), Rep.Some(email), Rep.Some(pwd), Rep.Some(profileIcon), Rep.Some(banner), Rep.Some(isArchived))).shaped.<>({r=>import r._; _1.map(_=> MemberRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6.get, _7.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(serial), AutoInc, PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column name SqlType(varchar), Length(150,true) */
    val name: Rep[String] = column[String]("name", O.Length(150,varying=true))
    /** Database column email SqlType(varchar), Length(150,true) */
    val email: Rep[String] = column[String]("email", O.Length(150,varying=true))
    /** Database column pwd SqlType(varchar), Length(150,true) */
    val pwd: Rep[String] = column[String]("pwd", O.Length(150,varying=true))
    /** Database column profile_icon SqlType(varchar), Length(250,true) */
    val profileIcon: Rep[String] = column[String]("profile_icon", O.Length(250,varying=true))
    /** Database column banner SqlType(varchar), Length(20,true) */
    val banner: Rep[String] = column[String]("banner", O.Length(20,varying=true))
    /** Database column is_archived SqlType(bool) */
    val isArchived: Rep[Boolean] = column[Boolean]("is_archived")
  }
  /** Collection-like TableQuery object for table Member */
  lazy val Member = new TableQuery(tag => new Member(tag))
}
