package net.hoangduchuu.foody;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

import static net.hoangduchuu.foody.R.id.btnUpdate;

public class UpdateUserActivity extends AppCompatActivity implements FirebaseAuth.AuthStateListener {
    Button buttonUpdate, btnShowInfo;
    ImageView imageView;
    EditText edtUserDisplay, edtLinkPhoto;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user);
        buttonUpdate = (Button) findViewById(R.id.btnUpdate);
        btnShowInfo = (Button) findViewById(R.id.btnShowInfo);
        imageView = (ImageView) findViewById(R.id.ivPhoto);
        edtUserDisplay = (EditText) findViewById(R.id.edtUser);
        edtLinkPhoto = (EditText) findViewById(R.id.edtLinkPhoto);
        mAuth = FirebaseAuth.getInstance();

        updateUser();
        showInfo();
    }

    private void showInfo() {
        btnShowInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseUser user = mAuth.getCurrentUser();
                if (user != null) {
                    Log.d("kiemtra - thongtin", user.getDisplayName() + "\n" + user.getPhotoUrl());

                    Glide.with(getApplicationContext())
                            .load(user.getPhotoUrl())
                            .into(imageView);

                } else {
                    Log.d("kiemtra - thongtin", "chuwa dang nhap");
                }
            }
        });
    }

    private void updateUser() {
        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseUser user = mAuth.getCurrentUser();


                UserProfileChangeRequest
                        changeRequest = new UserProfileChangeRequest.Builder()
                        .setDisplayName(edtUserDisplay.getText().toString().trim())
                        .setPhotoUri(Uri.parse(edtLinkPhoto.getText().toString().trim()))
                        .build();

                if (user != null) {
                    user.updateProfile(changeRequest).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Log.d("kiemtra", "thay doi thong tin thanh cong");
                            } else {
                                Log.d("kiemtra", "thay doi thong tin khong thanh cong");

                            }
                        }
                    });
                }
            }
        });
    }

    public void logOut(View view) {
//        mAuth.signOut();
        mAuth.sendPasswordResetEmail("hoangduchuuvn@gmail.com")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d("kiemtra", "da gui mail");
                        } else {
                            Log.d("kiemtra", "da chuwa gui dc mail");

                        }
                    }
                });

    }


    @Override
    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            Log.d("kiemtra", firebaseAuth.getCurrentUser().getEmail());

        } else {
            Toast.makeText(this, "ban da logout", Toast.LENGTH_SHORT).show();
            gotoMainLogin();
        }

    }

    private void gotoMainLogin() {
        startActivity(new Intent(UpdateUserActivity.this, MainActivity.class));
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
