```
./gradlew -p lib-foo :publishAllPublicationsToMavenRepository -Pdeploy.version=1.0 -Pinclude.cinterop=true
./gradlew -p lib-foo :publishAllPublicationsToMavenRepository -Pdeploy.version=1.1 -Pinclude.cinterop
=false
./gradlew -p lib-bar :publishAllPublicationsToMavenRepository -Pdeploy.version=1.0
./gradlew -p app build 
```