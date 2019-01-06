package com.example.valerio.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.design.internal.NavigationMenuView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.CompoundButtonCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //Defining Variables
    private Toolbar toolbar;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private GoogleSignInAccount acct;
    private static String accountMail;

    List<String> addedHouse = new ArrayList<>();

    public static String getAccountMail(){
        return accountMail;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initializing Toolbar and setting it as the actionbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //Default Fragment
        final HomeFragment homefragment = new HomeFragment();
        android.support.v4.app.FragmentTransaction homeFragmentTransaction
                = getSupportFragmentManager().beginTransaction();
        homeFragmentTransaction.replace(R.id.frame,homefragment);
        homeFragmentTransaction.commit();


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // setup the alert builder
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Selezionare i sensori da gestire:");
                // add a checkbox list
                String[] sensors = {"sensorTime", "sensorMeteo", "sensorServo", "sensorTemp", "sensorNoise", "sensorLight", "sensorSisma"};
                final boolean[] checkedItems = {false, false, false, false, false, false, false};

                builder.setMultiChoiceItems(sensors, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        // user checked or unchecked a box
                    }
                });

                // add OK and Cancel buttons
                builder.setPositiveButton("Next", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // user clicked OK
                        LayoutInflater layoutInflater = getLayoutInflater();
                        final View dialogView = layoutInflater.inflate(R.layout.addhouse_dialog, null);
                        final AlertDialog.Builder builder2 = new AlertDialog.Builder(MainActivity.this);
                        builder2.setTitle("Aggiungere informazioni:");
                        builder2.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });

                        builder2.setNegativeButton("Cancel", null);

                        // create and show the alert dialog
                        final AlertDialog dialog2 = builder2.create();
                        dialog2.setView(dialogView);
                        dialog2.setCanceledOnTouchOutside(false);
                        dialog2.show();
                        dialog2.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                final String label = ((EditText) dialogView.findViewById(R.id.Label)).getText().toString();
                                final String address = ((EditText) dialogView.findViewById(R.id.Address)).getText().toString();
                                final String city = ((EditText) dialogView.findViewById(R.id.City)).getText().toString();


                                //Do stuff, possibly set wantToCloseDialog to true then...
                                if(!TextUtils.isEmpty(label)) {
                                    dialog2.dismiss();

                                    House house = new House(label, address, city,
                                            checkedItems[0], checkedItems[1],
                                            checkedItems[2], checkedItems[3],
                                            checkedItems[4], checkedItems[5],
                                            checkedItems[6]
                                    );

                                    Menu menu = navigationView.getMenu();

                                    menu.add(0, addedHouse.size() + 1001, 0, house.getName());


                                    addedHouse.add(house.getName());

                                    homefragment.addItemList(house);
                                }
                            }

                        });
                    }
                });

                builder.setNegativeButton("Cancel", null);

                // create and show the alert dialog
                AlertDialog dialog = builder.create();
               // dialog.setView(dialogView);
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();

            }
        });

        //Initializing NavigationView
        navigationView = (NavigationView) findViewById(R.id.navigation_view);



        View hView =  navigationView.getHeaderView(0);
        ImageView nav_user = (ImageView) hView.findViewById(R.id.profile_image);

        TextView username = (TextView) hView.findViewById(R.id.username);
        TextView email = (TextView) hView.findViewById(R.id.email);

        acct = GoogleSignIn.getLastSignedInAccount(this);

        if (acct != null) {
            String personName = acct.getDisplayName();
            username.setText(personName);
//            String personGivenName = acct.getGivenName();
//            String personFamilyName = acct.getFamilyName();
            String personEmail = acct.getEmail();
            email.setText(personEmail);
            accountMail = personEmail;
//            String personId = acct.getId();
            Uri personPhoto = acct.getPhotoUrl();
            Picasso.get()
                    .load(personPhoto.toString())
                    .resize(200,200)
                    .into(nav_user);
        }
        else{
            requestEmail(AccessToken.getCurrentAccessToken());
            Profile profile = Profile.getCurrentProfile();
            accountMail = profile.getId().toString();
            if (profile != null) {
                String personName = profile.getName();

                username.setText(personName);
                email.setText("");

                String photoUrl = profile.getProfilePictureUri(200, 200).toString();

                Picasso.get()
                        .load(photoUrl)
                        .into(nav_user);

            } else {
                Profile.fetchProfileForCurrentAccessToken();
            }
        }


        //Setting Navigation View Item Selected Listener to handle the item click of the navigation menu
        navigationView.setNavigationItemSelectedListener
                (new NavigationView.OnNavigationItemSelectedListener() {

                    // This method will trigger on item Click of navigation menu
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {


                        //Checking if the item is in checked state or not, if not make it in checked state
                        if(menuItem.isChecked()) menuItem.setChecked(false);
                        else menuItem.setChecked(true);

                        //Closing drawer on item click
                        drawerLayout.closeDrawers();

                        //Check to see which item was being clicked and perform appropriate action
                        switch (menuItem.getItemId()){

                            case R.id.home:
                                HomeFragment homefragment = new HomeFragment();
                                android.support.v4.app.FragmentTransaction homeFragmentTransaction
                                        = getSupportFragmentManager().beginTransaction();
                                homeFragmentTransaction.replace(R.id.frame,homefragment);
                                homeFragmentTransaction.commit();
                                return true;
                            case R.id.action_settings:
//                                Fragment_Settings settingsfragment = new Fragment_Settings();
//                                android.support.v4.app.FragmentTransaction settingsFragmentTransaction
//                                        = getSupportFragmentManager().beginTransaction();
//                                settingsFragmentTransaction.replace(R.id.frame,settingsfragment);
//                                settingsFragmentTransaction.commit();
//                                return true;
//                                getFragmentManager().beginTransaction()
//                                        .replace(android.R.id.content, new Fragment_Settings()).addToBackStack(null)
//                                        .commit();
                                //return true;
                                Intent settings = new Intent(MainActivity.this, MyPreferenceActivity.class);
                                startActivity(settings);
                                //LoginManager.getInstance().logOut();
                                return true;

                            case R.id.action_logOut:
                                accountMail = "";
                                if (acct == null) {
                                    Intent turnLoginPage = new Intent(MainActivity.this, Login.class);
                                    startActivity(turnLoginPage);
                                    LoginManager.getInstance().logOut();
                                } else {
                                    Intent turnLoginPage = new Intent(MainActivity.this, Login.class);
                                    turnLoginPage.putExtra("logout", 1);
                                    startActivity(turnLoginPage);
                                }
                                return true;


                            default:
//                            Toast.makeText(getApplicationContext(),"Somethings Wrong",Toast.LENGTH_SHORT).show();
//                            return true;

//                                String[] id = getResources().getResourceName(menuItem.getItemId()).split("\\/");
//                                for(int i = 0; i<id.length; i++)
//                                for(int i = 0; i<addedHouse.size(); i++){
//                                    if(menuItem.getItem() = addedHouse.get(i))
//                                }
                                if(!addedHouse.isEmpty()){
                                    for(int i =0; i<addedHouse.size(); i++)
                                        if(addedHouse.get(i).equals(menuItem.getItemId())) {
                                            Intent intent = new Intent(MainActivity.this, ControlHouseActivity.class);
                                            startActivity(intent);
                                            return true;
                                        }
                                }
                             return false;
                        }
                    }
                });

        // Initializing Drawer Layout and ActionBarToggle
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        ActionBarDrawerToggle actionBarDrawerToggle
                = new ActionBarDrawerToggle(this,drawerLayout,toolbar,
                R.string.openDrawer, R.string.closeDrawer){

            @Override
            public void onDrawerClosed(View drawerView) {
                // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank
                super.onDrawerOpened(drawerView);
            }
        };


        //Setting the actionbarToggle to drawer layout
        drawerLayout.setDrawerListener(actionBarDrawerToggle);

        //calling sync state is necessay or else your hamburger icon wont show up
        actionBarDrawerToggle.syncState();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation_drawer, menu);
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
//            getFragmentManager().beginTransaction()
//                    .replace(android.R.id.content, new Fragment_Settings()).addToBackStack(null)
//                    .commit();
//            return true;
//            Fragment_Settings settingsfragment = new Fragment_Settings();
//            android.support.v4.app.FragmentTransaction settingsFragmentTransaction
//                    = getSupportFragmentManager().beginTransaction();
//            settingsFragmentTransaction.replace(R.id.frame,settingsfragment);
//            settingsFragmentTransaction.commit();
//            return true;
        }
        else if (id == R.id.action_logOut) {
            if (acct == null) {
                Intent turnLoginPage = new Intent(MainActivity.this, Login.class);
                startActivity(turnLoginPage);
                LoginManager.getInstance().logOut();
            } else {
                Intent turnLoginPage = new Intent(MainActivity.this, Login.class);
                turnLoginPage.putExtra("logout", 1);
                startActivity(turnLoginPage);
            }
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void requestEmail(AccessToken currentAccessToken) {
        GraphRequest request = GraphRequest.newMeRequest(currentAccessToken, new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                if (response.getError() != null) {
                    Toast.makeText(getApplicationContext(), response.getError().getErrorMessage(), Toast.LENGTH_LONG).show();
                    return;
                }
                try {
                    String email = object.getString("email");
                    //setEmail(email);
                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id, first_name, last_name, email, gender, birthday, location");
        request.setParameters(parameters);
        request.executeAsync();
    }
}
