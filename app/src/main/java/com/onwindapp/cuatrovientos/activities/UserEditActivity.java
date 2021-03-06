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

public class UserEditActivity extends AppCompatActivity {
    Realm realm;
    EditText name;
    EditText surname;
    EditText mail;
    EditText password;
    EditText phone;
    TextView result;
    Button edit;
    Users user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_edit);
        realm = Realm.getDefaultInstance();
        name = findViewById(R.id.name);
        surname = findViewById(R.id.surname);
        mail = findViewById(R.id.email);
        password = findViewById(R.id.password);
        phone = findViewById(R.id.phone);
        result = findViewById(R.id.result);
        Bundle bundle = getIntent().getExtras();
        edit = findViewById(R.id.edit);
        user = realm.where(Users.class).equalTo("id", CommonData.currentUser.getId()).findFirst();
        //load data of the user
        name.setText(user.getName());
        surname.setText(user.getSurname());
        mail.setText(user.getMail());
        mail.setEnabled(false);
        phone.setText(user.getTelephone());
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                realm.beginTransaction();
                if (!name.getText().toString().isEmpty()){
                    user.setName(name.getText().toString());
                }
                if (!surname.getText().toString().isEmpty()){
                    user.setSurname(surname.getText().toString());
                }
                if (!mail.getText().toString().isEmpty()){
                    user.setMail(mail.getText().toString());
                }
//                if (!password.getText().toString().isEmpty()){
//                    user.setPassword(password.getText().toString());
//                }
                if (!phone.getText().toString().isEmpty()){
                    user.setTelephone(phone.getText().toString());
                }
                realm.commitTransaction();
                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        CommonData.currentUser = realm.where(Users.class).equalTo("id", user.getId()).findFirst();
                    }
                });
                Intent intent = new Intent(UserEditActivity.this, UserInfoActivity.class);
                startActivity(intent);
            }
        });
    }
}