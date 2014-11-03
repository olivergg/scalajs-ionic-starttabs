scalajs-ionic-starttabs
==================

An experimental HTML5 mobile application that puts together the following libraries :
- scala-js to compile scala to javascript (see http://www.scala-js.org/)
- Ionic framework that brings an angular based mobile framework (see http://ionicframework.com/)
- scala-js-angular that provides Angular bindings (see https://github.com/greencatsoft/scalajs-angular)
- scalatags that provides a way to "compile" scala code to HTML files (see https://github.com/lihaoyi/scalatags) ( a SBT task has been specially devised to do so)

You will need to install the ionic framework first (see http://ionicframework.com/getting-started/).

This application aims to be almost equivalent to the one created with : ```ionic start XXXXXXX tabs```

There are several things you can customize in the Build.scala file :
```
 // the output folder for the generated scala-js javascript files (and jsdeps and launcher and SourceMaps)
 lazy val outputCompiledJS = new File("ionic/www/js")
 // the output folder for the generated HTML files
 lazy val outputCompiledHTML = new File("ionic/www")
 // the input package folder that will be scanned to find scala files compilable to HTML
 lazy val htmlScalaSourceDir = "com/olivergg/html"
```

To start development :

```
sbt 
~fastOptJS
```

To compile scala files in the html package to HTML files :
```
compileAllToHtmlTask
```

then, to run the app in the browser, you can use ionic built-in command
```
cd ionic
ionic serve
```
(see http://ionicframework.com/docs/guide/testing.html)

TODO

- ~~Use yet to be created scala-js bindings for the Cordova API~~ NOT SURE, see the ngCordova TODO below
- Improve bindings for angular-ui-router (that should be merged with scalajs-angular)
- Improve bindings for ionic specific services (that should be put in a separate project)
- Use ngCordova to communicate with the cordova API
- Improve the documentation in this README and in the code.
- use sbt to wrap ionic/cordova/npm commands to only have one tool to compile, build, emulate, run, etc. (sbt-web ?)
- ~~use webjar to include ionic framework.~~
- ~~find a way to generate index-dev.html and index.html (for fastOptJS and fullOptJS) automatically.~~

FIXME 
- The back button does not close the application on android (4.1) (must be related to the cordova API)
- Performance issue with a OnePlusOne device see http://forum.ionicframework.com/t/scrolling-and-animations-in-android-emulator-really-slow/1132/16 => animation have been removed to circumvent this issue
