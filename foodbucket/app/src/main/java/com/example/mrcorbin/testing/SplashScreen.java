package com.example.mrcorbin.testing;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;

public class SplashScreen extends AppCompatActivity {

    //private ProgressBar progress;
    private int progressValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);


        //progress = findViewById(R.id.progress_bar);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                work();
                startApps();
            }
        });

        thread.start();

    }

    public void work(){

        for (progressValue = 50 ; progressValue<=100 ; progressValue=progressValue+50){
            try{
                Thread.sleep(1000);
               // progress.setProgress(progressValue);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }


    }
    public void startApps(){
        Intent intent = new Intent(SplashScreen.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}
