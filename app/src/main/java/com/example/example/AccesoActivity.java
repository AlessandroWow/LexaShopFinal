package com.example.example;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;


import androidx.appcompat.app.AppCompatActivity;


public class AccesoActivity extends AppCompatActivity{
    private ImageView imageView;
    private ImageView imageView2;
    private ProgressBar progressBar;
    private ProgressBar progressBar2;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acceso);

        imageView = findViewById(R.id.lexita);
        imageView2 = findViewById(R.id.ravencita);
        progressBar = findViewById(R.id.progressBar);
        progressBar2 = findViewById(R.id.progressBar2);

        findViewById(R.id.megusta).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    MediaPlayer mediaPlayer = MediaPlayer.create(AccesoActivity.this, R.raw.sigusta);
                    mediaPlayer.start();

                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener(){
                        @Override
                        public void onCompletion(MediaPlayer mp){
                            mp.release();
                        }
                    });

            }
        });


        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }

                runOnUiThread(new Runnable(){
                    @Override
                    public void run(){
                        progressBar.setVisibility(View.GONE);
                        progressBar2.setVisibility(View.GONE);
                        imageView.setVisibility(View.VISIBLE);
                        imageView2.setVisibility(View.VISIBLE);
                        //imageView.setImageResource(R.drawable.lexa);

                    }

                });

            }

        });
        thread.start();
    }


    public void onClickVentana(View view){
        Intent intent = new Intent(this, AccesoActivity.class);
        startActivity(intent);
    }

}
