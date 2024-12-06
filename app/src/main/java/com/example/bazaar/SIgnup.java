package com.example.bazaar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SIgnup extends AppCompatActivity {
TextInputEditText nametext ,emailtext,passwordtext;
Button singup;
TextView tologin;
FirebaseAuth mAuth;
FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signup);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        db = FirebaseFirestore.getInstance();
        nametext = findViewById(R.id.Singupname);
        emailtext = findViewById(R.id.Signupemail);
        passwordtext = findViewById(R.id.Singuppassword);
        singup = findViewById(R.id.Singupbutton);
        tologin = findViewById(R.id.tologin);
        mAuth = FirebaseAuth.getInstance();

        tologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SIgnup.this, Login.class);
                startActivity(i);
            }
        });
        singup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nametext.getText().toString();
                String password = passwordtext.getText().toString();
                String email = emailtext.getText().toString();

                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Map<String,String> data = new HashMap<>();
                                    data.put("Name",name);
                                    data.put("email",email);
                                    data.put("password",password);
                                    String userId = task.getResult().getUser().getUid();
                                    db.collection("Users").document(userId).set(data);
                                   Intent i = new Intent(SIgnup.this, MainActivity.class);
                                   startActivity(i);

                                } else {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(SIgnup.this, "Account creation failed", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }
}