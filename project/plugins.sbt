resolvers ++= Seq(
  "less is" at "http://repo.lessis.me"
)

addSbtPlugin("me.lessis" % "ls-sbt" % "0.1.2")

libraryDependencies += "org.clapper" %% "grizzled-scala" % "1.0.13"
