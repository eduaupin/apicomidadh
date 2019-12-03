package br.com.digitalhouse.foodparty.views.login;

import android.content.Intent;

import com.google.android.material.textfield.TextInputLayout;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import br.com.digitalhouse.foodparty.R;

public class EsqueciSenhaActivity extends AppCompatActivity {

    private TextInputLayout inputEmail;
    private Button botaoEnviar;
    private ImageButton botaoVoltar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_esqueci_senha);

        inputEmail = findViewById(R.id.inputRecuperarEmail);
        botaoEnviar = findViewById(R.id.botaoEnviar);
        botaoVoltar = findViewById(R.id.botaoVoltarFav);

        botaoEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (inputEmail.getEditText().getText().equals("")
                        || inputEmail.getEditText().getText() == null) {
                    inputEmail.setError("Por favor, informe seu e-mail");
                } else {
                    startActivity(new Intent(EsqueciSenhaActivity.this,
                            FeedbackEsqueciSenhaActivity.class));
                }
            }
        });

        botaoVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentVoltar = new Intent(EsqueciSenhaActivity.this,
                        LoginActivity.class);
                startActivity(intentVoltar);
            }
        });
    }
}
