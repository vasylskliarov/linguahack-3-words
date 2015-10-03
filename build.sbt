name := "linguahack_3_words"

version := "1.0"

lazy val `linguahack_3_words` = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.1"

libraryDependencies ++= Seq(
  jdbc, anorm, cache, ws,
  "edu.stanford.nlp" % "stanford-corenlp" % "3.5.2"
)

unmanagedResourceDirectories in Test <+=  baseDirectory ( _ /"target/web/public/test" )