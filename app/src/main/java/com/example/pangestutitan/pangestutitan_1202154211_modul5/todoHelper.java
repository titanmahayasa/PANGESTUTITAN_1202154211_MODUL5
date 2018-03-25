package com.example.pangestutitan.pangestutitan_1202154211_modul5;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Pangestu Titan on 3/25/2018.
 */

public class todoHelper extends SQLiteOpenHelper{

    public static final String DATABASE_NAME = "todo.db";
    private static final int DATABASE_VERSION = 1 ;
    public static final String TABLE_NAME = "todo_list";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_DESCRIPTON = "description";
    public static final String COLUMN_PRIORITY = "priority";



    public todoHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(" CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT NOT NULL, " +
                COLUMN_DESCRIPTON + " TEXT NOT NULL, " +
                COLUMN_PRIORITY + " NUMBER NOT NULL);"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME );
        this.onCreate(db);
    }

    public void saveNewTodo (Todo todo){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, todo.getName());
        values.put(COLUMN_DESCRIPTON, todo.getDesc());
        values.put(COLUMN_PRIORITY, todo.getPrio());


        db.insert(TABLE_NAME,null, values);
        db.close();
    }

    public List<Todo> todoList(String filter){
        String query;
        if(filter.equals("")){
            //regular query
            query = "SELECT  * FROM " + TABLE_NAME;
        }else{
            //filter results by filter option provided
            query = "SELECT  * FROM " + TABLE_NAME + " ORDER BY "+ filter;
        }

        List<Todo> todoLinkedList = new LinkedList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Todo todo;

        if (cursor.moveToFirst()) {
            do {
                todo = new Todo();

                todo.setId(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)));
                todo.setName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)));
                todo.setDesc(cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTON)));
                todo.setPrio(cursor.getString(cursor.getColumnIndex(COLUMN_PRIORITY)));
                todoLinkedList.add(todo);
            } while (cursor.moveToNext());
        }

        return todoLinkedList;
    }

    public Todo getTodo(long id){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT  * FROM " + TABLE_NAME + " WHERE _id="+ id;
        Cursor cursor = db.rawQuery(query, null);

        Todo receivedTodo = new Todo();
        if(cursor.getCount() > 0) {
            cursor.moveToFirst();
            receivedTodo.setName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)));
            receivedTodo.setDesc(cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTON)));
            receivedTodo.setPrio(cursor.getString(cursor.getColumnIndex(COLUMN_PRIORITY)));
        }

        return receivedTodo;

    }

    public void deleteTodoRecord(long id, Context context){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM "+TABLE_NAME+" WHERE _id='"+id+"'");
        Toast.makeText(context, "Deleted successfully.", Toast.LENGTH_SHORT).show();
    }

}
