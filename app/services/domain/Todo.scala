package services.domain

/**
  * entity class of todo
  */
class Todo(id: Int, status: TodoStatus, title: String) {

  def getId = id

  def getStatus = status

  def getTitle = title

  def toTsvString = "%s\t%s\t%s".format(id.toString, status, title)
}




