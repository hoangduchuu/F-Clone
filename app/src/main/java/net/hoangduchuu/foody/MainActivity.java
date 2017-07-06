package net.hoangduchuu.foody;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ChildEventListener {
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


        adapter = new AdapterUser(getApplicationContext(), R.layout.item_user_row, userList);
        listView.setAdapter(adapter);

        Query query = databaseReference.orderByKey();

        query.addChildEventListener(this);


    }


    @Override
    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
        User user = dataSnapshot.getValue(User.class);
        userList.add(user);
        adapter.notifyDataSetChanged();
        Log.d("kiemtra", "added");
    }

    @Override
    public void onChildChanged(DataSnapshot dataSnapshot, String s) {
        Log.d("kiemtra", "onChildChanged");
        refreshList();
    }

    @Override
    public void onChildRemoved(DataSnapshot dataSnapshot) {
        Log.d("kiemtra", "onChildRemoved");
        refreshList();


    }

    @Override
    public void onChildMoved(DataSnapshot dataSnapshot, String s) {
        Log.d("kiemtra", "onChildMoved");
        refreshList();

    }

    @Override
    public void onCancelled(DatabaseError databaseError) {
        Log.d("kiemtra", "onCancelled");
        refreshList();


    }

    private void refreshList() {
        userList.clear();
        databaseReference.removeEventListener(this);
        databaseReference.addChildEventListener(this);
    }
}
