package net.hoangduchuu.foody.View;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import net.hoangduchuu.foody.R;

public class ForgotPasswordActivity extends AppCompatActivity implements View.OnClickListener {
    EditText edtEmail;
    Button btnSendMail;
    FirebaseAuth mAuth;
    ProgressBar pgLoading;
    TextView tvRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        mAuth = FirebaseAuth.getInstance();
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        btnSendMail = (Button) findViewById(R.id.btnSendMail);
        pgLoading = (ProgressBar) findViewById(R.id.pgLoading);
        tvRegister = (TextView) findViewById(R.id.tvRegister);

        tvRegister.setOnClickListener(this);
        btnSendMail.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btnSendMail:
                pgLoading.setVisibility(View.VISIBLE);
                sendMailReset();
                break;
            case R.id.tvRegister:
                startActivity(new Intent(ForgotPasswordActivity.this, RegisterActivity.class));
                break;
        }
    }

    private void sendMailReset() {
        pgLoading.setVisibility(View.GONE);
        if (edtEmail.length() > 0) {
            pgLoading.setVisibility(View.VISIBLE);
            mAuth.sendPasswordResetEmail(edtEmail.getText().toString().trim()).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        pgLoading.setVisibility(View.GONE);
                        Toast.makeText(ForgotPasswordActivity.this, "Da gui mail, vui long check", Toast.LENGTH_SHORT).show();
                    } else {
                        pgLoading.setVisibility(View.GONE);
                        Toast.makeText(ForgotPasswordActivity.this, "" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }else {
            Toast.makeText(this, "vui long nhap email", Toast.LENGTH_SHORT).show();
        }
    }
}
