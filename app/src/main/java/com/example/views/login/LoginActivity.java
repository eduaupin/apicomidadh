package com.example.views.login;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.login.R;
import com.example.util.BlurUtil;
import com.example.views.GenericaActivity;
import com.example.views.eventos.DetalhesDoEventoActivity;
import com.example.views.eventos.NaoExistemEventosActivity;

import com.example.views.home.HomeActivity;
import com.example.views.home.NotificacoesActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {


    private TextInputLayout inputEmail;
    private TextInputLayout inputSenha;
    private TextView textEsqueciSenha;
    private Button btnLogin;
    private Button btnFacebook;
    private TextView txtRegistrese;


    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9#_~!$&'()*+,;=:.\"(),:;<>@\\[\\]\\\\]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$";
    private Pattern pattern = Pattern.compile(EMAIL_PATTERN);
    private Matcher matcher;

    private String email, senha;

    public static final String EMAIL_KEY = "email";
    public static final String SENHA_KEY = "senha";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        //Add efeito na imagem de background
        ImageView bgRegister = (ImageView) findViewById(R.id.image_register_background);
        Bitmap bitmap = ((BitmapDrawable) bgRegister.getDrawable()).getBitmap();
        bgRegister.setImageBitmap(new BlurUtil().blur(LoginActivity.this, bitmap, 5.0f));

        initViews();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validarCampos();
            }
        });

        btnFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validarCampos();
            }
        });

        textEsqueciSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                esqueciSenha();
            }
        });


        txtRegistrese.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

    }


    public void initViews() {
        inputEmail = findViewById(R.id.tilayout_login_email);
        inputSenha = findViewById(R.id.tilayout_login_password);
        textEsqueciSenha = findViewById(R.id.text_password_forget);
        btnLogin = findViewById(R.id.button_login);
        btnFacebook = findViewById(R.id.button_login_facebook);
        txtRegistrese = findViewById(R.id.text_login_register);
    }

    public void validarCampos(){
        inputEmail.setErrorEnabled(false);
        inputSenha.setErrorEnabled(false);

        email = inputEmail.getEditText().getText().toString().trim();
        senha = inputSenha.getEditText().getText().toString().trim();

        if (!validateEmail(email) && !validatePassword(senha)) {
            inputEmail.setError("Digite um e-mail válido");
            inputSenha.setError("Sua senha deve ter pelo menos 6 caractéres!");
        } else if (!validatePassword(senha)) {
            inputSenha.setError("Sua senha deve ter pelo menos 6 caractéres!");
            inputEmail.setErrorEnabled(false);
        } else if(!validateEmail(email)){
            inputEmail.setError("Digite um e-mail válido");
            inputSenha.setErrorEnabled(false);
        }else{

            inputEmail.setErrorEnabled(false);
            inputSenha.setErrorEnabled(false);

            //Incluir fluxo seguinte... próximas sprints
            startActivity(new Intent(LoginActivity.this, HomeActivity.class));

        }

    }

    public boolean validateEmail(String email) {
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public boolean validatePassword(String password) {
        return password.length() > 5;
    }

    public void esqueciSenha(){
        startActivity(new Intent(LoginActivity.this, EsqueciSenhaActivity.class));
    }
}
