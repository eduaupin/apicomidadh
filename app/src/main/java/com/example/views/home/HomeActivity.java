package com.example.views.home;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.example.views.eventos.ListaEventosActivity;
import com.example.views.login.LoginActivity;
import com.example.views.pratos.PratosFavoritosActivity;
import com.example.views.sobre.SobreActivity;
import com.google.android.material.navigation.NavigationView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;

import androidx.navigation.ui.AppBarConfiguration;

import com.example.login.R;

public class HomeActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    //Cria um atributo do tipo DrawerLayout
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        replaceFragment(new HomeFragment());

        //Cria uma nova instancia da toolbar e inicializa
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        //Inicializa o drawer
        drawer = findViewById(R.id.drawer_layout);

        //Cria uma nova instância do NavigationView e inicializa
        NavigationView navigationView = findViewById(R.id.nav_view);

        //O ActionBarDraweToggle configura o icone para abrir e fechar
        //E recebe como parametro: activity, uma instancia do drawer para vincular a actionBar da activity,
        //uma instância da Toolbar para vincular o DrawerLayout
        //e as Strings para abertura e fechamento (acessibilidade)
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        );

        //invocamos o método addDrawerListener() do DrawerLayout para conectar o ActionBarDrawerToggle no próprio DrawerLayout.
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        //Passa cada ID de menu como um conjunto de IDs, pois cada menu deve ser considerado como destino de nível superior.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_eventos, R.id.nav_pratos_favoritos,
                R.id.nav_sobre, R.id.nav_logout)
                .setDrawerLayout(drawer)
                .build();


        //Ação de clique nos itens do menu do drawerLayout e replace dos respectivos fragmentos
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                //Criação de uma variavel que vai receber o id do item selecionado
                int id = menuItem.getItemId();

                //Verifica se o id recebido é igual ao do layout e realiza uma ação
                if (id == R.id.nav_home) {
                    replaceFragment(new HomeFragment());
                } else if (id == R.id.nav_eventos) {
                    startActivity(new Intent(HomeActivity.this, ListaEventosActivity.class));
                } else if (id == R.id.nav_pratos_favoritos) {
                    startActivity(new Intent(HomeActivity.this, PratosFavoritosActivity.class));
                } else if (id == R.id.nav_sobre) {
                    startActivity(new Intent(HomeActivity.this, SobreActivity.class));
                } else if (id == R.id.nav_logout) {

                    Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                    // set the new task and clear flags
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }

                //chama a ação de close do drawerLayout e mover a gaveta para direita
                drawer.closeDrawer(GravityCompat.START);
                return true;
            }
        });

        //TODO: Colocar um ícone de adicionar evento na home? id: home_add_evento
    }

    //Sobrescrita do método da ação de voltar que verifica se a NavigationView está aberta e o botão voltar for
    //precionado fecha apenas o menu ao invés da Activity
    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    //Inflar o menu superior e isso adiciona itens à barra de ação, se estiver presente.
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        //Criação de uma variavel que vai receber o id do item selecionado
        int id = item.getItemId();

        //Verifica se o id recebido é igual ao do layout e realiza uma ação
        if (id == R.id.menu_notificacao) {

            startActivity(new Intent(HomeActivity.this, NotificacoesActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //Método que recebe um Fragmento e recarrega na tela
    public void replaceFragment(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.home_container, fragment);
        transaction.commit();
    }
}