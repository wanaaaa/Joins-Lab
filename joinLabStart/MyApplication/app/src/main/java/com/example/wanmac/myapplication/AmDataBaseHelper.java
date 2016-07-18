package com.example.wanmac.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ubun1604 on 7/16/16.
 */
public class AmDataBaseHelper extends SQLiteOpenHelper {
    public static final String EMPLOYEE_TABLE_NAME = "employee";
    public static final String COL_ID = "_id";
    public static final String COL_SSN = "ssn";
    public static final String COL_FNAME = "fname";
    public static final String COL_LNAME = "lname";
    public static final String COL_DBIRTH = "dbirth";
    public static final String COL_CITY = "city";

    public static final String DEPARTMENT_TABLE_NAME = "department";
    public static final String COL_DSSN = "ssn";
    public static final String COL_COMPANY = "company";
    public static final String COL_SALARY = "salary";
    public static final String COL_EXPERICENCE = "experience";

    private AmDataBaseHelper(Context context) {
        super(context, "db", null, 1);
    }

    private static AmDataBaseHelper INSTANCE;

    public static synchronized AmDataBaseHelper getINSTANCE(Context context) {
        if (INSTANCE == null)
            INSTANCE = new AmDataBaseHelper(context.getApplicationContext());
        return INSTANCE;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_ENTRIES_EMPLOYEE);
        sqLiteDatabase.execSQL("create table department (ssn text, company text, salary integer, experience integer)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int  oldVersion, int newVersion) {
        sqLiteDatabase.execSQL(SQL_DELETE_ENTRIES_EMPLOYEE);
        sqLiteDatabase.execSQL(SQL_DELETE_ENTRIES_DEPARTMENT);
        onCreate(sqLiteDatabase);
    }

    private static final String SQL_CREATE_ENTRIES_EMPLOYEE = "CREATE TABLE " +
            EMPLOYEE_TABLE_NAME + " (" +
            COL_ID +" INTEGER PRIMARY KEY, " +
            COL_SSN + " TEXT, "+
            COL_FNAME + " TEXT, " +
            COL_LNAME + " TEXT, " +
            COL_DBIRTH + " TEXT, " +
            COL_CITY + " TEXT"+")";

    private static final String SQL_CREATE_ENTRIES_DEPARTMENT = "CREATE TABLE " +
            DEPARTMENT_TABLE_NAME + " (" +
            COL_DSSN + " TEXT, " +
            COL_COMPANY + " TEXT, "+
            COL_SALARY + " INTEGER, " +
            COL_EXPERICENCE + " INTEGER )";
//            + ","+
//            "FOREIGN KEY("+COL_DSSN+ ")  REFERENCE " +  EMPLOYEE_TABLE_NAME +
//            "(" + COL_SSN + ") )";

    private static final String SQL_DELETE_ENTRIES_EMPLOYEE = "DROP TABLE IF EXISTS " +
            EMPLOYEE_TABLE_NAME;
    private static final String SQL_DELETE_ENTRIES_DEPARTMENT = "DROP TABLE IF EXISTS " +
            DEPARTMENT_TABLE_NAME;

    public void insertRowEmployee(DaEmployee employee) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_SSN, employee.getSSN());
        values.put(COL_FNAME, employee.getFname());
        values.put(COL_LNAME, employee.getLname());
        values.put(COL_DBIRTH, employee.getyBirth());
        values.put(COL_CITY, employee.getCity());
        db.insertOrThrow(EMPLOYEE_TABLE_NAME, null, values);
    }

    public void insertRowDepartment(DaDepartment department) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_DSSN, department.getSSN());
        values.put(COL_COMPANY, department.getCompany());
        values.put(COL_SALARY, department.getSalary());
        values.put(COL_EXPERICENCE, department.getExpericence());
        db.insertOrThrow(DEPARTMENT_TABLE_NAME, null ,values);
    }

    public String getNameCompanyJoins() {
        SQLiteDatabase dataBase = getReadableDatabase();
        String result = "";
        String query = "SELECT "+COL_LNAME+", "+COL_COMPANY+" FROM "+EMPLOYEE_TABLE_NAME+
                " JOIN "+DEPARTMENT_TABLE_NAME+" ON "+EMPLOYEE_TABLE_NAME+"."+COL_SSN+
                " = "+DEPARTMENT_TABLE_NAME+"."+COL_DSSN;
        Cursor cursor = dataBase.rawQuery(query, null);

        if(cursor.moveToFirst()){
            while (!cursor.isAfterLast()){
                result += cursor.getString(cursor.getColumnIndex(COL_LNAME)) + "/" +
                        cursor.getString(cursor.getColumnIndex(COL_COMPANY));
                cursor.moveToNext();
            }
        }
        cursor.close();

        return result;
    }

    public String checkDepartment() {
        SQLiteDatabase db = getReadableDatabase();
        String result = "";
        String query = "SELECT "+COL_COMPANY+" FROM "+ DEPARTMENT_TABLE_NAME;

        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        result = cursor.getString(cursor.getColumnIndex(COL_COMPANY));
        cursor.close();

        return result;
    }


}
