package com.onwindapp.cuatrovientos.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.onwindapp.cuatrovientos.R;
import com.onwindapp.cuatrovientos.models.Users;
import com.onwindapp.cuatrovientos.utils.CommonData;

import io.realm.Realm;
import io.realm.RealmResults;

public class UserInfoActivity extends AppCompatActivity {
    Realm realm;
    TextView name;
    TextView mail;
    TextView phone;
    TextView puntuacion;
    TextView puntosco2;
    Button edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        realm = Realm.getDefaultInstance();
        name = findViewById(R.id.name);
        mail = findViewById(R.id.email);
        phone = findViewById(R.id.phone);
        puntuacion = findViewById(R.id.puntucion);
        puntosco2 = findViewById(R.id.puntosco2);
        Bundle bundle = getIntent().getExtras();
        edit = findViewById(R.id.edit);
        name.setText(CommonData.currentUser.getName()+" "+CommonData.currentUser.getSurname());
        mail.setText(CommonData.currentUser.getMail());
        phone.setText(CommonData.currentUser.getTelephone());
        puntuacion.setText(CommonData.currentUser.getPunctuation()+"");
        puntosco2.setText(CommonData.currentUser.getCO2points()+"");
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserInfoActivity.this, UserEditActivity.class);
                startActivity(intent);
            }
        });
    }
}