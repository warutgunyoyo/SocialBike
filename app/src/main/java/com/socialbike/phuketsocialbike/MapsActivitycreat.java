package com.socialbike.phuketsocialbike;

import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import  com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import android.app.Activity;
import android.view.Menu;
import  com.google.android.gms.maps.CameraUpdateFactory;
import  com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import  com.google.android.gms.maps.GoogleMap.OnMarkerDragListener;
import  com.google.android.gms.maps.model.Polyline;
import  com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.List;

public class MapsActivitycreat extends FragmentActivity {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    static final  LatLng phuc = new LatLng(7.966598, 98.359929);
    private Polyline polyline;
    private List<LatLng> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_activitycreat);
        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getBaseContext());
        if (status == ConnectionResult.SUCCESS) {
            SupportMapFragment fm = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
            mMap = fm.getMap();
            mMap.setMyLocationEnabled(true);
            list = new ArrayList<LatLng>();
            mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                @Override
                public void onMapClick(LatLng arg0) {
                    MarkerOptions m = new MarkerOptions();
                    m.position(arg0);
                    m.draggable(true);
                    m.snippet("กดที่นี่เพื่อลบ");
                    m.title(arg0.toString());
                    mMap.addMarker(m);

                    list.add(arg0);
                    drawRoute();
                }
            });
            mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                @Override
                public void onInfoWindowClick(Marker arg0) {
                    arg0.remove();
                }
            });
            mMap.setOnMarkerDragListener(new OnMarkerDragListener() {
                @Override
                public void onMarkerDragStart(Marker arg0) {
                    LatLng ll = arg0.getPosition();
                    arg0.setSnippet(ll.latitude + "," + ll.longitude);
                    arg0.showInfoWindow();

                }

                @Override
                public void onMarkerDrag(Marker marker) {

                }

                @Override
                public void onMarkerDragEnd(Marker marker) {

                }
            });

        }
        mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                .getMap();
        if (mMap != null) {
            // Try to obtain the map from the SupportMapFragment.
            Marker hs1 = mMap.addMarker(new MarkerOptions().position(phuc).title("ภูเก็ต").snippet("จังหวัด"));
            CameraPosition cameraPosition = new CameraPosition.Builder().target(phuc).zoom(12).build();

            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        }
    }



    @Override
    protected void onResume() {
        super.onResume();

    }
    public void  drawRoute(){
        PolylineOptions po;
        if (polyline == null){
            po = new PolylineOptions();

            for (int i = 0, tam = list.size(); i < tam; i++){

                po.add(list.get(i));
            }
            po.color(Color.RED);
            polyline = mMap.addPolyline(po);

        }

        else {
            polyline.setPoints(list);
        }

    }
    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p/>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */


    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */

}
