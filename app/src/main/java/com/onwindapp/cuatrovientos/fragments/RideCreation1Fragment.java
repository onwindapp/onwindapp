package com.onwindapp.cuatrovientos.fragments;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.onwindapp.cuatrovientos.R;
import com.onwindapp.cuatrovientos.models.Ride;
import com.onwindapp.cuatrovientos.models.RidesTypes;
import com.onwindapp.cuatrovientos.models.Users;
import com.onwindapp.cuatrovientos.utils.CommonData;

import java.security.PrivateKey;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.Calendar;
import java.util.Date;

import io.realm.RealmList;

public class RideCreation1Fragment extends Fragment {
    private Ride ride;
    private CheckBox chkAccept;
    EditText date, time, place, details;
    TextView cPlace, cDetails, cDate, cTime, globalErrorNote, title;
    Switch type;
    ArrayAdapter<String> seatsAvailableAdapter;
    Spinner seatsAvailable;
    Boolean dataLoaded = Boolean.FALSE;
    public RideCreation1Fragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ride_creation1, container, false);
        seatsAvailable = (Spinner) view.findViewById(R.id.seatsAvailable);
        time = (EditText) view.findViewById(R.id.timeSelector);
        date = (EditText) view.findViewById(R.id.dateselector);
        place = (EditText) view.findViewById(R.id.place);
        title = (TextView) view.findViewById(R.id.title);
        cPlace = (TextView) view.findViewById(R.id.checkPlace);
        cDetails = (TextView) view.findViewById(R.id.checkDetails);
        cDate = (TextView) view.findViewById(R.id.checkDate);
        cTime = (TextView) view.findViewById(R.id.checkTime);
        globalErrorNote = (TextView) view.findViewById(R.id.globalErrorNote);
        details = (EditText) view.findViewById(R.id.details);
        chkAccept = (CheckBox) view.findViewById(R.id.chkAccept);
        type = (Switch) view.findViewById(R.id.type);
        date.setInputType(InputType.TYPE_NULL);
        time.setInputType(InputType.TYPE_NULL);




        chkAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (chkAccept.isChecked()){
                    String check = "";
                    RidesTypes rideType = RidesTypes.Ida;
                    int availablePlaces = Integer.parseInt(seatsAvailable.getSelectedItem().toString());
                    if (type.isChecked()){
                        rideType = RidesTypes.Vuelta;
                    }
                    if (place.getText().toString().equals("")){
                        cPlace.setVisibility(View.VISIBLE);
                        check = "e";
                    }
                    if (details.getText().toString().equals("")){
                        cDetails.setVisibility(View.VISIBLE);
                        check = "e";
                    }
                    if (time.getText().toString().equals("")){
                        cTime.setVisibility(View.VISIBLE);
                        check = "e";
                    }
                    if (date.getText().toString().equals("")){
                        cDate.setVisibility(View.VISIBLE);
                        check = "e";
                    }
                    if (check.equals("e")){
                        chkAccept.toggle();
                        globalErrorNote.setVisibility(View.VISIBLE);
                        someEventListener.someEvent(null);
                        return;
                    }
                    if (CommonData.editMode == Boolean.FALSE){
                        RealmList<Double> initCords = new RealmList<Double>();
                        initCords.add(0.0);
                        initCords.add(0.0);
                        someEventListener.someEvent(new Ride(rideType, place.getText().toString(), initCords, availablePlaces, details.getText().toString(), date.getText().toString() + " " + time.getText().toString(), new Users()));
                    } else{
                        CommonData.editRide.setName(place.getText().toString());
                        CommonData.editRide.setDescription(details.getText().toString());
                        CommonData.editRide.setDateTime(date.getText().toString() + " " + time.getText().toString());
                        CommonData.editRide.setAvailablePlaces(Integer.parseInt(seatsAvailable.getSelectedItem().toString()));
                        if (type.isChecked()){
                            CommonData.editRide.setRideType(RidesTypes.Vuelta.toString());
                        } else {
                            CommonData.editRide.setRideType(RidesTypes.Ida.toString());
                        }
                        someEventListener.someEvent(new Ride("edit"));
                    }

                } else {
                    someEventListener.someEvent(null);
                }
            }
        });

        type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (chkAccept.isChecked()){
                    someEventListener.hideNextButton(Boolean.TRUE);
                    chkAccept.toggle();
                }
            }
        });

        place.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (chkAccept.isChecked()){
                    someEventListener.hideNextButton(Boolean.TRUE);
                    chkAccept.toggle();
                }
                cPlace.setVisibility(View.INVISIBLE);
                if (cDetails.getVisibility() == View.INVISIBLE && cDate.getVisibility() == View.INVISIBLE && cTime.getVisibility() == View.INVISIBLE ) globalErrorNote.setVisibility(View.INVISIBLE);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        details.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (chkAccept.isChecked()){
                    someEventListener.hideNextButton(Boolean.TRUE);
                    chkAccept.toggle();
                }
                cDetails.setVisibility(View.INVISIBLE);
                if (cPlace.getVisibility() == View.INVISIBLE && cDate.getVisibility() == View.INVISIBLE && cTime.getVisibility() == View.INVISIBLE ) globalErrorNote.setVisibility(View.INVISIBLE);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        date.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (chkAccept.isChecked()){
                    someEventListener.hideNextButton(Boolean.TRUE);
                    chkAccept.toggle();
                }
                cDate.setVisibility(View.INVISIBLE);
                if (cPlace.getVisibility() == View.INVISIBLE && cDetails.getVisibility() == View.INVISIBLE && cTime.getVisibility() == View.INVISIBLE ) globalErrorNote.setVisibility(View.INVISIBLE);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        time.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (chkAccept.isChecked()){
                    someEventListener.hideNextButton(Boolean.TRUE);
                    chkAccept.toggle();
                }
                cTime.setVisibility(View.INVISIBLE);
                if (cPlace.getVisibility() == View.INVISIBLE && cDetails.getVisibility() == View.INVISIBLE && cDate.getVisibility() == View.INVISIBLE ) globalErrorNote.setVisibility(View.INVISIBLE);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimeDialog(time);
            }
        });
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog(date);
            }
        });
        seatsAvailableAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_expandable_list_item_1, getResources().getStringArray(R.array.seats));
        seatsAvailableAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        seatsAvailableAdapter.notifyDataSetChanged();
        seatsAvailable.setAdapter(seatsAvailableAdapter);


        return view;
    }

    private void showTimeDialog(final EditText time_in) {
        final Calendar calendar= Calendar.getInstance();

        TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                calendar.set(Calendar.HOUR_OF_DAY,hourOfDay);
                calendar.set(Calendar.MINUTE,minute);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
                time_in.setText(simpleDateFormat.format(calendar.getTime()));
            }
        };

        new TimePickerDialog(getActivity(),timeSetListener,calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),true).show();
    }


    private void showDateDialog(final EditText date_in) {
        final Calendar calendar = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR,year);
                calendar.set(Calendar.MONTH,month);
                calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
                date_in.setText(simpleDateFormat.format(calendar.getTime()));

            }
        };Calendar.getInstance().get(Calendar.YEAR);
        new DatePickerDialog(getActivity(),dateSetListener,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    public interface onSomeEventListener {
        public void someEvent(Ride ride);
        public void hideNextButton(Boolean hide);
    }

    onSomeEventListener someEventListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            someEventListener = (onSomeEventListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement onSomeEventListener");
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        seatsAvailable.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if (chkAccept.isChecked()){
                    someEventListener.hideNextButton(Boolean.TRUE);
                    chkAccept.toggle();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {}
        });
        if (CommonData.editMode == Boolean.TRUE && dataLoaded == Boolean.FALSE){
            title.setText("Editar Viaje");
            place.setText(CommonData.editRide.getName());
            details.setText(CommonData.editRide.getDescription());
            String[]dateTime = CommonData.editRide.getDateTime().split(" ");
            date.setText(dateTime[0]);
            time.setText(dateTime[1]);
            seatsAvailable.setSelection(CommonData.editRide.getAvailablePlaces() - 1);
            if (CommonData.editRide.getRideType().toString().equals("Vuelta")) type.toggle();
            dataLoaded = Boolean.TRUE;
        }
    }


}