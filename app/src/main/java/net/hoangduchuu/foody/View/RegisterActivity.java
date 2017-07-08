package net.hoangduchuu.foody.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;

import net.hoangduchuu.foody.R;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnRegister;
    FirebaseAuth mAuth;
    EditText edtMail, edtPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
        btnRegister = (Button) findViewById(R.id.btnRegister);
        edtMail = (EditText) findViewById(R.id.edtEmail);
        edtPass = (EditText) findViewById(R.id.edtPassword);
        btnRegister.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
    }
}
