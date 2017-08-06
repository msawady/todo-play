package controllers

import javax.inject._

import play.api.mvc._
import services.TodoManager

/**
  * controller class for todo management
  */
@Singleton
class TodoController @Inject()(todoManager: TodoManager, cc: ControllerComponents, todoView:views.html.todo) extends AbstractController(cc) {

  /**
    * returns view of todo-list
    */
  def list = Action {
    Ok(todoView(todoManager.list))
  }

}
