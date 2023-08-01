```
./gradlew -p lib-foo-internal publishToRepoDir -Pdeploy.ver=1.0 -Pcinterop=true
./gradlew -p lib-foo publishToRepoDir -Pdeploy.ver=1.0
./gradlew -p lib-bar publishToRepoDir -Pdeploy.ver=1.0 -Pfoo.ver=1.0
./gradlew -p app runDebugExecutableMacosX64 -Pfoo.ver=1.0 -Pbar.ver=1.0
```

```
./gradlew -p lib-foo-internal publishToRepoDir -Pdeploy.ver=1.1 -Pcinterop=false
./gradlew -p lib-foo publishToRepoDir -Pdeploy.ver=1.1
./gradlew -p app runDebugExecutableMacosX64 -Pfoo.ver=1.1 -Pbar.ver=1.0
```