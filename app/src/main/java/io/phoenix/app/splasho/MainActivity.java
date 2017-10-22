package io.phoenix.app.splasho;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import io.phoenix.app.splasho.container.Tab;
import io.phoenix.app.splasho.photos.PhotosContract;

import static io.phoenix.app.splasho.container.ViewPagerContainerFragment.Screen;
import static io.phoenix.app.splasho.container.ViewPagerContainerFragment.newInstance;
import static io.phoenix.app.splasho.photos.PhotosContract.OrderBy.*;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final String SELECTED_MENU_ITEM = "selected_menu_item";

    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private ActionBarDrawerToggle mDrawerToggle;
    private TextView mToolbarTitle;

    private FragmentManager mFragmentManager;

    private int selectedItem = R.id.action_photos;

    private Tab[] photosTabs = new Tab[]{
            new Tab("Latest", LATEST),
            new Tab("Popular", POPULAR),
            new Tab("Oldest", OLDEST)
    };

    private Tab[] collectionsTabs = new Tab[]{
            new Tab("All", "all"),
            new Tab("Featured", "featured"),
            new Tab("Curated", "curated")
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFragmentManager = getSupportFragmentManager();

        mToolbar = findViewById(R.id.toolbar);
        mDrawerLayout = findViewById(R.id.drawer_layout);
        mNavigationView = findViewById(R.id.navigation_view);
        mToolbarTitle = findViewById(R.id.toolbar_title);

        mNavigationView.setNavigationItemSelectedListener(this);

        setSupportActionBar(mToolbar);
        setupToolbar();

        if (savedInstanceState != null) {
            selectedItem = savedInstanceState.getInt(SELECTED_MENU_ITEM, R.id.action_photos);
        }

        Menu menu = mNavigationView.getMenu();
        if (menu != null) {
            onNavigationItemSelected(menu.findItem(selectedItem));
        }
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        selectDrawerItem(menuItem);
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(SELECTED_MENU_ITEM, selectedItem);
    }

    private void setupToolbar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true);

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mDrawerToggle);
    }

    private void selectDrawerItem(MenuItem menuItem) {

        switch (menuItem.getItemId()) {
            case R.id.action_photos:
                mFragmentManager.beginTransaction()
                        .replace(R.id.main_content, newInstance(Screen.PHOTOS, photosTabs))
                        .commit();
                break;
            case R.id.action_curated_photos:
                mFragmentManager.beginTransaction()
                        .replace(R.id.main_content, newInstance(Screen.CURATED_PHOTOS, photosTabs))
                        .commit();
                break;
            case R.id.action_collections:
                mFragmentManager.beginTransaction()
                        .replace(R.id.main_content, newInstance(Screen.COLLECTIONS, collectionsTabs))
                        .commit();
                break;
            case R.id.action_settings:
                Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show();
                break;
        }

        menuItem.setChecked(true);

        selectedItem = menuItem.getItemId();
        setToolbarTitle(menuItem.getTitle().toString());

        mDrawerLayout.closeDrawer(GravityCompat.START);
    }

    private void setToolbarTitle(String title) {
        mToolbarTitle.setText(title);
    }
}
