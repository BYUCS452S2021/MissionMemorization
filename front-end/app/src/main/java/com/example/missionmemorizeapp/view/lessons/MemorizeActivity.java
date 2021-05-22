package com.example.missionmemorizeapp.view.lessons;

import android.os.Bundle;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.missionmemorizeapp.R;
import com.example.missionmemorizeapp.model.Project;

import java.util.ArrayList;
import java.util.List;

public class MemorizeActivity extends AppCompatActivity {
    public static final String PROJECT_ID = "com.example.missionmemorizeapp.PROJECT_ID";

    final int[] ORDER = {1, 5, 3, 7, 10, 2, 8, 6, 4, 9};

    private TextView referenceTitle;
    private TextView referenceBody;
    private SeekBar seekBar;
    private TextView seekDifficulty;
    private Button correctButton;
    private Button incorrectButton;

    private Project project;

    private List<Pair> pairs = new ArrayList<>();

    MemorizeActivity(Project project) {
        this.project = project;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memorize);

        referenceTitle = findViewById(R.id.reference);
        referenceTitle.setText(project.getProjectName());

        seekBar = findViewById(R.id.seekbar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                String hello = "hello";
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        referenceBody = findViewById(R.id.verseBody);

        seekDifficulty = findViewById(R.id.difficultyText);
        correctButton = findViewById(R.id.correctButton);
        incorrectButton = findViewById(R.id.incorrectButton);


    }

    class Pair {
        public int tag;
        public String word;

        Pair(int tag, String word) {
            this.tag = tag;
            this.word = word;
        }
    }

    private void tagWords() {
        String[] words = project.getProjectVerseString().split("\\s+");
        for (int i = 0; i < words.length; i++) {
            pairs.add(new Pair(ORDER[i % ORDER.length], words[i]));
        }
    }
}
