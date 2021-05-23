package com.example.missionmemorizeapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.missionmemorizeapp.R;
import com.example.missionmemorizeapp.view.dialogs.AddFolderDialog;
import com.example.missionmemorizeapp.view.dialogs.ProfileInfoDialog;
import com.example.missionmemorizeapp.view.signinupviews.HomeSignInUpFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction().add(R.id.flFragmentMain, new HomeFragment()).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.nav_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.profile:
                ProfileInfoDialog profileInfoDialog = new ProfileInfoDialog();
                profileInfoDialog.show(getSupportFragmentManager(), "MyFragment");
                return true;
            case R.id.logout:
                //TODO LogoutTask
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
