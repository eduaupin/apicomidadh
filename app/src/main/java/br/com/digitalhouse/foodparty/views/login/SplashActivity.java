package br.com.digitalhouse.foodparty.views.login;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

import br.com.digitalhouse.foodparty.R;
import br.com.digitalhouse.foodparty.util.ImageUtil;

public class SplashActivity extends AppCompatActivity {
    private ImageView bgSplash;
    private Timer timer = new Timer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        initViews();

        //Add blur effect to background image
        Bitmap bitmap = ((BitmapDrawable) bgSplash.getDrawable()).getBitmap();
        bgSplash.setImageBitmap(new ImageUtil().blur(SplashActivity.this, bitmap, 5.0f));

        bgSplash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextScreen();
            }
        });

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                nextScreen();
            }
        }, 3000);
    }

    public void initViews(){
        bgSplash = findViewById(R.id.image_splash_background);
    }

    public void nextScreen(){
        timer.cancel();
        startActivity(new Intent(SplashActivity.this, LoginActivity.class));
        finish();
    }
}
