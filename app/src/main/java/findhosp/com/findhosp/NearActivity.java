package findhosp.com.findhosp;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class NearActivity extends FragmentActivity implements OnMapReadyCallback {

    //Exp;icit
    private GoogleMap mMap;
    private int indexAnInt;
    private String tag = "9AprilV2";
    private double latADouble, longADouble;
    private LocationManager locationManager;
    private Criteria criteria;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_near);

        //Get Value From Intent
        getValueFromIntent();

        //Setup
        mySetup();

        // create map Fragment
        createFragment();

    }   // Main Method


    @Override
    protected void onResume() {
        super.onResume();

        locationManager.removeUpdates(locationListener);

        latADouble = 13.964591;
        longADouble = 100.585552;

        //For Find Location by Network
        Location networkLocation = myFindLocation(LocationManager.NETWORK_PROVIDER);
        if (networkLocation != null) {

            latADouble = networkLocation.getLatitude();
            longADouble = networkLocation.getLongitude();

        }

        //For Find Location my GPS card
        Location gpsLocation = myFindLocation(LocationManager.GPS_PROVIDER);
        if (gpsLocation != null) {
            latADouble = gpsLocation.getLatitude();
            longADouble = gpsLocation.getLongitude();

        }
        Log.d(tag, "Lat ==> " + latADouble);
        Log.d(tag, "Lng ==> " + longADouble);

        createCenterMap();

    }   //onResume

    @Override
    protected void onStop() {
        super.onStop();

        locationManager.removeUpdates(locationListener);
    }

    public Location myFindLocation(String strProvider) {

        Location location = null;

        if (locationManager.isProviderEnabled(strProvider)) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return null;
            }
            locationManager.requestLocationUpdates(strProvider, 1000, 10, locationListener);
            location = locationManager.getLastKnownLocation(strProvider);
        }

        return location;
    }

    public LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {

            latADouble = location.getLatitude();
            longADouble = location.getLongitude();


        }   //onLocationChanged

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };

    private void mySetup() {

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        criteria.setAltitudeRequired(false);
        criteria.setBearingRequired(false);

    }

    private void getValueFromIntent() {
        indexAnInt = getIntent().getIntExtra("Index", 0);
        Log.d(tag, "index ==> " + indexAnInt);
    }

    private void createFragment() {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        //Create Center map Marker User
        createCenterMap();

    }   //onmap

    private void createCenterMap() {
       try {
           LatLng latlng = new LatLng(latADouble, longADouble);
           mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latlng, 16));

       }catch (Exception e){
           e.printStackTrace();
       }
    }

}   // Main Class
