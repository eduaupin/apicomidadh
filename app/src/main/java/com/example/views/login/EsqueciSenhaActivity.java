package com.example.views.login;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.example.login.R;

public class EsqueciSenhaActivity extends AppCompatActivity {

    private TextInputEditText inputEmail;
    private Button botaoEnviar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_esqueci_senha);

        inputEmail = findViewById(R.id.inputRecuperarEmail);
        botaoEnviar = findViewById(R.id.botaoEnviar);

        botaoEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (inputEmail.getText().equals("") || inputEmail.getText() == null) {
                    inputEmail.setError("Por favor, informe seu e-mail");
                } else {
                    //Adicionar função
                }
            }
        });
    }
}
