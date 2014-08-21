organization := "scalatt"

name := "Mytest"

scalaVersion := "2.10.4"

scalacOptions ++= Seq("-unchecked", "-deprecation", "-Xlint")

libraryDependencies ++= Seq("com.datastax.spark" %% "spark-cassandra-connector" % "1.0.0-rc4" withSources() withJavadoc(),  "org.specs2" %% "specs2" % "1.12.3" % "test" )

resolvers ++= Seq("snapshots" at "http://oss.sonatype.org/content/repositories/snapshots",
  "releases" at "http://oss.sonatype.org/content/repositories/releases")
