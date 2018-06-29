package co.fyinews.fyinewsapp;

//import android.app.FragmentManager;
//import android.support.v4.app.Fragment;

import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import fyinews.global.ConnectivityReceiver;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, SourcesFragment.OnFragmentInteractionListener,
        TopHeadlinesFragment.OnFragmentInteractionListener, IndividualNewsFragment.IndividualNewsFragmentInteractionListener,
        NoInternetFragment.NoInternetOnFragmentInteractionListener, ConnectivityReceiver.ConnectivityReceiverListener,
        SearchFragment.OnSearchNewsFragmentInteractionListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        callTopHeadlinesFragment("General");


    }

    public void getNewsSourcesList() {


        if (!checkConnection()) {

            callNoInternetFragment();


        } else {

            SourcesFragment fragment = new SourcesFragment();
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.frag_output, fragment);
            transaction.commit();
        }

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


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


        switch (id) {
            case (R.id.news_sources):
                getNewsSourcesList();
                break;
            case (R.id.top_headlines_general):
                callTopHeadlinesFragment("General");
                break;
            case (R.id.top_headlines_business):
                callTopHeadlinesFragment("Business");
                break;
            case (R.id.top_headlines_entertainment):
                callTopHeadlinesFragment("Entertainment");
                break;
            case (R.id.top_headlines_health):
                callTopHeadlinesFragment("Health");
                break;
            case (R.id.top_headlines_science):
                callTopHeadlinesFragment("Science");
                break;
            case (R.id.top_headlines_sports):
                callTopHeadlinesFragment("Sports");
                break;
            case (R.id.top_headlines_technology):
                callTopHeadlinesFragment("Technology");
                break;
            case (R.id.search_news):
                callSearchFragment();
                break;

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void callSearchFragment() {

        if (!checkConnection()) {

            callNoInternetFragment();

        } else {


            SearchFragment fragment = new SearchFragment();

            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.frag_output, fragment);
            transaction.commit();

        }
    }



    public void callTopHeadlinesFragment(String newsCategory) {

        if (!checkConnection()) {

            callNoInternetFragment();

        } else {


            TopHeadlinesFragment fragment = new TopHeadlinesFragment();
            Bundle bundle = new Bundle();
            String myMessage = newsCategory;
            bundle.putString("newsCategory", myMessage);
            fragment.setArguments(bundle);
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.frag_output, fragment);
            transaction.commit();

        }
    }


    protected void callNoInternetFragment() {
        NoInternetFragment fragmentNoInt = new NoInternetFragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.frag_output, fragmentNoInt);
        transaction.commit();

    }

    protected void showSnack(boolean isConnected) {
        String message;
        int color;
        if (isConnected) {
            message = "Good! Connected to Internet";
            color = Color.WHITE;
        } else {
            message = "Sorry! Not connected to internet";
            color = Color.RED;
        }

        Snackbar snackbar = Snackbar
                .make(findViewById(R.id.frag_output), message, Snackbar.LENGTH_LONG);

        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(color);
        snackbar.show();
    }

    public void switchContent(int id, Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(id, fragment, fragment.toString());
        ft.addToBackStack(null);
        ft.commit();
    }

    protected boolean checkConnection() {
        boolean isConnected = ConnectivityReceiver.isConnected();
        return isConnected;
    }


    @Override
    protected void onResume() {
        super.onResume();

        MyApplication.getInstance().setConnectivityListener(this);
    }


    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        showSnack(checkConnection());
        if (!checkConnection()) {
            NoInternetFragment fragmentNoInt = new NoInternetFragment();
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.frag_output, fragmentNoInt);
            transaction.commit();
        }
    }

    @Override
    public void onSourceSelected(int id) {
    }

    @Override
    public void onTopHeadlineSelected(int id) {

    }

    @Override
    public void individualNewsFragmentInteraction(Uri uri) {

    }

    @Override
    public void noInternetOnFragmentInteraction(Uri uri) {

    }


    @Override
    public void onSearchNewsFragmentInteraction(Uri uri) {

    }
}
