scalajs-ionic-starttabs
==================

An experimental mobile application written in scala-js and using the ionic framework.
This application is the same one as the one created with : ```ionic start XXXXXXX tabs```

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

TODO 1

- ~~Use yet to be created scala-js bindings for the Cordova API~~ NOT SURE, see the ngCordova TODO below
- Improve bindings for angular-ui-router (that should be merged with scalajs-angular)
- Improve bindings for ionic specific services (that should be put in a separate project)
- Use ngCordova to communicate with the cordova API
- Improve the documentation in this README and in the code.
- use sbt to wrap ionic/cordova/npm commands to only have one tool to compile, build, emulate, run, etc. (sbt-web ?)
- ~~use webjar to include ionic framework.~~
- ~~find a way to generate index-dev.html and index.html (for fastOptJS and fullOptJS) automatically.~~

FIXME 
- The back button does not close the application on android (4.1)
- Performance issue with a OnePlusOne device see http://forum.ionicframework.com/t/scrolling-and-animations-in-android-emulator-really-slow/1132/16
