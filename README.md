scalajs-ionic-starttabs
==================

An experimental mobile application written in scala-js and using the ionic framework.
This application is the same one as the one created with : ```ionic start XXXXXXX tabs```

To start development :

```
sbt 
~fastOptJS
```
then, to run the app in the browser, you can use ripple-emulator
```
cd ionic
ripple emulate www
```
(see https://www.npmjs.org/package/ripple-emulator)

TODO 1

- Use yet to be created scala-js bindings for the Cordova API 
- Improve bindings for angular-ui-router (that should be merged with scalajs-angular)
- Improve bindings for ionic specific services (that should be put in a separate project)
- Improve the documentation in this README and in the code.

TODO 2
- use sbt to wrap ionic/cordova/npm commands to only have one tool to compile, build, emulate, run, etc. (sbt-web ?)
