package com.onwindapp.cuatrovientos.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.onwindapp.cuatrovientos.R;
import com.onwindapp.cuatrovientos.adapters.RideCreationAdapter;
import com.onwindapp.cuatrovientos.fragments.RideCreation1Fragment;
import com.onwindapp.cuatrovientos.fragments.RouteMapsFragment;
import com.onwindapp.cuatrovientos.fragments.SelectionMapFragment;
import com.onwindapp.cuatrovientos.models.Ride;
import com.onwindapp.cuatrovientos.models.RidesTypes;
import com.onwindapp.cuatrovientos.utils.CommonData;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

public class RideCreationActivity extends AppCompatActivity implements RideCreation1Fragment.onSomeEventListener {
    ViewPager2 pager;
    RideCreationAdapter adapter;
    LinearLayout mDotLayout;
    TextView[] mDots;
    Boolean firstInit = Boolean.TRUE;
    Button btnNext, btnBack;
    Boolean readyToConfirm = Boolean.FALSE;
    String markerInfo;
    Ride tmpRide, rideToEdit;
    String data;
    Realm realm;
    int nCurrentPage, numb;
    RealmList<Double> tempCords;
    SelectionMapFragment selectionMapFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ride_creation);
        pager = findViewById(R.id.view_pager_ride_creation);
        mDotLayout = (LinearLayout) findViewById(R.id.dotsLayout);
        btnNext = (Button) findViewById(R.id.next);
        btnBack = (Button) findViewById(R.id.back);
        realm = Realm.getDefaultInstance();

        // TODO: 21/02/2022 utiliza el bundel 
        Intent i = getIntent();
        String str = i.getStringExtra("id");
        int id = Integer.parseInt(str);
        if (id != -1){
            CommonData.editMode = Boolean.TRUE;

            realm.executeTransaction(realm -> rideToEdit = realm.where(Ride.class).equalTo("id", id).findFirst());
            CommonData.editRide = new Ride();
            CommonData.editRide.setId(rideToEdit.getId());
            CommonData.editRide.setName(rideToEdit.getName());
            CommonData.editRide.setDescription(rideToEdit.getDescription());
            CommonData.editRide.setPoint(rideToEdit.getPoint());
            tempCords = rideToEdit.getPoint();
            CommonData.editRide.setDateTime(rideToEdit.getDateTime());
            CommonData.editRide.setDriver(rideToEdit.getDriver());
            CommonData.editRide.setAvailablePlaces(rideToEdit.getAvailablePlaces());
            CommonData.editRide.setRideType(rideToEdit.getRideType().toString());
            CommonData.editRide.setUsersJoined(rideToEdit.getUsersJoined());
        }

        FragmentManager fm = getSupportFragmentManager();
        adapter = new RideCreationAdapter(fm, getLifecycle());
        pager.setAdapter(adapter);
        pager.setUserInputEnabled(false);
        addDotsIndicator(0);


        // TODO: 19/02/2022 send ride type to selection map

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pager.setCurrentItem(nCurrentPage + 1);
                if (readyToConfirm == Boolean.TRUE) {
                    if (CommonData.editMode == Boolean.FALSE){
                        CommonData.createRide.setDriver(CommonData.currentUser);
                        realm.beginTransaction();
                        realm.insertOrUpdate(CommonData.createRide);
                        realm.commitTransaction();
                        Toast.makeText(getApplicationContext(), "Viaje Creado",Toast.LENGTH_SHORT).show();
                        finish();
                    } else {

                        realm.executeTransaction(realm1 -> {
                            Ride tmpRide = realm.where(Ride.class).equalTo("id", CommonData.editRide.getId()).findFirst();
//                            RealmList<Double> tempCord = CommonData.editRide.getPoint().size() == 0 ? tempCords : CommonData.editRide.getPoint();
                            if (tmpRide != null){
                                tmpRide.setRideType(CommonData.editRide.getRideType().toString());
                                tmpRide.setName(CommonData.editRide.getName());
                                tmpRide.setDescription(CommonData.editRide.getDescription());
//                                tmpRide.setPoint(tempCord);
                                tmpRide.setDateTime(CommonData.editRide.getDateTime());
                                tmpRide.setAvailablePlaces(CommonData.editRide.getAvailablePlaces());
                                tmpRide.setDriver(CommonData.editRide.getDriver());
                                tmpRide.setUsersJoined(CommonData.editRide.getUsersJoined());
                                realm.copyToRealmOrUpdate(tmpRide);
                            }

                        });

                        CommonData.editRide = null;
                        Toast.makeText(getApplicationContext(),"Viaje Editado",Toast.LENGTH_SHORT).show();
                        CommonData.editMode =  Boolean.FALSE;
                        finish();
                    }

                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
                if (btnNext.getText() == "Confirmar") readyToConfirm = Boolean.TRUE;
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pager.setCurrentItem(nCurrentPage - 1);
                readyToConfirm = Boolean.FALSE;
            }
        });

        pager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                addDotsIndicator(position);
                nCurrentPage = position;

                if(position == 0){
                    btnBack.setEnabled(false);
                    btnNext.setEnabled(true);
                    btnBack.setVisibility(View.INVISIBLE);
                } else if (position == 2){
                    btnBack.setEnabled(true);
                    btnNext.setEnabled(true);
                    btnBack.setVisibility(View.VISIBLE);

                    btnBack.setText("Atr??s");
                    btnNext.setText("Confirmar");
                } else {
                    int pr = pager.getCurrentItem();
                    // selection map
//                    selectionMapFragment = (SelectionMapFragment) getSupportFragmentManager()
//                            .findFragmentByTag("f" + pager.getCurrentItem());
//                    selectionMapFragment.LoadData(tmpRide);
                    btnBack.setEnabled(true);
                    btnNext.setEnabled(true);
                    btnBack.setVisibility(View.VISIBLE);

                    btnBack.setText("Atr??s");
                    btnNext.setText("Siguiente");
                }
            }
        });
    }
    public void addDotsIndicator(int position){
        mDots = new TextView[3];
        mDotLayout.removeAllViews();

        for (int i = 0; i < mDots.length; i++){
            mDots[i] = new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226;"));
            mDots[i].setTextSize(55);
            mDots[i].setTextColor(getResources().getColor(R.color.transparentWhite));

            mDotLayout.addView(mDots[i]);
        }
        if (mDots.length > 0){
            mDots[position].setTextColor(getResources().getColor(R.color.white));
        }
    }
    @Override
    public void someEvent(Ride ride) {
        CommonData.createRide = ride;
        if (ride == null){
            btnNext.setVisibility(View.INVISIBLE);
        } else {
            btnNext.setVisibility(View.VISIBLE);
            CommonData.createRide = ride;
        }
    }

    public void hideNextButton(Boolean hide) {
        if (hide){
            btnNext.setVisibility(View.INVISIBLE);
        } else {
            btnNext.setVisibility(View.VISIBLE);
        }
    }

    public void onDestroy() {
        super.onDestroy();
        CommonData.editMode =  Boolean.FALSE;
        finish();
    }
}