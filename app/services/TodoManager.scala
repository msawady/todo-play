package services

import javax.inject.Inject

import com.google.inject.Singleton
import services.domain.{Todo, TodoStatus}
import services.repository.TodoRepository

/**
  * service class for todo management
  */
@Singleton
class TodoManager @Inject()(todoRepository: TodoRepository) {

  /**
    * get Todo List
    *
    * @return todo-list order by id
    */
  def list = todoRepository.getTodoList()

  /**
    * update todo status
    *
    * @param ids    id list of target TODO
    * @param status TodoStatus change to
    */
  def update(ids: List[Int], status: String): Unit = todoRepository.updateTodoList(ids, TodoStatus.withName(status))

  def add(title: String): Unit = todoRepository.addTodo(new Todo(0, TodoStatus.UNDONE, title))

}
