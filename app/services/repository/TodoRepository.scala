package services.repository

import javax.inject.Inject

import com.google.inject.Singleton
import services.domain.{Todo, TodoStatus, Todos}
import slick.driver.MySQLDriver.api._

import scala.concurrent.Await
import scala.concurrent.duration.Duration

/**
  * repository class for todo management
  *
  */
@Singleton
class TodoRepository {

  val database = Database.forURL("jdbc:mysql://localhost/todo_play?characterEncoding=UTF8&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
    driver = "com.mysql.jdbc.Driver", user = "user", password = "password")

  def getTodoList(): List[Todo] = {
    val f = database.run(Todos.sortBy(_.id).result)
    Await.result(f, Duration.Inf).toList
  }

  def updateTodoList(ids: List[Int], status: TodoStatus) = {
    val toBeUpdated = Todos.filter(row => row.id inSetBind ids)
    database.run(toBeUpdated.map(_.status).update(status))
  }

}

