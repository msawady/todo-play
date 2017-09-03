package services.repository

import java.io.PrintWriter

import com.google.inject.Singleton
import play.Environment
import services.domain.{Todo, TodoStatus}

import scala.collection.mutable
import scala.io.Source

/**
  * repository class for todo management
  *
  */
@Singleton
class TodoRepository() {

  private val filePath = "data/TODO.txt"
  private val encoding = "UTF-8"

  def getTodoList(): List[Todo] = {
    sortById(readTodoFile())
  }

  def updateTodoList(ids: List[Int], status: TodoStatus) = {
    val modified = readTodoFile()
    ids.foreach(id => {
      val todo: Todo = modified.get(id).get
      todo.update(status)
      modified.put(todo.getId, todo)
    })
    writeTodoFile(sortById(modified))
  }

  private def readTodoFile(): mutable.Map[Int, Todo] = {
    val map: mutable.Map[Int, Todo] = new mutable.HashMap[Int, Todo]
    val s = Source.fromFile(Environment.simple().getFile(filePath), encoding)
    val strList: List[String] = try s.getLines.toList finally s.close()
    strList.foreach(str => {
      val data: Array[String] = str split "\t"
      val todo: Todo = new Todo(data(0).toInt, TodoStatus.withName(data(1)), data(2))
      map.put(todo.getId, todo)
    })
    return map
  }

  private def writeTodoFile(list: List[Todo]) = {
    val pw = new PrintWriter(filePath, encoding)
    try list.foreach(todo => pw.write(todo.toTsvString + "\n")) finally pw.close()
  }

  private def sortById(map: mutable.Map[Int, Todo]): List[Todo] = {
    map.values.toList.sortBy(_.getId)
  }
}

