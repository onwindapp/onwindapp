package com.onwindapp.cuatrovientos.maps;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.onwindapp.cuatrovientos.R;
import com.onwindapp.cuatrovientos.activities.MainActivity;
import com.onwindapp.cuatrovientos.activities.RideCreationActivity;
import com.onwindapp.cuatrovientos.fragments.RouteMapsFragment;
import com.onwindapp.cuatrovientos.models.Ride;
import com.onwindapp.cuatrovientos.utils.CommonData;

import io.realm.Realm;

public class RouteActivity extends AppCompatActivity {
    private Realm realm;
    private Ride ride;
    private TextView txtTitulo, txtDescripcion, txtDataTipo, txtDataHora, txtDataPlazas, txtDataConductor;
    private Button btnTerminarViaje, btnEditar;
    private FloatingActionButton btnUnirse;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route);

        txtTitulo = (TextView) findViewById(R.id.txtDireccion);
        txtDescripcion = (TextView) findViewById(R.id.txtDescripccion);
        btnEditar = (Button) findViewById(R.id.btnEditar);
        btnUnirse = (FloatingActionButton) findViewById(R.id.btnUnirse);
        btnTerminarViaje = (Button) findViewById(R.id.btnTerminar);
        txtDataTipo = (TextView) findViewById(R.id.txtDataTipo);
        txtDataHora = (TextView) findViewById(R.id.txtDataHora);
        txtDataPlazas = (TextView) findViewById(R.id.txtDataPlazas);
        txtDataConductor = (TextView) findViewById(R.id.txtDataConductor);

        btnEditar.setVisibility(View.GONE);
        btnTerminarViaje.setVisibility(View.GONE);

        RouteMapsFragment routeMapsFragment = (RouteMapsFragment) getSupportFragmentManager().findFragmentById(R.id.mapContainer);

        Bundle bundle = getIntent().getExtras();
        realm = Realm.getDefaultInstance();
        // get the selected ride
        realm.executeTransaction(realm -> ride = realm.where(Ride.class).equalTo("id", bundle.getInt("id")).findFirst());

        txtTitulo.setText(ride.getName());
        txtDescripcion.setText(ride.getDescription());
        txtDataTipo.setText(String.format("Viaje de:%s", ride.getRideType().toString()));
        txtDataHora.setText(ride.getDateTime());
        txtDataPlazas.setText(String.format("%s", ride.getAvailablePlaces()));
        txtDataConductor.setText(ride.getDriver().getName());


        if (CommonData.currentUser.getId() == ride.getDriver().getId() && !ride.getFinished()) {
            btnEditar.setVisibility(View.VISIBLE);
            btnTerminarViaje.setVisibility(View.VISIBLE);
            btnEditar.setOnClickListener(v -> {
                Intent intent = new Intent(RouteActivity.this, RideCreationActivity.class);
                intent.putExtra("id", Integer.toString(ride.getId()));
                startActivity(intent);
            });
            btnTerminarViaje.setOnClickListener(v ->{
                realm.executeTransaction(realmFinish ->{
                    ride.finishRide();
                    float newPoints = (float) ((ride.getUsersJoined().size() + 1) * 5.3);
                    ride.getUsersJoined().forEach(users -> users.setCO2points(newPoints));
                    realm.copyToRealmOrUpdate(ride);
                });
            });
        }

        if (ride.getAvailablePlaces() < 1 || ride.getUsersJoined().contains(CommonData.currentUser) || ride.getFinished()) {
            btnUnirse.setEnabled(false);
        } else {
            btnUnirse.setOnClickListener(v -> realm.executeTransaction(realm -> {
            ride.addPersonToRide(CommonData.currentUser);
            realm.copyToRealmOrUpdate(ride);
            Toast.makeText(this, "Te has unido al viaje", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(RouteActivity.this, MainActivity.class));
            }));

        }
        routeMapsFragment.setData(ride);

    }
}