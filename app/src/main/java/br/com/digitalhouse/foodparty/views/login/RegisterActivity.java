package br.com.digitalhouse.foodparty.views.login;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.digitalhouse.foodparty.R;
import br.com.digitalhouse.foodparty.util.AppUtil;
import br.com.digitalhouse.foodparty.util.ImageUtil;
import br.com.digitalhouse.foodparty.views.home.HomeActivity;

public class RegisterActivity extends AppCompatActivity {
    private TextInputLayout inputEmail;
    private TextInputLayout inputNome;
    private TextInputLayout inputSenha;
    private Button btnRegister;
    private TextView textRegisterLogin;

    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9#_~!$&'()*+,;=:.\"(),:;<>@\\[\\]\\\\]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$";
    private Pattern pattern = Pattern.compile(EMAIL_PATTERN);
    private Matcher matcher;

    private String nome, email, senha;

    public static final String EMAIL_KEY = "email";
    public static final String SENHA_KEY = "senha";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initViews();

        //Add blur effect to background image
        ImageView bgRegister = (ImageView) findViewById(R.id.image_register_background);
        Bitmap bitmap = ((BitmapDrawable) bgRegister.getDrawable()).getBitmap();
        bgRegister.setImageBitmap(new ImageUtil().blur(RegisterActivity.this, bitmap, 5.0f));

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = inputEmail.getEditText().getText().toString().trim();
                nome = inputNome.getEditText().getText().toString().trim();
                senha = inputSenha.getEditText().getText().toString().trim();

                if (!validateEmail(email)) {
                    inputEmail.setError("Não é um e-mail válido");
                } else if (nome.isEmpty()) {
                    inputNome.setError("Insira um nome de usuário");
                } else if (!validatePassword(senha)) {
                    inputSenha.setError("Senha precisa ter no mínimo 6 caracteres");
                } else {
                    inputNome.setErrorEnabled(false);
                    inputEmail.setErrorEnabled(false);
                    inputSenha.setErrorEnabled(false);

                    sendBundleToLogin();
                }
                registrarUsuario(email, senha);

            }
        });

        textRegisterLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    public void registrarUsuario(String email, String senha){
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,senha)
                .addOnCompleteListener(task -> {
                    if(task.isSuccessful()){
                        AppUtil.salvarIdUsuario(RegisterActivity.this, FirebaseAuth.getInstance().getCurrentUser().getUid());
                        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                        finish();
                    }else {
                        Snackbar.make(btnRegister, task.getException().getMessage(), Snackbar.LENGTH_LONG).show();
                    }
                });
    }

    public void initViews() {
        inputEmail = findViewById(R.id.tilayout_register_email);
        inputNome = findViewById(R.id.tilayout_register_user);
        inputSenha = findViewById(R.id.tilayout_register_password);
        btnRegister = findViewById(R.id.button_register);
        textRegisterLogin = findViewById(R.id.text_register_login);
    }

    public boolean validateEmail(String email) {
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public boolean validatePassword(String password) {
        return password.length() > 5;
    }

    public void sendBundleToLogin() {

        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);

        Bundle bundle = new Bundle();

        bundle.putString(EMAIL_KEY, email);
        bundle.putString(SENHA_KEY, senha);

        intent.putExtras(bundle);

        startActivity(intent);
    }
}
