package br.ulbra.appfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.hardware.biometrics.BiometricPrompt;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    private EditText edemail;
    private EditText edsenha;
    private CheckBox chmostrar;
    private Button btLogin;
    private Button btregistrar;
    private ProgressBar pbbar;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        edemail = (EditText)findViewById(R.id.edt_email);
        edsenha = (EditText) findViewById(R.id.edt_senha);
        btLogin = (Button) findViewById(R.id.btn_login);
        btregistrar = (Button) findViewById(R.id.btn_registrar);
        chmostrar = (CheckBox) findViewById(R.id.chk_mostrarsenha);
        pbbar = (ProgressBar) findViewById(R.id.pb_progress);
        pbbar.setVisibility(View.INVISIBLE);

        chmostrar.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    edsenha.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }else{
                    edsenha.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
    btLogin.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String loginEmail = edemail.getText().toString();
            String loginSenha = edsenha.getText().toString();
            if(!TextUtils.isEmpty(loginEmail) || !TextUtils.isEmpty(loginSenha) ){
                pbbar.setVisibility(View.VISIBLE);
                mAuth.signInWithEmailAndPassword(loginEmail,loginSenha)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    abrirTelaPrincipal();
                                }else{
                                    String error = task.getException().getMessage();
                                    Toast.makeText(Login.this,""+error,Toast.LENGTH_LONG).show();
                                    pbbar.setVisibility(View.INVISIBLE);
                                }
                            }

                            private void abrirTelaPrincipal() {
                                Intent intent = new Intent(Login.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        });
            }

        }
    });
    }

}