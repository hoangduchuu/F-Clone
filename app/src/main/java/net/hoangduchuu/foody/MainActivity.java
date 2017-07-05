package net.hoangduchuu.foody;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements ValueEventListener {
    Button btnThemDuLieu;
    TextView tvHienThi;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    int dem = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("users");

        databaseReference.runTransaction(new Transaction.Handler() {
            @Override
            public Transaction.Result doTransaction(MutableData mutableData) {

                User user = mutableData.getValue(User.class);
                if (user == null){
                    return Transaction.success(mutableData);
                }else{
                    Log.d("kiemtra", "else cmnr" + "- " + mutableData.toString());
                    mutableData.setValue(user);
                }

                Log.d("kiemtra", "do" + "- " + mutableData.toString());
                return Transaction.success(mutableData);
            }

            @Override
            public void onComplete(DatabaseError databaseError, boolean b, DataSnapshot dataSnapshot) {
                Log.d("kiemtra", "onComplete");

            }
        });


//        databaseReference.setValue(true);
        btnThemDuLieu = (Button) findViewById(R.id.btnThemDuLieu);
        tvHienThi = (TextView) findViewById(R.id.tvHienThi);


    }

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
//        Log.d("kiemtra", dataSnapshot.getKey().toString());
//        Log.d("kiemtra2", dataSnapshot.getValue().toString());
//        Log.d("kiemtra3", dataSnapshot.toString());
//
//        Iterable<DataSnapshot> nodeChild_Users = (Iterable<DataSnapshot>) dataSnapshot.getChildren();
//
//        for (DataSnapshot dataSnapshot1 : nodeChild_Users) {
//            User user = dataSnapshot1.getValue(User.class);
//
////            Log.d("kiemtra4", dataSnapshot1.toString());
//            Log.d("kiemtra5", user.getHoten().toString());
//
//
//        }


    }

    @Override
    public void onCancelled(DatabaseError databaseError) {

    }
}

class User {

    String hoten;
    boolean gioitinh;
    Long tuoi;

    public User() {
    }

    public User(String hoten, boolean gioitinh, Long tuoi) {
        this.hoten = hoten;
        this.gioitinh = gioitinh;
        this.tuoi = tuoi;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public boolean isGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(boolean gioitinh) {
        this.gioitinh = gioitinh;
    }

    public Long getTuoi() {
        return tuoi;
    }

    public void setTuoi(Long tuoi) {
        this.tuoi = tuoi;
    }
}
