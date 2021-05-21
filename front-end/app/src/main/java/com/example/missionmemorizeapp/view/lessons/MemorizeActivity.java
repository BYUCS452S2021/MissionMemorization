package com.example.missionmemorizeapp.view.lessons;

import android.os.Bundle;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.missionmemorizeapp.R;

public class MemorizeActivity extends AppCompatActivity {
    public static final String PROJECT_ID = "com.example.missionmemorizeapp.PROJECT_ID";

    private TextView referenceTitle;
    private TextView referenceBody;
    private SeekBar seekBar;
    private TextView seekDifficulty;
    private Button correctButton;
    private Button incorrectButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memorize);

        referenceTitle = findViewById(R.id.reference);
        referenceBody = findViewById(R.id.verseBody);
        seekBar = findViewById(R.id.seekbar);
        seekDifficulty = findViewById(R.id.difficultyText);
        correctButton = findViewById(R.id.correctButton);
        incorrectButton = findViewById(R.id.incorrectButton);

        //  TODO: HOW I SET UP PASSING INTO ACTIVITY
        String projectID = getIntent().getStringExtra(PROJECT_ID);

        // TODO: Get body and ref data

        // TODO: Set values to data

        // TODO: Set up seek Bar

        // TODO: Set up algo to get rid of words

        //TODO: add a toggle
            /*
            Use getWordCount

            HAVE A toggle that toggles showing whole word or first letter

            %'s of wording:
            0 = show
            1 = Hide 10%
            2 = Hide 20%
            3 =
            4 =
            5 =
            6 =
            7 =
            8 = Hide 70%
            9 = Hide 80% - only show first letter for rest
            10 = Hide 100%

             */
    }

    public static int getWordCount(String verse) {
        if (verse == null || verse.isEmpty()) {
            return 0;
        }
        String[] words = verse.split("\\s+");
        return words.length;
    }

}
