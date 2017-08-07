package services

import com.google.inject.Singleton
import play.Environment
import services.domain._

import scala.collection.{immutable, mutable}
import scala.io.Source

/**
  * service class for todo management
  */
@Singleton
class TodoManager {

  private def initializeData(): mutable.Map[Int, Todo] = {
    val map: mutable.Map[Int, Todo] = new mutable.HashMap[Int, Todo]
    val s = Source.fromFile(Environment.simple().getFile("data\\TODO.txt"), "UTF-8")
    val strList: List[String] = try s.getLines.toList finally s.close()
    strList.foreach(str => {
      val data: Array[String] = str split "\t"
      val todo: Todo = new Todo(data(0).toInt, statusMap.get(data(1)).get, data(2))
      map.put(todo.getId, todo)
    })
    return map
  }

  private val statusMap: immutable.Map[String, TodoStatus] = Map("DONE" -> DONE, "DOING" -> DOING, "UNDONE" -> UNDONE)

  private val todoMap: mutable.Map[Int, Todo] = initializeData() // read file at startup

  /**
    * @return todo-list order by id
    */
  def list:List[Todo] = todoMap.values.toList.sortBy(t => t.getId)

}
