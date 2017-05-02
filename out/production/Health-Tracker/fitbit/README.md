# jFitbit

jFitbit was created at a time when Fitbit refused to offer intraday data access via the official API, except to partner applications. To work around this limitation, jFitbit scrapes the endpoints backing the web graphs to download intraday data. Fitbit has since changed their stance: individuals are now granted personal access to intraday data (only for the account associated with the API key), without question. So in 2017, if you're looking for access to your personal Fitbit data, this client is no longer necessary. It continues to function, but I'd generally recommend going the officially supported route whenever possible.

This client currently supports the following data:

 * Calorie burn/activity level on a 5-minute interval
 * Floor count on a 5-minute interval (if device supported)
 * Sleep level on a 1-minute interval
 * Step count on a 5-minute interval
 
Beyond above intraday time series data, jFitbit also provides access to:

  * Tracking device status and information
  * Weight measurements

## Example Usage

### Tracker Information

```java
Fitbit fitbit = Fitbit.create( "[fitbit-email]", "[fitbit-password]" );

FitbitTracker tracker = fitbit.getTracker( );
System.out.println( "Tracker:       " + tracker.getProductName( ) );
System.out.println( "Last sync:     " + tracker.getLastSync( ) );
System.out.println( "Battery level: " + tracker.getBattery( ) );
```

### Step Count

```java
Fitbit fitbit = Fitbit.create( "[fitbit-email]", "[fitbit-password]" );

System.out.println( "Today's step activity" );
for ( StepCount sc : fitbit.getStepCount( LocalDate.now( ) ) ) {
    System.out.println( sc.getInterval( ).getStart( ) + " " + sc.getValue( ) );
}
```


### Floor Activity

```java
Fitbit fitbit = Fitbit.create( "[fitbit-email]", "[fitbit-password]" );

System.out.println( "Today's floor activity" );
for ( FloorCount fc : fitbit.getFloorCount( LocalDate.now( ) ) ) {
    System.out.println( fc.getInterval( ).getStart( ) + " " + fc.getValue( ) );
}
```

### Calorie Burn

```java
Fitbit fitbit = Fitbit.create( "[fitbit-email]", "[fitbit-password]" );

System.out.println( "Today's calorie-burn" );
for ( CalorieBurn cb : fitbit.getCaloriesBurned( LocalDate.now( ) ) ) {
    System.out.println( cb.getInterval( ).getStart( ) + " " + cb.getValue( ) + "\t" + cb.getActivityLevel( ) );
}
```

### Sleep Log

```java
Fitbit fitbit = Fitbit.create( "[fitbit-email]", "[fitbit-password]" );

System.out.println( "Today's sleep" );
for ( SleepSession ss : fitbit.getSleepSessions( LocalDate.now( ) ) ) {

    System.out.println( "\nAsleep for " + ss.getDurationAsleep( ).getStandardMinutes( ) + " minutes" );
    System.out.println( "Restless for " + ss.getDurationRestless( ).getStandardMinutes( ) + " minutes" );
    System.out.println( "Awake for " + ss.getDurationAwake( ).getStandardMinutes( ) + " minutes" );
    
    //Write minute-by-minute sleep state over duration of session to stdout
    for ( SleepLevel level : ss.getSleepLevels( ) ) {
        System.out.println( level.getInterval( ).getStart( ) + " " + level.getValue( ) );
    }
}
```

### Weight Measurements

```java
Fitbit fitbit = Fitbit.create( "[fitbit-email]", "[fitbit-password]" );

System.out.println( "Recent weight measurements" );
LocalDate from = LocalDate.now( ).minusDays( 30 );
LocalDate to = LocalDate.now( );
for ( Weight w : fitbit.getWeights( from, to ) ) {
    System.out.println( w.getDateTime( ) + " " + w.getValue( ) );
}

```

## Notes on Localization

Responses may contain localized strings based on the country selection of the user account.
As a workaround, users experiencing difficulty with their locale may temporarily switch their account to
en_US as follows:

```java
Fitbit fitbit = Fitbit.create( "fitbit-email", "fitbit-password" );

// Override locale to be en_US for compatibility
fitbit.enableLocaleOverride( );

//data download and processing

// Restore original user locale upon completion
fitbit.restoreUserLocale( );
```

### Time Zone

Another issue can result from discrepancies between local time and the time zone selected
in the Fitbit web profile. As the JSON timestamps lack zone information, we assume the local system time matches
that selected zone on the Fitbit account.

If using the mobile app to sync, disabling the option to set time zone automatically is also recommended.
Otherwise you may find data is lost or time shifted when traveling.

## Changes from version 2.x.x

Previously, jFitbit relied on the XML endpoints that backed the Flash-based graphed on fitbit.com. With
the advent of the new online dashboards backed by the "/getNewGraphData" JSON endpoint, jFitbit migrated forward. While the
same basic data is available, derived activity and summary data is not as readily present.

Along with a refactor of the data models, daily-resolution queries have been removed; instead this client focuses solely
on providing intraday-resolution data. This release also find new added access to weight measurements and tracker status.

This update isn't plug-and-play with previous jFitbit releases. Presumably you can continue using the 2.x client
if desired, but I have no clue how long Fitbit will continue providing the older XML endpoints ("/getGraphData").

## Dependencies

 * [Apache HttpClient 4.3.x](http://hc.apache.org/)
 * [Gson 2.3.x](https://github.com/google/gson)
 * [Joda-Time 2.8.x](http://www.joda.org/joda-time/)

## Android Compatibility

This project is built against Apache HttpClient 4.3.x. Since the HttpClient packaged with Android is effectively a
fork of HttpClient 4.0, compatibility is an annoying issue. This project can be used on Android, however, using the
[HttpClient for Android project](https://hc.apache.org/httpcomponents-client-4.3.x/android-port.html) in place of
the above Apache HttpClient dependency.

## License

See the included [LICENSE](LICENSE.md) for rights and limitations under the terms of the MIT license.

## Downloads

Source is hosted at the [jFitbit GitHub repository](https://github.com/claygregory/jfitbit).
