package com.example.missionmemorizeapp.view.lessons;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.missionmemorizeapp.R;
import com.example.missionmemorizeapp.model.Project;
import com.example.missionmemorizeapp.view.dialogs.ProfileInfoDialog;
import com.example.missionmemorizeapp.view.dialogs.ResultsDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MemorizeFragment extends Fragment {


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

    public MemorizeFragment(Project project) {
        this.project = project;
        tagWords();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_memorize, container, false);

        referenceTitle = view.findViewById(R.id.reference);
        referenceTitle.setText(project.getProjectName());

        seekBar = view.findViewById(R.id.seekbar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                referenceBody.setText(blankWords(progress));
                seekDifficulty.setText(String.valueOf(seekBar.getProgress()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        referenceBody = view.findViewById(R.id.verseBody);
        referenceBody.setText(blankWords(seekBar.getProgress()));

        seekDifficulty = view.findViewById(R.id.difficultyText);
        seekDifficulty.setText(String.valueOf(seekBar.getProgress()));

        correctButton = view.findViewById(R.id.correctButton);
        correctButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO UpdateProjectTask
                // set bundle and bring up ResultsDialog
                ResultsDialog resultsDialog = new ResultsDialog(true);
                resultsDialog.show(getChildFragmentManager(), "MyFragment");
            }
        });

        incorrectButton = view.findViewById(R.id.incorrectButton);
        incorrectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO UpdateProjectTask
                // set bundle and bring up ResultsDialog
                ResultsDialog resultsDialog = new ResultsDialog(false);
                resultsDialog.show(getChildFragmentManager(), "MyFragment");
            }
        });

        return view;
    }

    private void tagWords() {
        String[] words = project.getProjectVerseString().split("\\s+");
        for (int i = 0; i < words.length; i++) {
            pairs.add(new Pair(ORDER[i % ORDER.length], words[i]));
        }
    }

    class Pair {
        public int tag;
        public String word;

        Pair(int tag, String word) {
            this.tag = tag;
            this.word = word;
        }
    }

    private String blankWords(int position) {
        StringBuilder builder = new StringBuilder();
        for (Pair pair : pairs) {
            if (pair.tag <= position) {
                if (pair.word.length() <= 2)
                    builder.append("__");
                else if (pair.word.length() <= 5)
                    builder.append("____");
                else
                    builder.append("______");
            }
            else
                builder.append(pair.word);

            builder.append(' ');
        }
        return builder.toString();
    }

}
