package com.example.views.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.login.R;

public class FeedbackEsqueciSenhaActivity extends AppCompatActivity {

    private Button btnContinuar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_esqueci_senha);

        initView();

        btnContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //precisa aprender a voltar mais de uma tela...
                onBackPressed();
            }
        });
    }

    public void initView(){
        btnContinuar = findViewById(R.id.button_continuar);
    }
}
