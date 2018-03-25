package com.example.pangestutitan.pangestutitan_1202154211_modul5;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private todoHelper dbHelper;
    private todoAdapter adapter;
    private String filter = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerview);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        populaterecyclerView(filter);

        swipe swipeToDelete = new swipe(MainActivity.this, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
        swipeToDelete.setLeftBackgroundColor(R.color.colorAccent);
        swipeToDelete.setRightBackgroundColor(R.color.colorPrimary);
        swipeToDelete.setLeftImg(R.drawable.ic_launcher_background);
        swipeToDelete.setRightImg(R.drawable.ic_launcher_background);
        swipeToDelete.setSwipetoDismissCallBack(getCallback(adapter));

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(swipeToDelete);
        itemTouchHelper.attachToRecyclerView(mRecyclerView);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, AddNewRecord.class);
                startActivity(i);
            }
        });
    }

    private void populaterecyclerView(String filter) {
        dbHelper = new todoHelper(this);
        adapter = new todoAdapter(dbHelper.todoList(filter), this, mRecyclerView);
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private swipe.SwipetoDismissCallBack getCallback(final todoAdapter adapter) {
        return new swipe.SwipetoDismissCallBack() {
            @Override
            public void onSwipedLeft(RecyclerView.ViewHolder viewholder) {
                adapter.remove(viewholder.getAdapterPosition());
            }

            @Override
            public void onSwipedRight(RecyclerView.ViewHolder viewHolder) {
                Toast.makeText(MainActivity.this, "Another or same action", Toast.LENGTH_SHORT).show();
            }
        };
    }

}
