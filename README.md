# Sport-Results Android

[![Build Status](https://travis-ci.org/angelkjos/SportResultsAndroid.svg?branch=master)](https://travis-ci.org/angelkjos/SportResultsAndroid)

Android app for showing live sport results. Showcasing many best practices for development od Android applications.

Build:
```
./gradlew clean assembleDebug
```

Unit-Test:
```
./gradlew clean test
```

Main features of the app:
* Show a list of football teams
  * Favourite a team
* Show live results for favoured teams
* Show upcoming fixtures for favoured teams
* Notify user of result change
* Background pooling for live results

The project shows usage of:
* MVP pattern on Android including Interactors & Entities for business-logic
* Dependency-Injection using Dagger2
* RxJava/RxAndroid
* Unit-Testing
  * Mockito
  * Matchers
  * TestObserver
* JobScheduler
* SOLID coding principles
