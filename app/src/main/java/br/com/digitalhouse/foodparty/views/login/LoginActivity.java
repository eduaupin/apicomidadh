package br.com.digitalhouse.foodparty.views.login;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.login.R;
import com.google.firebase.auth.FirebaseAuth;

import br.com.digitalhouse.foodparty.util.AppUtil;
import br.com.digitalhouse.foodparty.util.BlurUtil;

import br.com.digitalhouse.foodparty.views.home.HomeActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {


    private TextInputLayout inputEmail;
    private TextInputLayout inputSenha;
    private TextView textEsqueciSenha;
    private Button btnLogin;
    private Button btnFacebook;
    private TextView txtRegistrese;


    private SignInButton googleSignInButton;
    private GoogleSignInClient googleSignInClient;
    public static final String GOOGLE_ACCOUNT = "google_account";


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

        loginGoogle();

        btnLogin.setOnClickListener(view -> validarCampos());

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

    public void loginEmail() {

        String email = inputEmail.getEditText().getText().toString();
        String password = inputSenha.getEditText().getText().toString();

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Campos não podem ser vazios :(", Toast.LENGTH_SHORT).show();
            return;
        }

        FirebaseAuth.getInstance()
                .signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {

                    if(task.isSuccessful()){

                        irParaHome(FirebaseAuth.getInstance().getCurrentUser().getUid());

                    }else {

                        Snackbar.make(btnLogin, task.getException().getMessage(), Snackbar.LENGTH_SHORT).show();

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
        googleSignInButton = findViewById(R.id.sign_in_button);

    }

    private void irParaHome(String uiid) {
        AppUtil.salvarIdUsuario(getApplication().getApplicationContext(), uiid);
        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
        finish();
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

    public void concluirLogin(GoogleSignInAccount conta) {
        Intent intent = new Intent(this, HomeActivity.class);
        intent.putExtra(GOOGLE_ACCOUNT, conta);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK){
            switch (requestCode){
                case 101:
                    try{
                        Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);

                        GoogleSignInAccount conta = task.getResult(ApiException.class);
                        concluirLogin(conta);

                    }catch (ApiException e){
                        Log.i("LOG", "Error: " + e.getMessage());

                        Toast.makeText(getApplicationContext(), "Erro", Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        GoogleSignInAccount alreadyLoggedAccount =GoogleSignIn.getLastSignedInAccount(this);
        if(alreadyLoggedAccount != null){
            Toast.makeText(this, "Você já está logadp", Toast.LENGTH_SHORT).show();
            concluirLogin(alreadyLoggedAccount);
        }else{
            Toast.makeText(this, "Faça o login no app :)", Toast.LENGTH_SHORT).show();
        }

    }

    public void loginGoogle(){
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        googleSignInClient = GoogleSignIn.getClient(this, gso);

        googleSignInButton.setOnClickListener(view -> {
            Intent signIntent = googleSignInClient.getSignInIntent();
            startActivityForResult(signIntent, 101);
        });

    }
}
