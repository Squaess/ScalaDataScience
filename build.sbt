lazy val root = (project in file("."))
    .settings(
        name := "ScalaDataScience",
        scalaVersion := "2.13.4"
    )

libraryDependencies ++= Seq(
  "com.lihaoyi" %% "upickle" % "0.9.5",
  "com.lihaoyi" %% "os-lib" % "0.7.1",
  "org.plotly-scala" %% "plotly-almond" % "0.7.0"
)