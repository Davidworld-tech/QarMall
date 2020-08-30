package qarmall.co.ke;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class ResetPasswordActivity extends AppCompatActivity {
    private EditText resetEmail;
    private Button resetButton;
    private TextView goBack, forgotPasswordSentText;
    private ProgressBar resetProgressBar;
    private ImageView forgotEmailIcon;
    private FrameLayout parentFrameLayout;
    private FirebaseAuth firebaseAuth;
    private ViewGroup emailIconContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        resetEmail = findViewById(R.id.reset_email);
        resetButton = findViewById(R.id.resetPasswordBtn);
        goBack = findViewById(R.id.reset_exit_close);
        forgotPasswordSentText = findViewById(R.id.forgotPasswordEmailIconText);
        forgotEmailIcon = findViewById(R.id.forgotPasswordEmailIcon);
        firebaseAuth = FirebaseAuth.getInstance();
        emailIconContainer = findViewById(R.id.ln1);
        resetProgressBar = findViewById(R.id.reset_loading_bar);
        resetEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkInputs();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginIntent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(loginIntent);
                finish();
            }
        });
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetButton.setTextColor(Color.argb(50, 255, 255, 255));
                if (TextUtils.isEmpty(resetEmail.getText())) {
                    resetEmail.setError("Please enter email id");
                    resetEmail.requestFocus();
                } else {
                    firebaseAuth.sendPasswordResetEmail(resetEmail.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(getApplicationContext(), "Email Sent Successfully", Toast.LENGTH_SHORT).show();
                                    } else {
                                        String error = Objects.requireNonNull(task.getException()).getMessage();
                                        resetProgressBar.setVisibility(View.GONE);
                                        TransitionManager.beginDelayedTransition(emailIconContainer);
                                        forgotPasswordSentText.setText(error);
                                        forgotPasswordSentText.setTextColor(getResources().getColor(R.color.red));
                                        forgotEmailIcon.setVisibility(View.VISIBLE);

                                    }
                                }
                            });
                    forgotEmailIcon.setVisibility(View.VISIBLE);
                }

            }
        });
    }

    private void checkInputs() {
        if (TextUtils.isEmpty(resetEmail.getText())) {
            resetButton.setTextColor(Color.argb(50, 255, 255, 255));
        } else {
            resetButton.setTextColor(Color.rgb(255, 255, 255));
        }
    }
}