package com.demo.obd;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
    private SpeedBoard mRoundProgressBar1;
    private int progress=0;
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        mRoundProgressBar1 = (SpeedBoard) findViewById(R.id.roundProgressBar1);


        ((Button)findViewById(R.id.button2)).setOnClickListener(new View.OnClickListener() {
            @Override
        public void onClick(View v){
                mRoundProgressBar1.setProgress(0);
                progress=0;
            }

        });


        ((Button)findViewById(R.id.button1)).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {

                    @Override
                    public void run() {
                        while (progress <= 100) {
                            progress += 3;

                            System.out.println(progress);

                            mRoundProgressBar1.setProgress(progress);

                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }

                    }
                }).start();
            }
        });

    }
}
