package net.hoangduchuu.foody.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import net.hoangduchuu.foody.R;

public class HomePageActivity extends AppCompatActivity {
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();

        TextView textView = (TextView) findViewById(R.id.tvHelo);

        textView.setText(user.getEmail().toString());
    }
}
