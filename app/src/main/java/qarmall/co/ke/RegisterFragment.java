package qarmall.co.ke;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RegisterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegisterFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    ProgressDialog loading;
    private TextView alreadyHaveAnAccount;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private FrameLayout parentFrameLayout;
    private EditText email, phoneNumber, userName, password, confirmPassword;
    private ImageButton registerClose;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;
    private Button signUpButton;
    private String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+.[a-z]+";

    public RegisterFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RegisterFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RegisterFragment newInstance(String param1, String param2) {
        RegisterFragment fragment = new RegisterFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        alreadyHaveAnAccount = view.findViewById(R.id.already_have_an_account);
        email = view.findViewById(R.id.register_email);
        phoneNumber = view.findViewById(R.id.register_phoneNumber);
        userName = view.findViewById(R.id.register_userName);
        password = view.findViewById(R.id.register_password);
        confirmPassword = view.findViewById(R.id.register_confirm_password);
        registerClose = view.findViewById(R.id.register_close);
        signUpButton = view.findViewById(R.id.btnSignUp);
        loading = new ProgressDialog(getContext());
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

        parentFrameLayout = getActivity().findViewById(R.id.register_frame_layout);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        alreadyHaveAnAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(new LoginFragment());
            }
        });
        registerClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainIntent();
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
                                                Toast.makeText(getActivity(), "Account Created successfully", Toast.LENGTH_SHORT).show();
                                                loading.dismiss();
                                                mainIntent();

                                            } else {
                                                String error = Objects.requireNonNull(task.getException()).getMessage();
                                                Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
                                                loading.dismiss();

                                            }

                                        }
                                    });
                        }
                    }
                });
    }


    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.slide_from_left, R.anim.slideout_from_right);
        fragmentTransaction.replace(parentFrameLayout.getId(), fragment);
        fragmentTransaction.commit();
    }

    private void mainIntent() {
        Intent mainIntent = new Intent(getActivity(), MainActivity.class);
        startActivity(mainIntent);
        getActivity().finish();
    }

}