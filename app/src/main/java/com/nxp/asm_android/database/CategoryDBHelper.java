//package com.nxp.asm_android.database;
//
//import android.content.ContentValues;
//import android.content.Context;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//
//public class CategoryDBHelper extends SQLiteOpenHelper {
//
//    public static final String DB_NAME = "ASM";
//    public static final int DB_VERSION = 1;
//
//    public static final String TABLE_NAME = "TBL_USER";
//
//    public CategoryDBHelper(Context context) {
//        super(context, DB_NAME, null, DB_VERSION);
//    }
//
//    @Override
//    public void onCreate(SQLiteDatabase sqLiteDatabase) {
//        String sql = "CREATE TABLE " + TABLE_NAME + " ( "
//                + ID + " INTEGER PRIMARY KEY, "
//                + NAME + " TEXT, "
//                + GENDER + " TEXT, "
//                + DES + " TEXT)";
//        sqLiteDatabase.execSQL(sql);
//    }
//
//
//    @Override
//    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
//        String sql = "DROP TABLE IF EXISTS " + TABLE_NAME;
//        sqLiteDatabase.execSQL(sql);
//        onCreate(sqLiteDatabase);
//    }
//
//    public String addUser(String name, String gender, String desc) {
//        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(NAME, name);
//        contentValues.put(GENDER, gender);
//        contentValues.put(DES, desc);
//        long idAdd = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
//        if (idAdd == -1) {
//            return "add fail";
//        }
//        sqLiteDatabase.close();
//        return "add success";
//    }
//
//    public String updateUser(int id, String name, String gender, String desc) {
//        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(NAME, name);
//        contentValues.put(GENDER, gender);
//        contentValues.put(DES, desc);
//        long idUpdate = sqLiteDatabase.update(TABLE_NAME, contentValues, ID + " = ?", new String[]{id + ""});
//        if (idUpdate > 0) {
//            return "update ok";
//        }
//        sqLiteDatabase.close();
//        return "update fail";
//    }
//
//    public String deleteUser(int id) {
//        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
//        long isDelete = sqLiteDatabase.delete(TABLE_NAME, ID + " = ?", new String[]{id + ""});
//        if (isDelete > 0) {
//            return "delete ok";
//        }
//        sqLiteDatabase.close();
//        return "delete fail";
//    }
//
//    public Cursor getAllUser() {
//        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
//        String sql = "SELECT * FROM " + TABLE_NAME;
//        Cursor c = sqLiteDatabase.rawQuery(sql, null);
//        return c;
//    }
//}
