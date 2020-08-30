package qarmall.co.ke;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class RegisterActivity extends AppCompatActivity {
    ProgressDialog loading;
    private TextView alreadyHaveAnAccount;
    private FrameLayout parentFrameLayout;
    private EditText email, phoneNumber, userName, password, confirmPassword;
    private ImageButton registerClose;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;
    private Button signUpButton;
    private String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+.[a-z]+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        alreadyHaveAnAccount = findViewById(R.id.already_have_an_account);
        email = findViewById(R.id.register_email);
        phoneNumber = findViewById(R.id.register_phoneNumber);
        userName = findViewById(R.id.register_userName);
        password = findViewById(R.id.register_password);
        confirmPassword = findViewById(R.id.register_confirm_password);
        registerClose = findViewById(R.id.register_close);
        signUpButton = findViewById(R.id.btnSignUp);
        loading = new ProgressDialog(this);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

        alreadyHaveAnAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginIntent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(loginIntent);
                finish();
            }
        });
        registerClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(mainIntent);
                finish();
            }
        });

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createAccount();
            }
        });
    }

    private void createAccount() {
        if (TextUtils.isEmpty(email.getText())) {
            email.setError("Please enter email id");
            email.requestFocus();
        } else if (TextUtils.isEmpty(phoneNumber.getText())) {
            phoneNumber.setError("Please enter email id");
            phoneNumber.requestFocus();
        } else if (TextUtils.isEmpty(userName.getText())) {
            userName.setError("Please enter username");
            userName.requestFocus();
        } else if (TextUtils.isEmpty(password.getText())) {
            password.setError("please enter password");
            password.requestFocus();
        } else if (password.length() < 8) {
            password.setError("at least 8 characters");
            password.requestFocus();
        } else if (phoneNumber.length() < 10) {
            phoneNumber.setError("Invalid phone number");
            phoneNumber.requestFocus();
        } else if (TextUtils.isEmpty(confirmPassword.getText())) {
            confirmPassword.setError("retype password");
            confirmPassword.requestFocus();
        } else if (!(email.getText().toString().matches(emailPattern))) {
            email.setError("Invalid Email Format");
            email.requestFocus();
        } else if (!(password.getText().toString().equals(confirmPassword.getText().toString()))) {
            confirmPassword.setError("password does't match");
            confirmPassword.requestFocus();
        } else {
            loading.setTitle("Create account");
            loading.setMessage("Just a moment");
            loading.setCanceledOnTouchOutside(false);
            loading.show();
            validateDetails(email, phoneNumber, userName, password, confirmPassword);
        }
    }

    private void validateDetails(final EditText email, final EditText phoneNumber, final EditText userName, EditText password, EditText confirmPassword) {
        firebaseAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Map<String, Object> userdata = new HashMap<>();
                            userdata.put("email", email.getText().toString());
                            userdata.put("phoneNumber", phoneNumber.getText().toString());
                            userdata.put("userName", userName.getText().toString());

                            firebaseFirestore.collection("USERS")
                                    .add(userdata)
                                    .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                                        @Override
                                        public void onComplete(@NonNull Task<DocumentReference> task) {
                                            if (task.isSuccessful()) {
                                                Toast.makeText(getApplicationContext(), "Account Created successfully", Toast.LENGTH_SHORT).show();
                                                loading.dismiss();
                                                Intent mIntent = new Intent(getApplicationContext(), MainActivity.class);
                                                startActivity(mIntent);
                                                finish();

                                            } else {
                                                String error = Objects.requireNonNull(task.getException()).getMessage();
                                                Toast.makeText(getApplicationContext(), error, Toast.LENGTH_SHORT).show();
                                                loading.dismiss();

                                            }

                                        }
                                    });
                        }
                    }
                });
    }

}