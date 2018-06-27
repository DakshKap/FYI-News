package co.fyinews.fyinewsapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashActivity extends AppCompatActivity {

    private ImageView imgView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();
        imgView = (ImageView) findViewById(R.id.splashLogo);
        Animation anim = AnimationUtils.loadAnimation(this,R.anim.splash_anim);
        imgView.startAnimation(anim);
        final Intent intent = new Intent(this, MainActivity.class);
        Thread timer = new Thread(){
            public void run(){
                try {
                    sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    startActivity(intent);
                    finish();
                }
            }
        };

        timer.start();
    }
}
