package com.example.example;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
//Librerias para el video
import android.net.Uri;
import android.widget.MediaController;
import android.widget.VideoView;
//Importo librerias de video web
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;


public class NuevoUsuario extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.videos);

        //Configuracion del VideoView para reproducir un video local
        VideoView videoView = findViewById(R.id.videoView);
        String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.trailer;
        Uri uri = Uri.parse(videoPath);
        videoView.setVideoURI(uri);

        //Agregar controles de reproducción al VideoView
        MediaController mediaController = new MediaController(this);
        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);
        videoView.start();

        //Confiugracion del WebView para cargar un video de Youtube
        WebView webView = findViewById(R.id.webView);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        String videoUrl = "https://www.youtube.com/embed/RKHcFUPAWBY";
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(videoUrl);
    }
}
