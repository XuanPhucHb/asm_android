package com.nxp.asm_android;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;
import com.nxp.asm_android.database.DBHelper;
import com.nxp.asm_android.entity.Category;
import com.nxp.asm_android.fragment.ListFragment;

import java.util.ArrayList;

public class DrawerMenu extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, NavigationBarView.OnItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    BottomNavigationView bottomNavigationView;
    Toolbar toolbar;
    FrameLayout mainContent;
    public static DBHelper dbHelper;
    private ListFragment listFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer_menu);
        initView();
        initData();
    }

    private void initData(){
        dbHelper = new DBHelper(this);
        dbHelper.getReadableDatabase();
        dbHelper.initCategory();
        Cursor c = dbHelper.getAllCategory();
        ArrayList<Category> mArrayList = new ArrayList<>();
        for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
            // The Cursor is now set to the right position
            System.out.println(c.getString(1));;
        }
        System.out.println();
    }

    private void initView() {

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.drawer_nav_view);
        toolbar = findViewById(R.id.drawer_toolbar);

        navigationView.bringToFront();
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle =
                new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                        R.string.open_menu_message,
                        R.string.close_menu_message);
        drawerLayout.addDrawerListener(toggle);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_item_list_spending:
                Log.d(DrawerMenu.class.getName(), "List spending");
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.drawer_nav_view, listFragment, ListFragment.class.getName())
                        .commit();
                break;
            case R.id.menu_item_add_spending:
                Log.d(DrawerMenu.class.getName(), "Add spending");
                break;
//            case R.id.menu_item_list_song:
//                Log.d(DrawerMenuDemo.class.getName(), "List song");
//                break;
//            case R.id.menu_item_create_song:
//                Log.d(DrawerMenuDemo.class.getName(), "Create song");
//                break;
//            case R.id.menu_item_about:
//                Log.d(DrawerMenuDemo.class.getName(), "About");
//                break;
//            case R.id.menu_item_policy:
//                Log.d(DrawerMenuDemo.class.getName(), "Policy");
//                break;
//            case R.id.home_button:
//                Log.d(DrawerMenuDemo.class.getName(), "Home");
//                getSupportFragmentManager()
//                        .beginTransaction()
//                        .replace(R.id.main_content, fragment01, Fragment01.class.getName())
//                        .commit();
//                break;
//            case R.id.song_button:
//                Log.d(DrawerMenuDemo.class.getName(), "Song");
//                getSupportFragmentManager()
//                        .beginTransaction()
//                        .replace(R.id.main_content, fragment02, Fragment02.class.getName())
//                        .commit();
//                break;
//            case R.id.contact_button:
//                Log.d(DrawerMenuDemo.class.getName(), "Contact");
//                break;
            default:
                throw new IllegalStateException("Unexpected value: " + item.getItemId());
        }
        return false;
    }
}
