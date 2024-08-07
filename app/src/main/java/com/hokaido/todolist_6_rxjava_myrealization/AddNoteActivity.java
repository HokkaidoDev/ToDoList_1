package com.hokaido.todolist_6_rxjava_myrealization;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

public class AddNoteActivity extends AppCompatActivity {

    private EditText editTextNote;
    private RadioButton radioButtonLow;
    private RadioButton radioButtonMedium;
    private Button buttonSave;

    private AddNoteViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        initView();

        viewModel = new ViewModelProvider(this).get(AddNoteViewModel.class);
        viewModel.shouldCloseScreen().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean shouldClose) {
                if(shouldClose){
                    finish();
                }
            }
        });

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveNote();
            }
        });
    }

    private void initView(){
        editTextNote = findViewById(R.id.editTextNote);
        radioButtonLow = findViewById(R.id.radioButtonLow);
        radioButtonMedium = findViewById(R.id.radioButtonMedium);
        buttonSave = findViewById(R.id.buttonSave);
    }

    public static Intent newIntent(Context context){
        Intent intent = new Intent(context, AddNoteActivity.class);
        return intent;
    }

    private void saveNote(){
        String text = editTextNote.getText().toString();
        if(text.isEmpty()){
            Toast.makeText(this, R.string.error_field_empty, Toast.LENGTH_SHORT).show();
            //Snackbar.make( findViewById(R.id.main), R.string.error_field_empty, Snackbar.LENGTH_SHORT).show();
        }else{
            int priority = getPriority();
            Note note = new Note(text, priority);
            viewModel.showNotes(note);
        }
    }

    private int getPriority(){
        int priority;
        if(radioButtonLow.isChecked()){
            priority = 0;
        } else if (radioButtonMedium.isChecked()) {
            priority = 1;
        }else{
            priority = 2;
        }
        return priority;
    }
}