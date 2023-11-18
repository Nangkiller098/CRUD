package com.example.crud.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.crud.models.Category;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String NAME = "NAME";
    public static final String ID = "ID";
    public static final String CATEGORY_DB = "category_db";
    public static final String CATEGORY_TABLE = "CATEGORY_TABLE";

    public DatabaseHelper(@Nullable Context context) {
        super(context, CATEGORY_DB, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createStatement = "CREATE TABLE " + CATEGORY_TABLE + " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME + " TEXT)";
        sqLiteDatabase.execSQL(createStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE " + CATEGORY_TABLE);
    }

    public boolean create(Category person) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME, person.getName());
        return db.insert(CATEGORY_TABLE, null, contentValues) != -1;
    }

    public boolean update(Category person) {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME, person.getName());
        long result = db.update(CATEGORY_TABLE, contentValues, ID + " = " + person.getId(), null);
        return result != -1;
    }

    public List<Category> getAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        List<Category> categoryList = new ArrayList<>();
        String sql = "SELECT * FROM " + CATEGORY_TABLE;
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                categoryList.add(new Category(id, name));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return categoryList;
    }

    public Category getById(Integer id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Category category = new Category();
        String sql = "SELECT * FROM " + CATEGORY_TABLE + " WHERE ID='" + id + "'";
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                int catId = cursor.getInt(0);
                String name = cursor.getString(1);
                category = new Category(catId, name);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return category;
    }


}
