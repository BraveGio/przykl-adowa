package com.example.adam.adam_1;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.net.Uri;
import android.support.annotation.DrawableRes;
import android.util.Base64;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;

import static android.R.attr.bitmap;
import static android.R.attr.value;
import static android.R.attr.version;
import static android.os.Build.ID;
import static android.provider.Contacts.SettingsColumns.KEY;
import static com.example.adam.adam_1.R.drawable.arbuz;

/**
 * Created by Adam on 28.02.2017.
 */

public class owocedbHelper extends SQLiteOpenHelper {
    public static final String Table_name="tabela_owoce";
    public static final String query="create table "+Table_name+"(ID INTEGER PRIMARY KEY AUTOINCREMENT,LITERA TEXT,ZDJECIE BLOP)";
    Bitmap bitmap;
    public owocedbHelper(Context context) {
        super(context, Table_name, null, 1);
    }

    public boolean dodaj_owoc(String litera,byte[] zdjecie)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("LITERA",litera);
        contentValues.put("ZDJECIE",zdjecie);
        Long result=db.insert(Table_name,null,contentValues);
        if(result==1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(query);
    }

    public Cursor getAlldata(String id)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res2=db.rawQuery("select * from "+Table_name+" WHERE id=? ",new String[]{id+""});
        res2.moveToFirst();
        return res2;
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
