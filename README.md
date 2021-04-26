# NexusMods4J

NexusMods4J is a wrapper around the [nexus mods](https://nexusmods.com) API.

This Library only utilizes two libraries, OKHTTP3, and JSON parser, GSON (may change in the future).

NexusMods4J uses a fluent design system to enhance the readability of your code. For example  
a standard method call may be `api.getGames()` or `api.endorse()`, these calls don't give you  
much information on the type of call, or what the call is connected to.  
A NexusMods4J method call may be `api.get().games().all()`, this call tells you three thing straight away,  
that it is a `get` request, that it is part of the `games` section of the api, and that you are  
requesting ALL the games(with info).

# How to get

We currently are not on Maven Central, so you will need to add our repo to your pom or build.gradle.  
Our repo is `https://repo.repsy.io/mvn/innoxium/innoxium`  

For Maven: 
```xml
<dependency>
  <groupId>uk.co.innoxium.nexusmods4j</groupId>
  <artifactId>nexusmods4j</artifactId>
  <version>1.0</version>
</dependency>
```

For Gradle:
```groovy
implementation 'uk.co.innoxium.nexusmods4j:nexusmods4j:1.0'
```

Or Gradle Kotlin DSL:
```kotlin
implementation("uk.co.innoxium.nexusmods4j:nexusmods4j:1.0")
```