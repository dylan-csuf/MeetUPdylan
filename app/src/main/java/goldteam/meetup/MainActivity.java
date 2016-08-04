package goldteam.meetup;

/**
 * Created by c on 7/14/2016.
 */

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.pavelsikun.vintagechroma.ChromaDialog;
import com.pavelsikun.vintagechroma.IndicatorMode;
import com.pavelsikun.vintagechroma.OnColorSelectedListener;
import com.pavelsikun.vintagechroma.colormode.ColorMode;

import goldteam.meetup.fragments.BlogFragment;
import goldteam.meetup.fragments.FriendsFragment;
import goldteam.meetup.fragments.LoginFragment;
import goldteam.meetup.fragments.PendingFriendsFragment;
import goldteam.meetup.fragments.ProfileFragment;
import goldteam.meetup.statics.Constants;

public class MainActivity extends AppCompatActivity{

    private SharedPreferences pref;
    private int color = Color.BLUE;  //default color of the user interface
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pref = getPreferences(0);
        initToolBar();
        initFragment();

    }

    @Override
    protected void onStart() {
        int color = ContextCompat.getColor(getApplicationContext(), R.color.primary);
        super.onStart();
    }

    private void initFragment(){
        Fragment fragment;
        if(pref.getBoolean(Constants.IS_LOGGED_IN,false)){
            fragment = new FriendsFragment();
        }else {
            fragment = new LoginFragment();
        }
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_frame,fragment);
        ft.commit();
    }

    public void initToolBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        updateToolbar(color, color);

        /*toolbar.setNavigationIcon(R.drawable.ic_menu_white_24dp);
        toolbar.setNavigationOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "clicking the toolbar!", Toast.LENGTH_SHORT).show();
                    }
                }

        );*/
    }

    //updates the color of the user interface
    private void updateToolbar(int oldColor, int newColor) {
        final TransitionDrawable transition = new TransitionDrawable(new ColorDrawable[] {
                new ColorDrawable(oldColor), new ColorDrawable(newColor)
        });
        toolbar.setBackground(transition);
        transition.startTransition(300);
    }

    //provides support for recent android versions
    private int darkenColor(int color) {
        float[] hsv = new float[3];
        Color.colorToHSV(color, hsv);
        hsv[2] *= 0.8f;
        return Color.HSVToColor(hsv);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Fragment fragment;

        switch (item.getItemId()) {
            case R.id.profile_view:
                fragment = new ProfileFragment();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.fragment_frame,fragment);
                ft.commit();
                return true;

            case R.id.blog_view:
                fragment = new BlogFragment();
                FragmentTransaction blog_ft = getFragmentManager().beginTransaction();
                blog_ft.replace(R.id.fragment_frame,fragment);
                blog_ft.commit();
                return true;

            case R.id.logout:
                logout();
                return true;

            case R.id.Requests:
                fragment = new PendingFriendsFragment();
                FragmentTransaction lol = getFragmentManager().beginTransaction();
                lol.replace(R.id.fragment_frame,fragment);
                lol.commit();
                return true;

            case R.id.Colors: //Option to change color of user interface
                new ChromaDialog.Builder()
                        .initialColor(Color.BLUE)
                        .colorMode(ColorMode.RGB)
                        .indicatorMode(IndicatorMode.DECIMAL)
                        .onColorSelected(new OnColorSelectedListener() {
                            @Override
                            public void onColorSelected(@ColorInt int newColor) {
                                updateToolbar(color, newColor);
                                color = newColor;
                                getWindow().setStatusBarColor(darkenColor(newColor));
                            }
                        })
                        .create()
                        .show(getSupportFragmentManager(), "ChromaDialog");

                return true;

            case R.id.Friends:
                fragment = new FriendsFragment();
                FragmentTransaction f1t= getFragmentManager().beginTransaction();
                f1t.replace(R.id.fragment_frame,fragment);
                f1t.commit();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void logout() {
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean(Constants.IS_LOGGED_IN,false);
        editor.putString(Constants.EMAIL,"");
        editor.putString(Constants.NAME,"");
        editor.putString(Constants.UNIQUE_ID,"");
        editor.apply();
        goToLogin();
    }

    private void goToLogin(){

        Fragment login = new LoginFragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_frame,login);
        ft.commit();
    }
}
