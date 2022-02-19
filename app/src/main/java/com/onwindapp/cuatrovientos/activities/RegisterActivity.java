package com.onwindapp.cuatrovientos.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.onwindapp.cuatrovientos.R;
import com.onwindapp.cuatrovientos.models.Users;
import com.onwindapp.cuatrovientos.utils.CommonData;

import io.realm.Realm;
import io.realm.RealmResults;

public class RegisterActivity extends AppCompatActivity {
    RealmResults<Users> realmUserList;
    Realm realm;
    EditText name;
    EditText surname;
    EditText mail;
    EditText password;
    EditText phone;
    TextView result;
    Button register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        realm = Realm.getDefaultInstance();
        name = (EditText) findViewById(R.id.name);
        surname = (EditText) findViewById(R.id.surname);
        mail = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        phone = (EditText) findViewById(R.id.phone);
        result = findViewById(R.id.result);
        Bundle bundle = getIntent().getExtras();
        register = (Button) findViewById(R.id.register);
        mail.setText(bundle.getString("mail"));
        realmUserList = realm.where(Users.class).findAll();
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (name.getText().toString().isEmpty()||surname.getText().toString().isEmpty()||mail.getText().toString().isEmpty()||password.getText().toString().isEmpty()||phone.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Complete todos los campos para poder registrarte", Toast.LENGTH_LONG);
                }else {
                    Users rookie = new Users(name.getText().toString(), surname.getText().toString(), password.getText().toString(), mail.getText().toString(), phone.getText().toString());
                    int location = realmUserList.indexOf(rookie);
                    if (location > -1){
                        Toast.makeText(getApplicationContext(), "Este correo ya está registrado", Toast.LENGTH_LONG);
                    }
                    else {
                        realm.beginTransaction();
                        realm.copyToRealm(rookie);
                        realm.commitTransaction();
                        realm.executeTransaction(new Realm.Transaction() {
                            @Override
                            public void execute(Realm realm) {
                                CommonData.currentUser = realm.where(Users.class).equalTo("id", rookie.getId()).findFirst();
                            }
                        });
                        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                }
            }
        });
    }
}