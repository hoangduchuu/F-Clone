package net.hoangduchuu.foody;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ListViewCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
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
    ListView listView;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    List<User> userList;
    AdapterUser adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("users");
        listView = (ListView) findViewById(R.id.lvView);


        userList = new ArrayList<>();


        adapter= new AdapterUser(getApplicationContext(), R.layout.item_user_row, userList);
        listView.setAdapter(adapter);


        databaseReference.addValueEventListener(this);


    }

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        Iterable<DataSnapshot> nodeChild = dataSnapshot.getChildren();
        for (DataSnapshot data : nodeChild) {
            User user = data.getValue(User.class);
            userList.add(user);
            adapter.notifyDataSetChanged();

        }
    }

    @Override
    public void onCancelled(DatabaseError databaseError) {

    }

}
