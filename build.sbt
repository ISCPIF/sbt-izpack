// ---------------------------------------------------------------------------
// SBT 0.10.x Build File for SBT IzPack Plugin
//
// Copyright (c) 2010-2011 Brian M. Clapper
//
// See accompanying license file for license information.
// ---------------------------------------------------------------------------

// ---------------------------------------------------------------------------
// Basic settings

name := "sbt-izpack"

version := "0.3.4.3"

sbtPlugin := true

organization := "org.clapper"

licenses := Seq("BSD-like" ->
  url("http://software.clapper.org/sbt-izpack/license.html")
)

description := "SBT plugin to generate an IzPack installer"

// ---------------------------------------------------------------------------
// Additional compiler options and plugins

scalacOptions ++= Seq("-deprecation", "-unchecked")

crossScalaVersions := Seq("2.9.2", "2.9.1")

seq(lsSettings :_*)

(LsKeys.tags in LsKeys.lsync) := Seq("izpack", "installer")

(description in LsKeys.lsync) <<= description(d => d)

// ---------------------------------------------------------------------------
// Other dependendencies

// External deps
libraryDependencies ++= Seq(
  "org.codehaus.izpack" % "izpack-standalone-compiler" % "5.0.0-beta11" % "compile",
  "org.yaml" % "snakeyaml" % "1.9"
)

libraryDependencies += "org.clapper" %% "grizzled-scala" % "1.0.13"

// ---------------------------------------------------------------------------
// Publishing criteria

publishTo <<= (version) { version: String =>
   val scalasbt = "http://scalasbt.artifactoryonline.com/scalasbt/"
   val (name, url) = if (version.contains("-SNAPSHOT"))
                       ("sbt-plugin-snapshots", scalasbt+"sbt-plugin-snapshots")
                     else
                       ("sbt-plugin-releases", scalasbt+"sbt-plugin-releases")
   Some(Resolver.url(name, new URL(url))(Resolver.ivyStylePatterns))
}

publishMavenStyle := false

publishArtifact in (Compile, packageBin) := true

publishArtifact in (Test, packageBin) := false

publishArtifact in (Compile, packageDoc) := false

publishArtifact in (Compile, packageSrc) := false
