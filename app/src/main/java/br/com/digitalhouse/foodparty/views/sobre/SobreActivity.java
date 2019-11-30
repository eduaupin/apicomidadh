package br.com.digitalhouse.foodparty.views.sobre;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.login.R;

public class SobreActivity extends AppCompatActivity {
    private TextView reportarBug;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sobre);

        reportarBug = findViewById(R.id.sobre_reportar_bug);

        Toolbar favToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(favToolbar);

        if (favToolbar != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setElevation(0);
            favToolbar.setTitle("Sobre");
        }

        reportarBug.setOnClickListener(view -> {
            String email = getString(R.string.nosso_email);
            Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:" + email));
            intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.relato_problema));
            intent.putExtra(Intent.EXTRA_TEXT, "");
            startActivity(Intent.createChooser(intent, getString(R.string.enviar_email_via)));
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

}
