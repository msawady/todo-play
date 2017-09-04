package services.domain

import slick.driver.MySQLDriver.api._

sealed trait TodoStatus

/**
  * todo status
  */
object TodoStatus {

  case object UNDONE extends TodoStatus

  case object DOING extends TodoStatus

  case object DONE extends TodoStatus

  implicit val todoStatusMapper = MappedColumnType.base[TodoStatus, String](
    _.toString, TodoStatus.withName(_)
  )

  def withName(s: String) = s.toLowerCase match {
    case "undone" => UNDONE
    case "doing" => DOING
    case "done" => DONE
  }

}
