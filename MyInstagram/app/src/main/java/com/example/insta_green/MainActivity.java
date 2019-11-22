package com.example.insta_green;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.insta_green.fragments.ComposeFragment;
import com.example.insta_green.fragments.PostsFragment;
import com.example.insta_green.fragments.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {




    private BottomNavigationView bottomNavigationView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final FragmentManager fragmentManager = getSupportFragmentManager();
        bottomNavigationView = findViewById(R.id.bottom_navigation);


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment fragment;

                switch (menuItem.getItemId()) {
                    case R.id.action_home:
                        //Todo Swap fragmnet here
                        fragment  = new PostsFragment();
                        //Toast.makeText(MainActivity.this, "Home!", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_compose:
                        fragment  = new ComposeFragment();
                        //Toast.makeText(MainActivity.this, "Compose!", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_profile:
                    default:
                        //Todo Swap fragmnet here
                        fragment  = new ProfileFragment();
                        //Toast.makeText(MainActivity.this, "Profile!", Toast.LENGTH_SHORT).show();
                        break;
                }
                fragmentManager.beginTransaction().replace(R.id.flContainer, fragment).commit();
                return true;
            }
        });
        // Set default selection
        bottomNavigationView.setSelectedItemId(R.id.action_home);

    }
}
