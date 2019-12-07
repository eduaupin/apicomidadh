package br.com.digitalhouse.foodparty.views.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

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

import com.facebook.login.LoginManager;
import com.firebase.ui.auth.AuthUI;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthProvider;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

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
    private TextView nomeUser;
    private TextView emailUser;
    private FirebaseAuth mFirebase;
    private DatabaseReference databaseReference;


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

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user != null){
            nomeUser.setText(user.getDisplayName());
            emailUser.setText(user.getEmail());
        }



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
                    logout();

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
    private void logout() {

        AuthUI.getInstance().signOut(this)
                   .addOnCompleteListener(task -> {
                    startActivity(new Intent(this, LoginActivity.class));
                    finish();
                });
        LoginManager.getInstance().logOut();
    }
    private void initViews() {
        buttonAddEvento = findViewById(R.id.home_add_evento);
        toolbar = findViewById(R.id.toolbar);
        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        nomeUser = headerView.findViewById(R.id.drawer_name);
        emailUser = headerView.findViewById(R.id.drawer_email);
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