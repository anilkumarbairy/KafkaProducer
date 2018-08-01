name := "Kafka Custom Partitioner"
version := "1.0"
scalaVersion := "2.11.11"
resolvers ++= Seq ("apache-snapshots" at "http://repository-apache.org/snapshots/")
libraryDependencies ++= {
  Seq(
    "org.apache.kafka" %% "kafka" % "1.0.0",
    "org.apache.kafka" % "kafka-clients" % "1.0.0"
  )
}