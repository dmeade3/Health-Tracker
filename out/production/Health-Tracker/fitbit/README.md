jFitbit was created at a time when Fitbit refused to offer intraday data access via the official API, except to partner
applications. To work around this limitation, jFitbit scrapes the endpoints backing the web graphs to download intraday
data. Fitbit has since changed their stance: individuals are now granted personal access to
intraday data (only for the account associated with the API key), without question. So in 2017, if you're looking for
access to your personal Fitbit data, this client is no longer necessary. It continues to function, but I'd generally
recommend going the officially supported route whenever possible.

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

Fitbit fitbit = Fitbit.create( "[fitbit-email]", "[fitbit-password]" );

FitbitTracker tracker = fitbit.getTracker( );
System.out.println( "Tracker:       " + tracker.getProductName( ) );
System.out.println( "Last sync:     " + tracker.getLastSync( ) );
System.out.println( "Battery level: " + tracker.getBattery( ) );


### Step Count

Fitbit fitbit = Fitbit.create( "[fitbit-email]", "[fitbit-password]" );

System.out.println( "Today's step activity" );
for ( StepCount sc : fitbit.getStepCount( LocalDate.now( ) ) ) {
    System.out.println( sc.getInterval( ).getStart( ) + " " + sc.getValue( ) );
}



### Floor Activity

Fitbit fitbit = Fitbit.create( "[fitbit-email]", "[fitbit-password]" );

System.out.println( "Today's floor activity" );
for ( FloorCount fc : fitbit.getFloorCount( LocalDate.now( ) ) ) {
    System.out.println( fc.getInterval( ).getStart( ) + " " + fc.getValue( ) );
}


### Calorie Burn

Fitbit fitbit = Fitbit.create( "[fitbit-email]", "[fitbit-password]" );

System.out.println( "Today's calorie-burn" );
for ( CalorieBurn cb : fitbit.getCaloriesBurned( LocalDate.now( ) ) ) {
    System.out.println( cb.getInterval( ).getStart( ) + " " + cb.getValue( ) + "\t" + cb.getActivityLevel( ) );
}

### Sleep Log


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


### Weight Measurements


Fitbit fitbit = Fitbit.create( "[fitbit-email]", "[fitbit-password]" );

System.out.println( "Recent weight measurements" );
LocalDate from = LocalDate.now( ).minusDays( 30 );
LocalDate to = LocalDate.now( );
for ( Weight w : fitbit.getWeights( from, to ) ) {
    System.out.println( w.getDateTime( ) + " " + w.getValue( ) );
}


## Notes on Localization

Responses may contain localized strings based on the country selection of the user account.
As a workaround, users experiencing difficulty with their locale may temporarily switch their account to
en_US as follows:


Fitbit fitbit = Fitbit.create( "fitbit-email", "fitbit-password" );

// Override locale to be en_US for compatibility
fitbit.enableLocaleOverride( );

//data download and processing

// Restore original user locale upon completion
fitbit.restoreUserLocale( );
