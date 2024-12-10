package com.example.virtualpiano;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // List to hold active MediaPlayer instances
    private List<MediaPlayer> mediaPlayers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mediaPlayers = new ArrayList<>();

        // Initialisation des boutons
        Button noteC = findViewById(R.id.c);
        Button noteD = findViewById(R.id.d);
        Button noteE = findViewById(R.id.e);
        Button noteF = findViewById(R.id.f);
        Button noteG = findViewById(R.id.g);
        Button noteA = findViewById(R.id.a);
        Button noteB = findViewById(R.id.b);
        
        Button noteCSharp = findViewById(R.id.c_black);
        Button noteDSharp = findViewById(R.id.d_black);
        Button noteESharp = findViewById(R.id.e_black);
        Button noteFSharp = findViewById(R.id.f_black);
        Button noteASharp = findViewById(R.id.a_black);
        Button noteBSharp = findViewById(R.id.b_black);

        // White keys events

        noteC.setOnClickListener(view -> playSound(R.raw.doo));
        noteD.setOnClickListener(view -> playSound(R.raw.re));
        noteE.setOnClickListener(view -> playSound(R.raw.mi));
        noteF.setOnClickListener(view -> playSound(R.raw.fa));
        noteG.setOnClickListener(view -> playSound(R.raw.sol));
        noteA.setOnClickListener(view -> playSound(R.raw.la));
        noteB.setOnClickListener(view -> playSound(R.raw.si));

        // Black keys events
        noteCSharp.setOnClickListener(view -> playSound(R.raw.cc));
        noteDSharp.setOnClickListener(view -> playSound(R.raw.dd));
        noteFSharp.setOnClickListener(view -> playSound(R.raw.ff));
        noteBSharp.setOnClickListener(view -> playSound(R.raw.bb));
        noteASharp.setOnClickListener(view -> playSound(R.raw.aa));    }

    // Play sound method with improved handling for multiple buttons being pressed simultaneously
    private void playSound(int soundResource) {
        // Create a new MediaPlayer for each sound to be played simultaneously
        MediaPlayer mediaPlayer = MediaPlayer.create(this, soundResource);

        // Add the mediaPlayer to the list of active players
        mediaPlayers.add(mediaPlayer);

        mediaPlayer.setOnCompletionListener(mp -> {
            mp.release();  // Release MediaPlayer once the sound finishes
            mediaPlayers.remove(mp);  // Remove from active players list
        });

        // Start playing the sound
        mediaPlayer.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // Release all MediaPlayer instances to prevent memory leaks
        for (MediaPlayer mp : mediaPlayers) {
            if (mp != null) {
                mp.release();
            }
        }
        mediaPlayers.clear();  // Clear the list of active media players
    }
}
