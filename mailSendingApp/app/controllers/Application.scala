package controllers

import com.typesafe.plugin._
import play.api._
import play.api.Play.current
import play.api.data._
import play.api.data.Forms._
import play.api.mvc._

object Application extends Controller {

  case class MailData(email: String)

  val mailForm = Form(
    mapping(
      "email" -> email)(MailData.apply)(MailData.unapply))

  def index = Action {
    Ok(views.html.index(mailForm))
  }

  def sendMail: Action[play.api.mvc.AnyContent] = Action { implicit request =>
    mailForm.bindFromRequest.fold (
    formWithErrors => {
      Redirect("/")
    },
      mailData => {
        val mail = use[MailerPlugin].email
            mail.setSubject("Email sent using Scala")
            mail.addRecipient(mailData.email)
            mail.addFrom(mailData.email)
            mail.send("Hello World")
            Redirect("/")
      })
 }
}