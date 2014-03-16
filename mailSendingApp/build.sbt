name := "mailSendingApp"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  jdbc,
  anorm,
  cache,
  "com.typesafe" %% "play-plugins-mailer" % "2.1-RC2"
)     

play.Project.playScalaSettings