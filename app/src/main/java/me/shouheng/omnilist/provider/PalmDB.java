package me.shouheng.omnilist.provider;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by wangshouheng on 2017/3/13. */
public class PalmDB extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "OmniList.db";
    private static final int VERSION = 1;

    private Context mContext;
    @SuppressLint("StaticFieldLeak")
    private static PalmDB sInstance = null;

    private volatile boolean isOpen = true;

    public static PalmDB getInstance(final Context context){
        if (sInstance == null){
            synchronized (PalmDB.class) {
                if (sInstance == null) {
                    sInstance = new PalmDB(context.getApplicationContext());
                }
            }
        }
        return sInstance;
    }

    private PalmDB(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        LocationsStore.getInstance().onCreate(db);
        AttachmentsStore.getInstance().onCreate(db);
        AlarmsStore.getInstance().onCreate(db);
        TimelineStore.getInstance().onCreate(db);
        CategoryStore.getInstance().onCreate(db);
        WeatherStore.getInstance().onCreate(db);
        AssignmentsStore.getInstance().onCreate(db);
        SubAssignmentStore.getInstance().onCreate(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        LocationsStore.getInstance().onUpgrade(db, oldVersion, newVersion);
        AttachmentsStore.getInstance().onUpgrade(db, oldVersion, newVersion);
        AlarmsStore.getInstance().onUpgrade(db, oldVersion, newVersion);
        TimelineStore.getInstance().onUpgrade(db, oldVersion, newVersion);
        CategoryStore.getInstance().onUpgrade(db, oldVersion, newVersion);
        WeatherStore.getInstance().onUpgrade(db, oldVersion, newVersion);
        AssignmentsStore.getInstance().onUpgrade(db, oldVersion, newVersion);
        SubAssignmentStore.getInstance().onUpgrade(db, oldVersion, newVersion);
    }

    @Override
    public synchronized void close() {
        super.close();
        isOpen = false;
        sInstance = null;
    }

    public boolean isOpen() {
        return isOpen;
    }
}
