package net.hoangduchuu.foody;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements FirebaseAuth.AuthStateListener {

    FirebaseAuth mAuth;
    EditText edtMail;
    EditText edtPass;
    Button btnDangNhap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        edtMail = (EditText) findViewById(R.id.edtMail);
        edtPass = (EditText) findViewById(R.id.edtPass);
        btnDangNhap = (Button) findViewById(R.id.btnDangNhap);
//        mAuth.signOut();


//        create();
        login();

    }

    private void login() {

        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mail = edtMail.getText().toString().trim();
                String pass = edtPass.getText().toString().trim();

                mAuth.signInWithEmailAndPassword(mail, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d("kiemtra", "dang nhap thanh cong");
                        } else {
                            Log.d("kiemtra", "dang nhap that bai");

                        }
                    }


                });
            }
        });
    }

    private void gotoUpdateUserActivity() {
        startActivity(new Intent(MainActivity.this, UpdateUserActivity.class));
    }

    private void create() {
        mAuth.createUserWithEmailAndPassword("hoangduchuuvn@gmail.com", "123456789").addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Log.d("kiemtra", "tao thanh cong");
                } else {
                    Log.d("kiemtra", "tao khong thanh cong" + task.getException().getMessage().toString());
                }
            }
        });

    }


    @Override
    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            Log.d("kiemtra", firebaseAuth.getCurrentUser().getEmail());
            gotoUpdateUserActivity();

        } else {
            Toast.makeText(this, "dang nhap that bai", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mAuth.removeAuthStateListener(this);
    }
}
