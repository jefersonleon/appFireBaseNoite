package br.ulbra.appfirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.google.firebase.auth.FirebaseAuth;

public class Registro extends AppCompatActivity {
    private EditText ednome_cad;
    private EditText edsobre_cad;
    private EditText edemail_cad;
    private EditText edsenha_cad;
    private EditText edresenha_cad;
    private CheckBox chmostrar_senha_cad;
    private Button btLogin_cad;
    private Button btcadastrar_cad;
    private ProgressBar pbbar_cad;
    private FirebaseAuth mAuth_cad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        mAuth_cad = FirebaseAuth.getInstance();
        ednome_cad = (EditText) findViewById(R.id.edt_nome_cad);
        edsobre_cad = (EditText) findViewById(R.id.edt_sobre_cad);
        edemail_cad = (EditText) findViewById(R.id.edt_email_cad);
        edsenha_cad = (EditText) findViewById(R.id.edt_senha_cad);
        edresenha_cad = (EditText) findViewById(R.id.edt_re_senha_cad);
        btLogin_cad = (Button) findViewById(R.id.btn_Login_cad);
        btcadastrar_cad = (Button) findViewById(R.id.btn_cad_cad);
        chmostrar_senha_cad = (CheckBox) findViewById(R.id.chk_mostrasenha_cad);
        pbbar_cad = (ProgressBar) findViewById(R.id.pb_progress_cad);
        pbbar_cad.setVisibility(View.INVISIBLE);

        btLogin_cad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                voltarLogin();
            }

            private void voltarLogin() {
                Intent intent = new Intent(Registro.this, Login.class);
                startActivity(intent);
                finish();
            }
        });
        chmostrar_senha_cad.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    edsenha_cad.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    edresenha_cad.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }else{
                    edsenha_cad.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    edresenha_cad.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });


    }
}