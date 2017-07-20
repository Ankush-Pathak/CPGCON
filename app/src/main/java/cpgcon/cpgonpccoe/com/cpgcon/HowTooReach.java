package cpgcon.cpgonpccoe.com.cpgcon;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.provider.ContactsContract;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class HowTooReach extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    int flag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_too_reach);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        ConnectivityManager check = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        if(check.getActiveNetworkInfo() != null
                && check.getActiveNetworkInfo().isAvailable()
                && check.getActiveNetworkInfo().isConnected())
        {
            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);
            Toast.makeText(this,"Tap on the marker to get navigation options.",Toast.LENGTH_LONG).show();

        }
        else
        {
            Intent intent = new Intent(HowTooReach.this,map_zoom.class);
            startActivity(intent);
            finish();
        }


    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng cpgconLoc = new LatLng(18.651671, 73.762027);
        mMap.addMarker(new MarkerOptions().position(cpgconLoc).title("cPGCON 2016"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(cpgconLoc));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(12), 5000, null);

    }
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this,MainActivity.class);
        intent.putExtra("key",1);
        startActivity(intent);
        finish();
    }
}
