package com.example.crudapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class SignActivity extends AppCompatActivity {
    private static final String TAG = "SignActivity";

    EditText username, email, password, confirmPassword;
    Button btnSign;
    TextView tvLogin;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);

        mAuth = FirebaseAuth.getInstance();

        username = findViewById(R.id.etUsername);
        email = findViewById(R.id.etEmail);
        password = findViewById(R.id.etPassword);
        confirmPassword = findViewById(R.id.etConfirmPassword);
        btnSign = findViewById(R.id.btnSign);
        tvLogin = findViewById(R.id.tvLogin);

        tvLogin.setOnClickListener(view -> {
            Intent sign = new Intent(SignActivity.this, MainActivity.class);
            startActivity(sign);
        });

        btnSign.setOnClickListener(view -> {
            String usernameInput = username.getText().toString().trim();
            String emailInput = email.getText().toString().trim();
            String passwordInput = password.getText().toString().trim();
            String confirmPasswordInput = confirmPassword.getText().toString().trim();

            if (TextUtils.isEmpty(emailInput)) {
                Toast.makeText(SignActivity.this, "Please enter email", Toast.LENGTH_SHORT).show();
                return;
            }
            if (TextUtils.isEmpty(passwordInput)) {
                Toast.makeText(SignActivity.this, "Please enter password", Toast.LENGTH_SHORT).show();
                return;
            }
            if (TextUtils.isEmpty(usernameInput)) {
                Toast.makeText(SignActivity.this, "Please enter username", Toast.LENGTH_SHORT).show();
                return;
            }
            if (!TextUtils.equals(passwordInput, confirmPasswordInput)) {
                Toast.makeText(SignActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                return;
            }

            mAuth.createUserWithEmailAndPassword(emailInput, passwordInput)
                    .addOnCompleteListener(SignActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Log.d(TAG, "createUserWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();
                                updateUI(user);
                            } else {
                                Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                Toast.makeText(SignActivity.this, "Authentication failed: " + task.getException().getMessage(),
                                        Toast.LENGTH_SHORT).show();
                                updateUI(null);
                            }
                        }
                    });
        });
    }

    private void updateUI(FirebaseUser user) {
        if (user != null) {
            FirebaseAuth.getInstance().signOut();
            Toast.makeText(SignActivity.this, "Registration successful. Please login.", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(SignActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(SignActivity.this, "Authentication failed", Toast.LENGTH_SHORT).show();
        }
    }

}

