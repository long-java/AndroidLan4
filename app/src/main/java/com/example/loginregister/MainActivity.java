package com.example.loginregister;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    EditText user, email, password;
    TextView txtBanChuaCoTaiKhoan;
    Button btLogin, btRegister;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance(); //Khởi tạo phiên bản FirebaseAuth

        user = (EditText) findViewById(R.id.edtUser);
        email = (EditText) findViewById(R.id.edtEmail);
        password = (EditText) findViewById(R.id.edtPassword);
        txtBanChuaCoTaiKhoan = (TextView) findViewById(R.id.txtBanChuaCoTaiKhoan);
        btLogin = (Button) findViewById(R.id.btLogin);
        btRegister = (Button) findViewById(R.id.btRegister);

        btRegister.setVisibility(View.GONE);
        user.setVisibility(View.GONE);

        //Sự kiện bấm vào txtBanChuaCoTaiKhoan
        txtBanChuaCoTaiKhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btRegister.setVisibility(View.VISIBLE);
                user.setVisibility(View.VISIBLE);
                btLogin.setVisibility(View.GONE);
                txtBanChuaCoTaiKhoan.setVisibility(View.GONE);
            }
        });

        //Sự kiện bấm vào button Login
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(email.getText().toString().isEmpty() || password.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this, "Vui lòng  nhập đầy đủ", Toast.LENGTH_SHORT).show();
                }else{
                    //Ham dang nhap
                    Login(email.getText().toString(), password.getText().toString());
                }
            }
        });
        //Sự kiện bấm vào button Register
        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(user.getText().toString().isEmpty() || email.getText().toString().isEmpty() || password.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this, "Vui lòng  nhập đầy đủ", Toast.LENGTH_SHORT).show();
                }else{
                    //Ham dang ky
                    Register(user.getText().toString(), email.getText().toString(), password.getText().toString());

                }

            }
        });
    }

    //Function Login
    public void Login(String email, String password){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();

                            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                            startActivity(intent);


                        } else {
                            // If sign in fails, display a message to the user.

                            Toast.makeText(MainActivity.this, "Email hoặc mật khẩu sai !.", Toast.LENGTH_SHORT).show();
                            // ...
                        }
                    }
                });
    }

    //Function Register
    public void Register(String user, String email, String password){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();

                            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                            startActivity(intent);

                        } else {
                            Toast.makeText(MainActivity.this, "Đăng ký không thành công.", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }



}














