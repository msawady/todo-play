package services.domain

/**
  * todo status
  */
sealed trait TodoStatus
case object UNDONE extends TodoStatus
case object DOING extends  TodoStatus
case object DONE extends TodoStatus

