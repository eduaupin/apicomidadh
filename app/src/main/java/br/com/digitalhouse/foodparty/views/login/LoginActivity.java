package br.com.digitalhouse.foodparty.views.login;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import br.com.digitalhouse.foodparty.R;

import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import br.com.digitalhouse.foodparty.util.AppUtil;

import br.com.digitalhouse.foodparty.util.ImageUtil;
import br.com.digitalhouse.foodparty.views.home.HomeActivity;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {


    private static final String TAG = "";
    private TextInputLayout inputEmail;
    private TextInputLayout inputSenha;
    private TextView textEsqueciSenha;
    private Button btnLogin;
    private LoginButton btnFacebook;
    private TextView txtRegistrese;
    private CallbackManager callbackManager;
    private FirebaseAuth mAuth;
    private SignInButton googleSignInButton;
    private GoogleSignInClient googleSignInClient;
    private static final int RC_SIGN_IN = 1001;
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
        bgRegister.setImageBitmap(new ImageUtil().blur(LoginActivity.this, bitmap, 5.0f));
        initViews();



        btnLogin.setOnClickListener(view -> loginEmail());

        btnFacebook.setOnClickListener(v -> loginFacebook());

        textEsqueciSenha.setOnClickListener(view -> esqueciSenha());

        googleSignInButton.setOnClickListener(view -> loginGoogle());

        txtRegistrese.setOnClickListener(view -> startActivity(new Intent(LoginActivity.this, RegisterActivity.class)));

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
        callbackManager = CallbackManager.Factory.create();
        mAuth = FirebaseAuth.getInstance();
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

    public void loginFacebook() {
        callbackManager = CallbackManager.Factory.create();
        LoginButton loginButton = findViewById(R.id.button_login_facebook);
        loginButton.setReadPermissions("email", "public_profile");
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d(TAG, "facebook:onSuccess:" + loginResult);
                handleFacebookAccessToken(loginResult.getAccessToken());
                irParaHome(loginResult.getAccessToken().getUserId());
            }

            @Override
            public void onCancel() {
                Log.d(TAG, "facebook:onCancel");
                // ...
            }

            @Override
            public void onError(FacebookException error) {
                Log.d(TAG, "facebook:onError", error);
                // ...
            }
        });// ...
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
            callbackManager.onActivityResult(requestCode, resultCode, data);

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                GoogleSignInAccount account = result.getSignInAccount();
                autenticacaoGoogle(account);
            }
        }

    }

    private void autenticacaoGoogle(GoogleSignInAccount account) {
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        FirebaseAuth.getInstance().signInWithCredential(credential).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                irParaHome(FirebaseAuth.getInstance().getCurrentUser().getUid());
            } else {
                Toast.makeText(getApplicationContext(), "Erro login google", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();


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
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        GoogleApiClient googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(LoginActivity.this, connectionResult -> {
                    Toast.makeText(getApplicationContext(), "Falha na conexão", Toast.LENGTH_SHORT).show();
                })
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        Intent signIntent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        startActivityForResult(signIntent, RC_SIGN_IN);


    }

    private void handleFacebookAccessToken(AccessToken token) {
        Log.d(TAG, "handleFacebookAccessToken:" + token);
        // [START_EXCLUDE silent]
//        showProgressDialog();
        // [END_EXCLUDE]

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }

                        // [START_EXCLUDE]
                        //hideProgressDialog();
                        // [END_EXCLUDE]
                    }
                });
    }


}
