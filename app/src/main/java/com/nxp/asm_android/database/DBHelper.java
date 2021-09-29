package com.nxp.asm_android.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "ASM";
    public static final int DB_VERSION = 1;

    public static String TABLE_NAME = "TBL_SPENDING";
    public static String ID = "id";
    public static String NAME = "name";
    public static String DETAIL = "detail";
    public static String DES = "des";
    public static String AMOUNT = "amount";
    public static String TIME = "time";
    public static String CATEGORY = "cate";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE Category ( "
                + ID + " INTEGER PRIMARY KEY, "
                + "category_name TEXT)";
        sqLiteDatabase.execSQL(sql);
        String sql2 = "CREATE TABLE Spending ( "
                + ID + " INTEGER PRIMARY KEY, "
                + NAME + " TEXT, "
                + "description TEXT, "
                + "detail TEXT, "
                + "amount INTEGER, "
                + "spendTime TEXT, "
                + "categoryId INTEGER, "
                + "categoryName TEXT)";
        sqLiteDatabase.execSQL(sql2);
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String sql = "DROP TABLE IF EXISTS " + TABLE_NAME;
        sqLiteDatabase.execSQL(sql);
        onCreate(sqLiteDatabase);
    }

    public String addSpending(String name, String detail, Integer amount,
                              String spendTime, Integer categoryId, String desc, String categoryName) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME, name);
        contentValues.put("detail", detail);
        contentValues.put(DES, desc);
        contentValues.put("amount", amount);
        contentValues.put("spendTime", spendTime);
        contentValues.put("categoryId", categoryId);
        contentValues.put("categoryName", categoryName);
        contentValues.put(DES, desc);
        long idAdd = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
        if (idAdd == -1) {
            return "add fail";
        }
        sqLiteDatabase.close();
        return "add success";
    }

    public String updateUser(int id, String name, String detail, Integer amount,
                             String spendTime, Integer categoryId, String desc, String categoryName) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME, name);
        contentValues.put("detail", detail);
        contentValues.put(DES, desc);
        contentValues.put("amount", amount);
        contentValues.put("spendTime", spendTime);
        contentValues.put("categoryId", categoryId);
        contentValues.put("categoryName", categoryName);
        long idUpdate = sqLiteDatabase.update(TABLE_NAME, contentValues, ID + " = ?", new String[]{id + ""});
        if (idUpdate > 0) {
            return "update ok";
        }
        sqLiteDatabase.close();
        return "update fail";
    }

    public String deleteUser(int id) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        long isDelete = sqLiteDatabase.delete(TABLE_NAME, ID + " = ?", new String[]{id + ""});
        if (isDelete > 0) {
            return "delete ok";
        }
        sqLiteDatabase.close();
        return "delete fail";
    }

    public Cursor getAllUser() {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_NAME;
        Cursor c = sqLiteDatabase.rawQuery(sql, null);
        return c;
    }

    public Cursor getAllCategory() {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String sql = "SELECT * FROM Category";
        Cursor c = sqLiteDatabase.rawQuery(sql, null);
        return c;
    }

    public void initCategory() {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", 1);
        contentValues.put("categoryName", "shopping");
        sqLiteDatabase.insert("Category", null, contentValues);

        ContentValues contentValues2 = new ContentValues();
        contentValues.put("id", 2);
        contentValues.put("categoryName", "home");
        sqLiteDatabase.insert("Category", null, contentValues2);

        ContentValues contentValues3 = new ContentValues();
        contentValues.put("id", 3);
        contentValues.put("categoryName", "food");
        sqLiteDatabase.insert("Category", null, contentValues3);
    }
}
