# NexusMods4J

NexusMods4J is a wrapper around the [nexus mods](https://nexusmods.com) API.

This Library only utilizes two libraries, OKHTTP3, and JSON parser, GSON (may change in the future).

NexusMods4J uses a fluent design system to enhance the readability of your code. For example a standard method call may be `api.getGames()` or `api.endorse()`, these calls don't give you much information on the type of call, or what the call is connected to.  
A NexusMods4J method call may be `api.get().games().all()`, this call tells you three thing straight away, that it is a `get` request, that it is part of the `games` section of the api, and that you are requesting ALL the games(with info).

# Usage
You will first need to obtain an API key for your application (or use your personal api key for testing), see how to do this [here](https://app.swaggerhub.com/apis-docs/NexusMods/nexus-mods_public_api_params_in_form_data/1.0#/) or [here](https://github.com/Nexus-Mods/sso-integration-demo).  
Future versions may have a built in system for accessing the SSO and obtaining an api key.

Next, create a reference to `co.uk.innoxium.nexusmods4j.NexusAPI`. It should look like the following:
```java
NexusAPI api = new NexusAPI(<api key>);
```

You can now use the Fluent api design to access the API to your hearts desire (NexusMods does have rate limiting in place).

# Examples
An example of requesting a list of all moddable games can be executed with the following code:
```java
ArrayList<Game> allGames = api.get().games().all();
// You can also request non-verified games, these are games which may have mods available on the page, but are not yet visible.
ArrayList<Game> allGamesWithNonVerified = api.get().games().all(true);
```
As described above, this code sendes a `get` request to the `games` endpoint requesting ALL games

An exmaple of tracking a mod would be
```java
boolean tracked = api.post().mods().track(<game_domain>, <mod_id>);
// You can also untrack by calling:
boolean untracked = api.post().mods.untrack(<game_domain>, <mod_id>);
// You can also use a Mod reference due to it containing the "game_domain" and "mod_id" values in the request body.
boolean tracked = api.post().mods().track(modList.getSelectedItem());
// ^ This method assumes you are using a JList<Mod> to display/manipulate mods
```

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
