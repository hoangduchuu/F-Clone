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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("users");


        Map<String, Object> valueUpdates = new HashMap<>();
        valueUpdates.put("gioitinh", false);
        valueUpdates.put("hoten", "Nong Duc manh hai");


        Map<String, Object> keyUpdates = new HashMap<>();
        keyUpdates.put("user1", valueUpdates);

        databaseReference.updateChildren(keyUpdates);



//        databaseReference.setValue(true);
        btnThemDuLieu = (Button) findViewById(R.id.btnThemDuLieu);
        tvHienThi = (TextView) findViewById(R.id.tvHienThi);

        databaseReference.addValueEventListener(this);

        btnThemDuLieu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                databaseReference.child("ccc").setValue("kaka");
//
//                User user = new User("Cong Hoang" , false, "33");
//                User user2 = new User("Khanh Hoang" , false, "43");
//
//                List<User> userList = new ArrayList<User>();
//                userList.add(user);
//
//                userList.add(user2);
//                databaseReference.child("users").child("user4").setValue(userList);


            }
        });


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
