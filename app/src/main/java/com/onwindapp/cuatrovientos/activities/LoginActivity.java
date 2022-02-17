package com.onwindapp.cuatrovientos.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.onwindapp.cuatrovientos.R;
import com.onwindapp.cuatrovientos.models.Users;

import io.realm.Realm;
import io.realm.RealmResults;

public class LoginActivity extends AppCompatActivity {
    RealmResults<Users> realmUserList;
    Realm realm;
    Button login;
    Button register;
    EditText email;
    EditText password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        realm = Realm.getDefaultInstance();
        login = (Button) findViewById(R.id.login);
        register = (Button) findViewById(R.id.register);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        realmUserList = realm.where(Users.class).findAll();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (email.getText().toString().isEmpty() || password.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Los campos correo y contraseña tienen que ser rellenados", Toast.LENGTH_LONG);
                }else {
                    Users user = realm.where(Users.class).equalTo("mail", email.getText().toString()).findFirst();
                    if (user == null){
                        Toast.makeText(getApplicationContext(), "No se hay nadie registrado con ese correo", Toast.LENGTH_LONG);
                    }
                    else {
                        if (user.getPassword().equals(password.getText().toString())){
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            intent.putExtra("user", user.getId());
                            startActivity(intent);
                        }else {
                            Toast.makeText(getApplicationContext(), "Contraseña incorrecta", Toast.LENGTH_LONG);
                        }
                    }
                }
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                intent.putExtra("mail", email.getText().toString());
                startActivity(intent);
            }
        });
    }
}