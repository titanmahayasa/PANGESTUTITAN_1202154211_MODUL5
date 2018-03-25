package com.example.pangestutitan.pangestutitan_1202154211_modul5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class AddNewRecord extends AppCompatActivity {

    private EditText mNameEditText;
    private EditText mDescEditText;
    private EditText mPrioEditText;
    private Button mAddBtn;

    private todoHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_record);

        mNameEditText = (EditText)findViewById(R.id.id_name);
        mDescEditText = (EditText)findViewById(R.id.id_desc);
        mPrioEditText = (EditText)findViewById(R.id.id_prio);
        mAddBtn = (Button)findViewById(R.id.id_addtodo);

        mAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveTodo();
            }
        });
    }
    private void saveTodo(){
        String name = mNameEditText.getText().toString().trim();
        String description = mDescEditText.getText().toString().trim();
        String priority = mPrioEditText.getText().toString().trim();
        dbHelper = new todoHelper(this);

        if(name.isEmpty()){
            //error name is empty
            Toast.makeText(this, "You must enter a name", Toast.LENGTH_SHORT).show();
        }

        if(description.isEmpty()){
            //error name is empty
            Toast.makeText(this, "You must enter an age", Toast.LENGTH_SHORT).show();
        }

        if(priority.isEmpty()){
            //error name is empty
            Toast.makeText(this, "You must enter an occupation", Toast.LENGTH_SHORT).show();
        }


        //create new person
        Todo todo = new Todo(name, description, priority);
        dbHelper.saveNewTodo(todo);

        //finally redirect back home
        // NOTE you can implement an sqlite callback then redirect on success delete
        goBackHome();

    }

    private void goBackHome(){
        startActivity(new Intent(AddNewRecord.this, MainActivity.class));
    }
}

