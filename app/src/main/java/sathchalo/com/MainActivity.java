package sathchalo.com;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;

import sathchalo.com.Fragments.Dashboard_Fragment;
import sathchalo.com.Fragments.Journey_Fragment;
import sathchalo.com.Fragments.Profile_Fragment;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    Toolbar toolbar;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout =findViewById(R.id.drawerLayout);
        toolbar =findViewById(R.id.toolbar);
        navigationView =findViewById(R.id.nav);

        if (savedInstanceState == null) {
            Fragment initialFragment = new Dashboard_Fragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frameLayout, initialFragment)
                    .commit();

            navigationView.setCheckedItem(R.id.dashboard);
        }


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int itemId = item.getItemId();

                if (itemId == R.id.dashboard)
                {
                    fragment(new Dashboard_Fragment());
                    drawerLayout.closeDrawer(GravityCompat.START);
                    Toast.makeText(MainActivity.this, "Dashboard", Toast.LENGTH_SHORT).show();
                }
                else if (itemId == R.id.profile)
                {
                    fragment(new Profile_Fragment());
                    drawerLayout.closeDrawer(GravityCompat.START);
                    Toast.makeText(MainActivity.this, "Profile", Toast.LENGTH_SHORT).show();
                }
                else if (itemId == R.id.journey)
                {
                    fragment(new Journey_Fragment());
                    drawerLayout.closeDrawer(GravityCompat.START);
                    Toast.makeText(MainActivity.this, "Journey", Toast.LENGTH_SHORT).show();
                }
//                else if (itemId == R.id.share)
//                {
//                    fragment(new Journey_Fragment());
//                    drawerLayout.closeDrawer(GravityCompat.START);
//                    Toast.makeText(MainActivity.this, "Journey", Toast.LENGTH_SHORT).show();
//                }
                else if (itemId == R.id.logOut)
                {
                    drawerLayout.closeDrawer(GravityCompat.START);
                    Toast.makeText(MainActivity.this, "Logout", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this, Login_Activity.class));
                }

                return true; // Return true to indicate the item has been handled
            }
        });


    }

    private void fragment(Fragment fragment){

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout,fragment);
        fragmentTransaction.commit();
    }

}