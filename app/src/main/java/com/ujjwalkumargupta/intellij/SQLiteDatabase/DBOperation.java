package com.ujjwalkumargupta.intellij.SQLiteDatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.ujjwalkumargupta.intellij.ModelClasses.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TCL-MOBILE on 11-Aug-16.
 */
public class DBOperation extends SQLiteOpenHelper {
    private static final String TAG = "DBOperation";
    private static final int database_version = 1;
    private static final String TABLE_INFO = "";
    private static SQLiteDatabase sqlDB;
    private static ContentValues contentValues;

    public String CREATE_TASK_LIST_TABLE = "CREATE TABLE "
            + TableData.TableInfo.TB_NAME_TASK + "("
            + TableData.TableInfo.ID + " INTEGER PRIMARY KEY NOT NULL,"
            + TableData.TableInfo.NAME + " TEXT,"
            + TableData.TableInfo.DESCRIPTION + " TEXT,"
            + TableData.TableInfo.CREATEDDATE + " TEXT,"
            + TableData.TableInfo.UPDATEDDATE + " TEXT);";

    public DBOperation(Context context) {
        super(context, TableData.TableInfo.TB_NAME_TASK, null, database_version);
    }

    @Override
    public void onCreate(SQLiteDatabase SQdb) {
        SQdb.execSQL(CREATE_TASK_LIST_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INFO);
        onCreate(db);
    }

    // Insert new task into sqlite database
    public void insertNewTask(String name, String desc, String createdDate) {
        sqlDB = this.getWritableDatabase();
        contentValues = new ContentValues();
        contentValues.put(TableData.TableInfo.NAME, name);
        contentValues.put(TableData.TableInfo.DESCRIPTION, desc);
        contentValues.put(TableData.TableInfo.CREATEDDATE, createdDate);
        sqlDB.insert(TableData.TableInfo.TB_NAME_TASK, null, contentValues);
        sqlDB.close();
    }

    // Insert updated task into sqlite database
    public void updateTask(int taskId, String name, String desc, String updatedDate) {
        SQLiteDatabase db = this.getWritableDatabase();
        contentValues = new ContentValues();
        contentValues.put(TableData.TableInfo.NAME, name);
        contentValues.put(TableData.TableInfo.DESCRIPTION, desc);
        contentValues.put(TableData.TableInfo.UPDATEDDATE, updatedDate);

        // updating row
        db.update(TableData.TableInfo.TB_NAME_TASK, contentValues, TableData.TableInfo.ID + " = ?",
                new String[] { String.valueOf(taskId) });
    }

    // Deleting single contact
    public void deleteContact(List<Task> tasks) {
        SQLiteDatabase db = this.getWritableDatabase();
        for (int counter = 0; counter < tasks.size(); counter++) {
            db.delete(TableData.TableInfo.TB_NAME_TASK, TableData.TableInfo.ID + " = ?",
                    new String[]{String.valueOf(tasks.get(counter).getTaskId())});
        }
        db.close();
    }

    // Retrieve Task's from Database
    public List<Task> getTasks() {
        List<Task> taskList = new ArrayList<>();
        sqlDB = this.getReadableDatabase();
        // Select Query
//        String selectQuery = "SELECT " + TableData.TableInfo.NAME + ", " +
//                TableData.TableInfo.DESCRIPTION + ", " +
//                TableData.TableInfo.CREATEDDATE + ", " +
//                TableData.TableInfo.UPDATEDDATE + " FROM " +
//                TableData.TableInfo.TB_NAME_TASK;

        // Select All Query
        String selectQuery = "SELECT  * FROM " + TableData.TableInfo.TB_NAME_TASK;

        Cursor cursor = sqlDB.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Task task = new Task();
                task.setTaskId(Integer.parseInt(cursor.getString(0)));
                task.setTaskName(cursor.getString(1));
                task.setTaskDescription(cursor.getString(2));
                task.setTaskCreated(cursor.getString(3));
                task.setTaskUpdated(cursor.getString(4));
                taskList.add(task);
            } while (cursor.moveToNext());
        }
        return taskList;
    }

}
