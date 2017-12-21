package fr.ofuro.mydogtrackerspy;


import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private double latitude;
    private double longitude;
    public static final String TAG = "GeekQuote";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnActiver = findViewById(R.id.btn_main_activate);

        // création d'un locationListener
        final LocationListener locListener=new LocationListener() {
            @Override
            public void onLocationChanged(android.location.Location location) {
                double latitude=location.getLatitude();
                double longitude=location.getLongitude();
                String msg="New Latitude: "+latitude + "New Longitude: "+longitude;
                Toast.makeText(getBaseContext(),msg,Toast.LENGTH_LONG).show();
            }

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

        //Recupère la position du téléphone
        btnActiver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

                //try catch temporaire: évite les check de permissions pour l'instant
                try {
                    lm.requestLocationUpdates("gps", 60000, 1, locListener);
                } catch (SecurityException e) {
                    Toast.makeText(MainActivity.this, "Erreur de droits GPS.", Toast.LENGTH_SHORT).show();
                }

               Location maLocation;
                try{
                    maLocation = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                    latitude = maLocation.getLatitude();
                    longitude = maLocation.getLongitude();
                } catch (SecurityException e) {
                    Toast.makeText(MainActivity.this, "Erreur getLastKnowLocation GPS .", Toast.LENGTH_SHORT).show();
                }

                //Affichage check
                Toast.makeText(MainActivity.this, "position(lat: "+ latitude +
                        "; long: "+longitude, Toast.LENGTH_SHORT).show();

            }
        });
    }
}
