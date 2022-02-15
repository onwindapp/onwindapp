package com.onwindapp.cuatrovientos.maps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.onwindapp.cuatrovientos.R;
import com.onwindapp.cuatrovientos.activities.MainActivity;
import com.onwindapp.cuatrovientos.fragments.MapsFragment;
import com.onwindapp.cuatrovientos.models.Ride;
import com.onwindapp.cuatrovientos.utils.CommonData;

import java.util.List;

import io.realm.Realm;

public class RouteActivity extends AppCompatActivity {
    private Realm realm;
    private Ride ride;
    private TextView txtTitulo, txtDescripcion;
    private Button btnEditar, btnUnirse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route);

        txtTitulo = (TextView) findViewById(R.id.txtDireccion);
        txtDescripcion = (TextView) findViewById(R.id.txtDescripccion);
        btnEditar = (Button) findViewById(R.id.btnEditar);
        btnUnirse = (Button) findViewById(R.id.btnUnirse);

        btnEditar.setVisibility(View.GONE);

        MapsFragment mapsFragment = (MapsFragment) getSupportFragmentManager().findFragmentById(R.id.mapContainer);

        Bundle bundle = getIntent().getExtras();
        realm = Realm.getDefaultInstance();
        // get the selected ride
        realm.executeTransaction(realm -> ride = realm.where(Ride.class).equalTo("id", bundle.getInt("id")).findFirst());

        txtTitulo.setText(ride.getName());
        txtDescripcion.setText(ride.getDescription());

        if (CommonData.currentUser.getId() == ride.getDriver().getId()) {
            btnEditar.setVisibility(View.VISIBLE);
            btnEditar.setOnClickListener(v -> {
                Intent intent = new Intent(RouteActivity.this, MainActivity.class);
                startActivity(intent);
            });
        }

        if (ride.getAvailablePlaces() < 1 || ride.getUsersJoined().contains(CommonData.currentUser)) {
            btnUnirse.setEnabled(false);
        } else {
            btnUnirse.setOnClickListener(v -> realm.executeTransaction(realm -> {
            ride.addPersonToRide(CommonData.currentUser);
            realm.copyToRealmOrUpdate(ride);
            }));
            Toast.makeText(this, "Te has unido al viaje", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(RouteActivity.this, MainActivity.class));
        }
        mapsFragment.setData(ride);

    }
}