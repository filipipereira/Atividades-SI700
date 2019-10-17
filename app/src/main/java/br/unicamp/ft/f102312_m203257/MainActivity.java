package br.unicamp.ft.f102312_m203257;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import br.unicamp.ft.f102312_m203257.alunos.AlunosAdapter;
import br.unicamp.ft.f102312_m203257.alunos.AlunosFragment;
import br.unicamp.ft.f102312_m203257.interfaces.OnBiografiaRequest;
import br.unicamp.ft.f102312_m203257.puzzle.PuzzleFragment;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    FragmentManager fragmentManager;
    RecyclerView mRecyclerView;
    AlunosAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        fragmentManager = getSupportFragmentManager();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if(savedInstanceState==null){
            fragmentManager = getSupportFragmentManager();
            MyFirstFragment f1 = new MyFirstFragment();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.frame, f1, "frag");
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
    }

    public void replaceFragment(Fragment fragment, String tag) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame, fragment, tag);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Fragment statsFragment = fragmentManager.findFragmentByTag("stats");
            if(statsFragment == null){
                statsFragment = new EstatisticasFragment();
            }
            replaceFragment(statsFragment,"stats");

        } else if(id == R.id.send){
        Fragment mailFragment = fragmentManager.findFragmentByTag("mail");
        if(mailFragment == null){
            mailFragment = new MailFragment();
        }
        replaceFragment(mailFragment,"mail");
        return true;
    }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.autores) {
            Fragment fragAutores = fragmentManager.findFragmentByTag("autores");
            if(fragAutores == null){
                fragAutores = new AutoresFragment();
            }
            replaceFragment(fragAutores,"autores");

        } else if (id == R.id.alunos) {
            Fragment fragAlunos = fragmentManager.findFragmentByTag("alunos");
            if(fragAlunos == null){
                fragAlunos = new AlunosFragment();

                ((AlunosFragment) fragAlunos).setOnBiografiaRequest(
                        new OnBiografiaRequest() {
                            @Override
                            public void onRequest(int position) {
                                Fragment fragBiografias = fragmentManager.findFragmentByTag("biografias");
                                if(fragBiografias == null){
                                    fragBiografias = new BiografiasFragment();
                                    ((BiografiasFragment) fragBiografias).setIndex(position);
                                }
                                replaceFragment(fragBiografias,"biografias");
                            }
                        }
                );
            }
            replaceFragment(fragAlunos,"alunos");
            Toast.makeText(this, "Alunos", Toast.LENGTH_LONG).show();

        } else if (id == R.id.biografias) {

            Fragment fragBiografias = fragmentManager.findFragmentByTag("biografias");
            if(fragBiografias == null){
                fragBiografias = new BiografiasFragment();
            }
            replaceFragment(fragBiografias,"biografias");
            Toast.makeText(this, "Biografias", Toast.LENGTH_LONG).show();

        } else if (id == R.id.jogo1) {

            PuzzleFragment puzzleFragment = (PuzzleFragment) fragmentManager.findFragmentByTag("puzzle");
            if (puzzleFragment == null) {
                puzzleFragment = new PuzzleFragment();
            }
            replaceFragment(puzzleFragment, "puzzle");

            Toast.makeText(this, "Jogo1", Toast.LENGTH_LONG).show();

        } else if (id == R.id.jogo2) {
            NameFragment nameFragment = (NameFragment) fragmentManager.findFragmentByTag("name");
            if (nameFragment == null) {
                nameFragment = new NameFragment();
                nameFragment.setOnBiografiaRequest(new OnBiografiaRequest() {
                    @Override
                    public void onRequest(int position) {
                        Fragment fragBiografias = fragmentManager.findFragmentByTag("biografias");
                        if(fragBiografias == null){
                            fragBiografias = new BiografiasFragment();
                            ((BiografiasFragment) fragBiografias).setIndex(position);
                        }
                        replaceFragment(fragBiografias,"biografias");
                    }
                });
            }
            replaceFragment(nameFragment, "name");
            Toast.makeText(this, "Jogo2", Toast.LENGTH_LONG).show();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void doSomething(String msg){
        Fragment fragAutores = fragmentManager.findFragmentByTag("autores");
        if(fragAutores == null){
            fragAutores = new AutoresFragment();
        }
        replaceFragment(fragAutores,"autores");
        ((AutoresFragment)fragAutores).setText(msg);
    }

}
