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
    Users user;

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
        user = realm.where(Users.class).equalTo("id", bundle.getString("user")).findFirst();
        name.setText(user.getName()+" "+user.getSurname());
        mail.setText(user.getMail());
        phone.setText(user.getTelephone());
        puntuacion.setText(user.getPunctuation()+"");
        puntosco2.setText(user.getCO2points()+"");
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserInfoActivity.this, UserEditActivity.class);
                intent.putExtra("user", bundle.getString("user"));
                startActivity(intent);
            }
        });
    }
}