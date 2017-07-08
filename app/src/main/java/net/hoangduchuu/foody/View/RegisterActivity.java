package net.hoangduchuu.foody.View;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import net.hoangduchuu.foody.R;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener, FirebaseAuth.AuthStateListener {
    Button btnRegister;
    FirebaseAuth mAuth;
    EditText edtMail, edtPass, edtPass2;
    ProgressBar pgLoading;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
        btnRegister = (Button) findViewById(R.id.btnRegister);
        edtMail = (EditText) findViewById(R.id.edtEmail);
        edtPass = (EditText) findViewById(R.id.edtPassword);
        edtPass2 = (EditText) findViewById(R.id.edtPassword2);
        pgLoading = (ProgressBar) findViewById(R.id.pgLoading);
        btnRegister.setOnClickListener(this);


    }

    private void register() {
        String mail = edtMail.getText().toString().trim();
        String pwd = edtPass.getText().toString().trim();
        String pwd2 = edtPass2.getText().toString().trim();
        if (mail.length() < 1) {
            Toast.makeText(this, "nhap email", Toast.LENGTH_SHORT).show();
        } else if (pwd.length() < 1 || pwd2.length() < 1) {
            Toast.makeText(this, "nhap pass", Toast.LENGTH_SHORT).show();
        } else if (!pwd.equals(pwd2)) {
            Toast.makeText(this, "hai pass khac nhau", Toast.LENGTH_SHORT).show();
        } else {
            pgLoading.setVisibility(View.VISIBLE);
            mAuth.createUserWithEmailAndPassword(mail, pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    FirebaseUser user = mAuth.getCurrentUser();
                    if (task.isSuccessful()) {
                        pgLoading.setVisibility(View.GONE);
                        Toast.makeText(RegisterActivity.this, "dang nhap thanh cong, xin chao:  " + user.getEmail(), Toast.LENGTH_SHORT).show();
                    } else {
                        pgLoading.setVisibility(View.GONE);
                        Toast.makeText(RegisterActivity.this, "Dang nhap that bai: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }


    @Override
    public void onClick(View view) {
        register();
    }

    @Override
    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            Toast.makeText(this, "ban da dang nhap", Toast.LENGTH_SHORT).show();
        } else {
            Log.d("kiemtra", " chua co dang nhap");
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        mAuth.removeAuthStateListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(this);
    }
}
