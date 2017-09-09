package services.domain

import slick.jdbc.MySQLProfile.api._

/**
  * entity class of todo
  */
case class Todo(id: Int, var status: TodoStatus, title: String)

class Todos(tag: Tag) extends Table[Todo](tag, "todo") {
  def id = column[Int]("id", O.PrimaryKey, O.AutoInc)

  def status = column[TodoStatus]("status")

  def title = column[String]("title")

  def * = (id, status, title) <> (Todo.tupled, Todo.unapply)

}
object Todos extends TableQuery(new Todos(_))




