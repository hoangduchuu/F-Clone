package net.hoangduchuu.foody.View;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import net.hoangduchuu.foody.R;

public class LoginActivity extends AppCompatActivity
        implements GoogleApiClient.OnConnectionFailedListener
        , View.OnClickListener
        , FirebaseAuth.AuthStateListener {
    Button btnGoogle, btnLogin;
    LoginButton btnFacebook;
    FirebaseAuth mAuth;
    GoogleApiClient apiClient;
    public static final int REQUEST_CODE_GOOGLE_SIGNIN = 99;
    public static int CHECK_SIGNIN_METHOD = 0;// 1 IS GOOGLE // 2 IS FACEBOOK
    CallbackManager mCallbackManager;
    TextView txtRegister, tvForgotPass;
    EditText edtMail, edtPass;
    ProgressBar pgLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        mCallbackManager = CallbackManager.Factory.create();

        mAuth = FirebaseAuth.getInstance();
        findViewByIds();
        createCliendGoogleLogin();
        facebokLogin();

    }

    private void loginWithUserAndPass(String mail, String pass) {
        pgLoading.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(mail, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                FirebaseUser user = mAuth.getCurrentUser();
                if (task.isSuccessful()) {
                    pgLoading.setVisibility(View.GONE);
                    Toast.makeText(LoginActivity.this, "Dang nhap thanh cong", Toast.LENGTH_SHORT).show();
//                    startActivity(new Intent(LoginActivity.this, HomePageActivity.class));
                } else {
                    pgLoading.setVisibility(View.GONE);
                    Toast.makeText(LoginActivity.this, "Dang nhap khong thanh cong", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void facebokLogin() {
        btnFacebook.setReadPermissions("email", "public_profile");
        btnFacebook.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                CHECK_SIGNIN_METHOD = 2;
                String tokenId = loginResult.getAccessToken().getToken();
                Log.d("kiemtra facebook", tokenId);
                firebaseCertificate(tokenId);
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException e) {

            }
        });
    }

    // start login with google
    private void firebaseCertificate(String tokenId) {
        AuthCredential credential = null;
        if (CHECK_SIGNIN_METHOD == 1) {
            // dang nhap google
            credential = GoogleAuthProvider.getCredential(tokenId, null);

        } // end google if
        else if (CHECK_SIGNIN_METHOD == 2) {
            credential = FacebookAuthProvider.getCredential(tokenId);
        }

        if (credential != null) {
            mAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Log.d("kiemtra", "dang nhap thanh cong : " + mAuth.getCurrentUser().getEmail());
                    } else {
                        Log.d("kiemtra", "dang nhap that bai : ");
                    }
                }
            });
        }


    } // end start login with google


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_GOOGLE_SIGNIN) {
            if (resultCode == RESULT_OK) {
                GoogleSignInResult signInResult = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
                GoogleSignInAccount account = signInResult.getSignInAccount();
                String tokenId = account.getIdToken();

                firebaseCertificate(tokenId);


            }
        }
    }

    private void findViewByIds() {
        btnGoogle = (Button) findViewById(R.id.btnDangNhapGoogle);
        btnFacebook = (LoginButton) findViewById(R.id.btnDangNhapFacebook);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        txtRegister = (TextView) findViewById(R.id.txtDangKyMoi);
        tvForgotPass = (TextView) findViewById(R.id.tvForgotPass);
        pgLoading = (ProgressBar) findViewById(R.id.pgLoading);


        edtMail = (EditText) findViewById(R.id.edtEmail);
        edtPass = (EditText) findViewById(R.id.edtPassword);


        btnGoogle.setOnClickListener(this);
        btnFacebook.setOnClickListener(this);
        txtRegister.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
        tvForgotPass.setOnClickListener(this);

    }


    // start create cliend google
    private void createCliendGoogleLogin() {
        GoogleSignInOptions signInOptions = new GoogleSignInOptions.Builder()
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        apiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, signInOptions)
                .build();


    } // end start create cliend google

    // start open form google
    private void loginGoogle(GoogleApiClient apiClient) {
        CHECK_SIGNIN_METHOD = 1;
        Intent intentGoogle = Auth.GoogleSignInApi.getSignInIntent(apiClient);
        startActivityForResult(intentGoogle, REQUEST_CODE_GOOGLE_SIGNIN);
    }// end open form google

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }


    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btnDangNhapGoogle:
                // doing
                loginGoogle(apiClient);
                break;
            case R.id.btnDangNhapFacebook:
                break;
            case R.id.txtDangKyMoi:
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                break;
            case R.id.btnLogin:
                String mail = edtMail.getText().toString().trim();
                String pass = edtPass.getText().toString().trim();
                if (edtPass.length() > 0 && edtPass.length() > 0) {
                    loginWithUserAndPass(mail, pass);
                } else {
                    Toast.makeText(this, "check mail and pass", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.tvForgotPass:
                startActivity(new Intent(LoginActivity.this, ForgotPasswordActivity.class));
                break;

        }
    }

    @Override
    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            Log.d("kiemtra", "ban xin chao: " + user.getEmail());
            startActivity(new Intent(LoginActivity.this, HomePageActivity.class));
        } else {
            Log.d("kiemtra", "ban chua dang nhap, khong chao: ");

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

