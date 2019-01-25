package com.blueskylinks.sql_example;

/**
 * Created by omsai on 2/5/2018.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class myDbAdapter {
    myDbHelper myhelper;
    Context mcontext;

    public myDbAdapter(Context context) {
        mcontext=context;
    }

    public long insertData(String name, String pass) {
        myhelper = new myDbHelper(mcontext);
        SQLiteDatabase dbb = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.NAME, name);
        contentValues.put(myDbHelper.MyPASSWORD, pass);
        long id = dbb.insert(myDbHelper.TABLE_NAME, null, contentValues);
        return id;
    }

    public String getData(String sname) {
        myhelper = new myDbHelper(mcontext);
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] columns = {myDbHelper.UID, myDbHelper.NAME, myDbHelper.MyPASSWORD};
        String[] whereArg={sname};
        Cursor cursor = db.query(myDbHelper.TABLE_NAME, columns, myDbHelper.NAME+" = ?",whereArg, null, null, null, null);
        StringBuffer buffer = new StringBuffer();
        Cursor cursor1 = db.rawQuery("SELECT SUM(" + myDbHelper.UID + ") as Total FROM " + myDbHelper.TABLE_NAME, null);

        while (cursor.moveToNext()) {
            int cid = cursor.getInt(cursor.getColumnIndex(myDbHelper.UID));
            String name = cursor.getString(cursor.getColumnIndex(myDbHelper.NAME));
            String password = cursor.getString(cursor.getColumnIndex(myDbHelper.MyPASSWORD));

            buffer.append(cid + "   " + name + "   " + password + " \n");
        }
        if (cursor1.moveToFirst()) {
            int total = cursor1.getInt(cursor1.getColumnIndex("Total"));
            buffer.append("total"+""+total);
        }

        return buffer.toString();
    }
/*    public  int delete(String uname)
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] whereArgs ={uname};

        int count =db.delete(myDbHelper.TABLE_NAME ,myDbHelper.NAME+" = ?",whereArgs);
        return  count;
    }*/

    public int updateName(String oldName , String newName)
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.NAME,newName);
        String[] whereArgs= {oldName};
        int count =db.update(myDbHelper.TABLE_NAME,contentValues, myDbHelper.NAME+" = ?",whereArgs );
        return count;
    }


    static class myDbHelper extends SQLiteOpenHelper {
        private static final String DATABASE_NAME = "myDatabase";    // Database Name
        private static final String TABLE_NAME = "myTable";   // Table Name
        private static final int DATABASE_Version = 1;    // Database Version
        private static final String UID = "_id";     // Column I (Primary Key)
        private static final String NAME = "Name";    //Column II

        private static final String MyPASSWORD = "ContactNumber";    // Column III
        private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME +
                " (" + UID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME + " VARCHAR(255) ," + MyPASSWORD + " VARCHAR(225));";
        private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
        public Context context;

        public myDbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_Version);
           // this.context = context;
        }

        public void onCreate(SQLiteDatabase db) {

            try {
                db.execSQL(CREATE_TABLE);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try {
                Toast.makeText(context, "Onupgrade", Toast.LENGTH_SHORT).show();
                db.execSQL(DROP_TABLE);
                onCreate(db);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
