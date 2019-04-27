package com.example.book.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.book.R;
import com.example.book.adapter.HomeAdapter;
import com.example.book.model.HomeItem;
import com.example.book.utils.ModuleUtils;
import com.stx.xhb.xbanner.XBanner;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,AdapterView.OnItemClickListener {

    private TextView title,role;

    private GridView gridView;
    private XBanner banner_1;
    private String id;
    private String name;
    private String roleName;
    private String username;
    private List<HomeItem> allModule;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView = findViewById(R.id.main_gv);
        banner_1 = findViewById(R.id.banner_1);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View headerView = navigationView.getHeaderView(0);

        title = headerView.findViewById(R.id.bar_title);
        role = headerView.findViewById(R.id.role);

        id = getIntent().getStringExtra("id");
        name = getIntent().getStringExtra("name");
        roleName = getIntent().getStringExtra("role");
        username = getIntent().getStringExtra("username");
        title.setText(name);
        role.setText(roleName);
        initView();
        initData();
    }

    private void initView() {



        checkModule();

        HomeAdapter homeAdapter = new HomeAdapter(allModule);
        gridView.setAdapter(homeAdapter);
        gridView.setOnItemClickListener(this);




    }

    private void checkModule() {
        switch (roleName) {
            case "系统管理员":
                allModule = ModuleUtils.admin();
                break;
            case "图书管理员":
                allModule = ModuleUtils.book();
                break;
            case "学生":
                allModule = ModuleUtils.student();
                break;
            default:
                allModule = ModuleUtils.student();
                break;
        }
    }

    private void initData() {
        final List<Integer> integers = new ArrayList<>();
        integers.add(R.drawable.lib1);
        integers.add(R.drawable.lib2);
        integers.add(R.drawable.lib3);
        banner_1.setData(integers,null);
        banner_1.setmAdapter(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                ((ImageView) view).setImageResource(integers.get(position));
            }
        });
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_manage) {
            Intent intent = new Intent(this,UserUpdateActivity.class);
            intent.putExtra("from", "userSetting");
            intent.putExtra("id", this.id);
            startActivity(intent);
        } else if (id == R.id.nav_share) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, allModule.get(position).getClazz());
        intent.putExtra("userName", username);
        intent.putExtra("role", roleName);
        intent.putExtra("name", name);
        startActivity(intent);
    }
}
