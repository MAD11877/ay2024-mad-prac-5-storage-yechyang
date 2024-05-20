package sg.edu.np.mad.madpractical5;

import static android.content.ContentValues.TAG;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.Random;

public class DBhandler extends SQLiteOpenHelper{
    private static final int DATABASE_Version = 1;
    private static final String DATABASE_NAME = "users.db";
    private static final String TABLE_USER = "users";
    private static final String name = "names";
    private static final String description = "description";
    private static final String id = "_id";
    private static final String followed = "followed";

    public DBhandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_Version);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER
                + " (" + id + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + name + " TEXT, "
                + description + " TEXT, "
                + followed + " INTEGER " + ")";
        db.execSQL(CREATE_USER_TABLE);
        addProduct(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        Log.d(TAG, "Database upgraded from version " + oldVersion + " to " + newVersion);
        onCreate(db);
    }

    public void addProduct(SQLiteDatabase db) {
        Log.d(TAG, "Adding products to the database.");
        Random random = new Random();
        for (int i = 1; i <= 20; i++) {
            ContentValues values = new ContentValues();
            values.put(name, "Name" + random.nextInt(99999999));
            values.put(description, "Description " + random.nextInt(99999999));
            values.put(followed, random.nextInt(2));
            db.insert(TABLE_USER, null, values);
        }
    }

//    public void addProduct() {
//        SQLiteDatabase db = this.getWritableDatabase();
//        Log.d("DBHandler", "Adding products to the database.");
//        Random random = new Random();
//        for (int i = 1; i <= 20; i++) {
//            ContentValues values = new ContentValues();
//            values.put("_id", i);
//            values.put(name, "Name" + random.nextInt(99999999));
//            values.put(description, "Description " + random.nextInt(99999999));
//            values.put(followed, new Random().nextInt(2));
//            db.insert(TABLE_USER, null, values);
//        }
//        db.close();
//    }

    public ArrayList<User> getUsers() {
        ArrayList<User> userList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_USER;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.id = Integer.parseInt(cursor.getString(0));
                user.name = cursor.getString(1);
                user.description = cursor.getString(2);
                user.followed = cursor.getInt(3) == 1;
                userList.add(user);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return userList;
    }

    public void updateUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(name, user.getName());
        values.put(description, user.getDescription());
        values.put(followed, user.getFollowed() ? 1 : 0);
        db.update(TABLE_USER, values, id + " = ?", new String[]{String.valueOf(user.getId())});
        db.close();
    }

//    public void deleteAllUsers() {
//        SQLiteDatabase db = this.getWritableDatabase();
//        db.delete(TABLE_USER, null, null);
//        db.close();
//    }
}
