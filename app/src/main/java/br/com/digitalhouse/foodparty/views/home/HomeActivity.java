package br.com.digitalhouse.foodparty.views.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.ui.AppBarConfiguration;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import br.com.digitalhouse.foodparty.R;
import br.com.digitalhouse.foodparty.views.eventos.CriarEventoActivity;
import br.com.digitalhouse.foodparty.views.eventos.ListaEventosActivity;
import br.com.digitalhouse.foodparty.views.login.LoginActivity;
import br.com.digitalhouse.foodparty.views.pratos.PratosFavoritosActivity;
import br.com.digitalhouse.foodparty.views.sobre.SobreActivity;

public class HomeActivity extends AppCompatActivity {
    private FloatingActionButton buttonAddEvento;
    private AppBarConfiguration mAppBarConfiguration;
    private Toolbar toolbar;
    private DrawerLayout drawer;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initViews();

        replaceFragment(new HomeFragment());

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        );
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_eventos, R.id.nav_pratos_favoritos,
                R.id.nav_sobre, R.id.nav_logout)
                .setDrawerLayout(drawer)
                .build();


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                int id = menuItem.getItemId();

                if (id == R.id.nav_home) {
                    replaceFragment(new HomeFragment());
                } else if (id == R.id.nav_eventos) {
                    startActivity(new Intent(HomeActivity.this, ListaEventosActivity.class));
                } else if (id == R.id.nav_pratos_favoritos) {
                    startActivity(new Intent(HomeActivity.this, PratosFavoritosActivity.class));
                } else if (id == R.id.nav_sobre) {
                    startActivity(new Intent(HomeActivity.this, SobreActivity.class));
                } else if (id == R.id.nav_logout) {
                    FirebaseAuth.getInstance().signOut();
                    Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }

                drawer.closeDrawer(GravityCompat.START);
                return true;
            }
        });

        buttonAddEvento.setOnClickListener(view -> {
            startActivity(new Intent(HomeActivity.this, CriarEventoActivity.class));
        });
    }

    private void initViews() {
        buttonAddEvento = findViewById(R.id.home_add_evento);
        toolbar = findViewById(R.id.toolbar);
        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public void replaceFragment(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.home_container, fragment);
        transaction.commit();
    }
}