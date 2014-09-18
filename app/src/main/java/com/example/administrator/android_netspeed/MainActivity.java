package com.example.administrator.android_netspeed;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.beardedhen.androidbootstrap.BootstrapButton;


public class MainActivity extends Activity{

    static MasterLayout masterLayout;   //Should be static


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final BootstrapButton button1 = (BootstrapButton)findViewById(R.id.btn1);
        final BootstrapButton button2  = (BootstrapButton) findViewById(R.id.btn2);
        final BootstrapButton button3  = (BootstrapButton) findViewById(R.id.btn3);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button1.setBootstrapType("success");
                button2.setBootstrapType("warning");
                button3.setBootstrapType("warning");
                Log.i("mine","click");
                new DownLoadSigTask().cancel(true);
                masterLayout.reset();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button1.setBootstrapType("warning");
                button2.setBootstrapType("success");
                button3.setBootstrapType("warning");
                Log.i("mine","click");
                new DownLoadSigTask().cancel(true);
                masterLayout.reset();
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button1.setBootstrapType("warning");
                button2.setBootstrapType("warning");
                button3.setBootstrapType("success");
                new DownLoadSigTask().cancel(true);
                masterLayout.reset();
            }
        });

        masterLayout = (MasterLayout) findViewById(R.id.MasterLayout01);

        //Onclick listener of the progress button
        masterLayout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                masterLayout.animation();   //Need to call this method for animation and progression
                if (masterLayout.flg_frmwrk_mode == 1) {
                    new DownLoadSigTask().execute();
                }
                if (masterLayout.flg_frmwrk_mode == 2) {
                    new DownLoadSigTask().cancel(true);
                    masterLayout.reset();
                }
                if (masterLayout.flg_frmwrk_mode == 3) {
                    new DownLoadSigTask().cancel(true);
                    masterLayout.reset();
                }
            }
        });
    }

    static class DownLoadSigTask extends AsyncTask<String, Integer, String> {
        @Override
        protected String doInBackground(final String... args) {
            //Creating dummy task and updating progress
            for (int i = 0; i <= 100; i++) {
                try {
                    Thread.sleep(50);

                } catch (InterruptedException e) {

                    e.printStackTrace();
                }
                publishProgress(i);

            }
            return null;
        }


        @Override
        protected void onProgressUpdate(Integer... progress) {
            masterLayout.cusview.setupprogress(progress[0]);
        }

    }
}
