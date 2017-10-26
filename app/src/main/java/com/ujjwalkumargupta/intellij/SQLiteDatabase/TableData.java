package com.ujjwalkumargupta.intellij.SQLiteDatabase;

import android.provider.BaseColumns;

/**
 * Created by TCL-MOBILE on 07-May-16.
 */
public class TableData {

    public TableData() {
    }

    public static abstract class TableInfo implements BaseColumns {
        // Database names
        public static final String DATABASE = "task_database";

        //Table Name
        public static final String TB_NAME_TASK = "task_table";
        // Favourite List Table Columns
        public static final String ID = "task_id";
        public static final String NAME = "task_name";
        public static final String DESCRIPTION = "task_description";
        public static final String CREATEDDATE = "created_date";
        public static final String UPDATEDDATE = "updated_date";

    }

}
