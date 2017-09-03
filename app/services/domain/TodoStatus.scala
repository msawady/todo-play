package services.domain

sealed trait TodoStatus

/**
  * todo status
  */
object TodoStatus {

  case object UNDONE extends TodoStatus

  case object DOING extends TodoStatus

  case object DONE extends TodoStatus

  def withName(s: String) = s.toLowerCase match {
    case "undone" => UNDONE
    case "doing" => DOING
    case "done" => DONE
  }
}
