package controllers

import javax.inject._
import play.api.mvc._

import models._


@Singleton
class HomeController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {





  def index() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.index())
  }

  def students()= Action { implicit request: Request[AnyContent] =>
    Ok(views.html.students(DB.all_students))
  }

def student(index : String) = Action { implicit request: Request[AnyContent] =>
  Ok(views.html.student(DB.all_students.filter(_.index == index.toInt), index))
}

  def lectures() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.lectures(DB.ALL_Lectures))
  }

  def lecture(index : String) =  Action { implicit request: Request[AnyContent] =>
      Ok(views.html.lecture(DB.ALL_Lectures.filter(_.id == index).head, DB.ALL_enrollments.filter(_.id == index).head.students))
    }

}
