package org.fuzzyrobot.weather.activity;

import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

import org.fuzzyrobot.weather.R;
import org.fuzzyrobot.weather.fragment.WeatherFragment;
import org.fuzzyrobot.weather.log.Log;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private GoogleApiClient googleApiClient;
    private WeatherFragment weatherFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        weatherFragment = (WeatherFragment) getSupportFragmentManager().findFragmentById(R.id.container);
        if (weatherFragment == null) {
            weatherFragment = new WeatherFragment();
            Log.d("adding");
            getSupportFragmentManager().beginTransaction().add(R.id.container, weatherFragment).commit();
        }

        if (googleApiClient == null) {
            googleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }
    }

    @Override
    protected void onStart() {
        googleApiClient.connect();
        super.onStart();
    }

    @Override
    protected void onStop() {
        googleApiClient.disconnect();
        super.onStop();
    }

    @Override
    public void onConnected(Bundle connectionHint) {
        final Location lastLocation = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
        Log.d(lastLocation);
        if (lastLocation != null) {
            weatherFragment.setLocation(lastLocation);
        }
    }

    @Override
    public void onConnectionSuspended(final int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull final ConnectionResult connectionResult) {

    }
}
