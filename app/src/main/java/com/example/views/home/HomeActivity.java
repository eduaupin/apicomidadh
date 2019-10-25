package com.example.views.home;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;

import com.example.interfaces.ClickEvento;
import com.example.interfaces.ClickPratos;
import com.example.login.R;



public class HomeActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ReplaceFragment(new HomeFragment());

        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        );

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home,R.id.nav_eventos,R.id.nav_pratos_favoritos,R.id.nav_sobre,R.id.nav_logout
                ).setDrawerLayout(drawerLayout).build();

<<<<<<< Updated upstream
        recyclerViewPratos.setLayoutManager(layoutManager);

        //PagerView
        List<ModelCard> listaModelo = new ArrayList<>();

        ViewPager viewPager = findViewById(R.id.viewPager_home);

        listaModelo.add(new ModelCard("Noite do Churros", "10/10/2019", CardEventoFragment.novaInstancia(R.drawable.churros,"Noite do Churros","10/10/2019")));
        listaModelo.add(new ModelCard("Churrasco dos migos", "12/10/2019", CardEventoFragment.novaInstancia(R.drawable.churras,"Churrasco dos migos", "12/10/20199")));
        listaModelo.add(new ModelCard("Festa do Sorvete", "03/11/2019", CardEventoFragment.novaInstancia(R.drawable.sorvete,"Festa do Sorvete", "03/11/2019")));

        CardEventoAdapter adapter = new CardEventoAdapter(getSupportFragmentManager(), listaModelo);
=======
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
>>>>>>> Stashed changes

                //Criação de uma variavel que vai receber o id do item selecionado
                int id = menuItem.getItemId();

                //Verifica se o id recebido é igual ao do layout e realiza uma ação
                if (id == R.id.nav_home){

                    //Faço o replace do fragmento de Home quando clicar no botão de Home
                    // replaceFragment(new HomeFragment());

                }else if (id == R.id.nav_eventos){

                    //Faço o replace do fragmento de Galeria quando clicar no botão de Galeria
                    // replaceFragment(new GaleriaFragment());

                }

                //chama a ação de close do drawerLayout e mover a gaveta para direita
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });

    }

    private void ReplaceFragment(Fragment fragment){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.container_home, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void initViews(){



    }


}
