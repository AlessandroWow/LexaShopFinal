package com.example.example;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.preference.PreferenceManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.tileprovider.tilesource.XYTileSource;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.Polyline;

import androidx.appcompat.app.AppCompatActivity;

public class AccesoAdministrador extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accesoadministrador);
        Configuration.getInstance().load(getApplicationContext(), PreferenceManager.getDefaultSharedPreferences(getApplicationContext()));
        MapView mapView = findViewById(R.id.mapView);
        mapView.setTileSource(TileSourceFactory.MAPNIK);
        mapView.setBuiltInZoomControls(true);
        mapView.setMultiTouchControls(true);

        double metroLatitud = -33.4991618;
        double metroLongitud = -70.589146;

        GeoPoint MetroPoint = new GeoPoint(metroLatitud, metroLongitud);
        mapView.getController().setZoom(15.0);
        mapView.getController().setCenter(MetroPoint);

        Marker marcadorMetro = new Marker(mapView);
        marcadorMetro.setPosition(MetroPoint);
        marcadorMetro.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        marcadorMetro.setTitle("METRO LAS TORRES");
        marcadorMetro.setSnippet("Mi metro mas cercano");

        mapView.getOverlays().add(marcadorMetro);

        double tiendaLatitud = -33.5014025;
        double tiendaLongitud = -70.5890237;

        GeoPoint tiendaPoint = new GeoPoint(tiendaLatitud, tiendaLongitud);

        Marker marcadorTienda = new Marker(mapView);
        marcadorTienda.setPosition(tiendaPoint);
        marcadorTienda.setAnchor(0.2f, 0.4f);
        marcadorTienda.setInfoWindowAnchor(0.2f, 0.0f);
        marcadorTienda.setTitle("Tienda Don Roberto");
        marcadorTienda.setSnippet("Mi tienda favorita");

        mapView.getOverlays().add(marcadorTienda);

        Polyline linea = new Polyline();
        linea.addPoint(MetroPoint);
        linea.addPoint(tiendaPoint);
        linea.setColor(0xFF0000FF);
        linea.setWidth(5);
        mapView.getOverlayManager().add(linea);

        Spinner mapTypeSpinner = findViewById(R.id.mapTypeSpinner);
        String[] mapTypes = {"Mapa normal", "Mapa de transporte", "Mapa Topográfico"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, mapTypes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mapTypeSpinner.setAdapter(adapter);

        mapTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        mapView.setTileSource(TileSourceFactory.MAPNIK);
                        break;
                    case 1:
                        mapView.setTileSource(new XYTileSource("PublicTransport", 0, 18, 256, ".png", new String[]{
                                "https://tile.memomaps.de/tilegen/"}));
                        break;
                    case 2:
                        mapView.setTileSource(new XYTileSource("USGS_Satellite", 0, 18, 256, ".png", new String[]{
                                "https://a.tile.opentopomap.org/",
                                "https://b.tile.opentopomap.org/",
                                "https://c.tile.opentopomap.org/"}));
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    public void onClickVentana(View view){
        Intent intent = new Intent(this, AccesoAdministrador.class);
        startActivity(intent);
    }
}

