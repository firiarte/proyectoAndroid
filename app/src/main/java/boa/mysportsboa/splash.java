package boa.mysportsboa;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Window;

/**
 * Created by Gary on 12/04/15.
 */
public class splash extends Activity {

    protected boolean active = true;
    protected int splashTime = 1000;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.splash);

        Thread splashThread = new Thread(){
            @Override
            public void run(){
                try
                {
                    Thread.sleep(2000);
                }
                catch(Exception e){}
                openApp();
            }
        };
        splashThread.start();
    }

    private void openApp(){
        finish();
        startActivity(new Intent(this,MainActivity.class));
    }

}
