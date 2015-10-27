package bbsk.chartinity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.database.Cursor;

/**
 * Created by simeonkostadinov on 25/10/15.
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    /**
     * Connection with SQLite database
     */

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "signUpForm.db",
    TABlE_NAME = "signUpFormTable",
    COLUMN_ID = "id",
    COLUMN_USERNAME = "username",
    COLUMN_NAME = "name",
    COLUMN_EMAIL = "email",
    COLUMN_PASSWORD = "password",
    TABLE_CONFIG = "CREATE TABLE " + TABlE_NAME + "(" +
                    COLUMN_ID + " INTEGER PRIMARY KEY," +
                    COLUMN_USERNAME + " TEXT," +
                    COLUMN_NAME + " TEXT," +
                    COLUMN_EMAIL + " TEXT," +
                    COLUMN_PASSWORD + " TEXT)";

    SQLiteDatabase db;


    public DatabaseHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CONFIG);
        this.db = db;
    }

    public void insertSignUpForm(SignUpForm form){

        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        String query = "SELECT * FROM " + TABlE_NAME;
        Cursor cursor = db.rawQuery(query, null);

        int count = cursor.getCount();

        values.put(COLUMN_ID, count);
        values.put(COLUMN_USERNAME, form.getUsername());
        values.put(COLUMN_NAME, form.getName());
        values.put(COLUMN_EMAIL, form.getEmail());
        values.put(COLUMN_PASSWORD, form.getPassword());

        db.insert(TABlE_NAME, null, values);
        db.close();
    }

    public String passwordMatching(String username){

        db = this.getReadableDatabase();
        String query = "SELECT " + COLUMN_USERNAME + ", " + COLUMN_PASSWORD + " FROM " + TABlE_NAME;
        Cursor cursor = db.rawQuery(query, null);

        String user, password;
        password = "Password not found";

        if(cursor.moveToFirst()){
            do{
                user = cursor.getString(0);

                if(user.equals(username)){
                    password = cursor.getString(1);
                    break;
                }
            }
            while(cursor.moveToNext());
        }

        return password;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP DATABASE IF EXISTS " + DATABASE_NAME;
        db.execSQL(query);
        this.onCreate(db);
    }
}
