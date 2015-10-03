name := "linguahack_3_words"

version := "1.0"

lazy val `linguahack_3_words` = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.1"

libraryDependencies ++= Seq( 
	javaCore,
    javaJdbc,
 	javaEbean,
 	anorm , cache , ws ,
  	"edu.stanford.nlp" % "stanford-corenlp" % "3.5.2",
	"postgresql" % "postgresql" % "9.1-901.jdbc4"
	)

unmanagedResourceDirectories in Test <+=  baseDirectory ( _ /"target/web/public/test" )  
