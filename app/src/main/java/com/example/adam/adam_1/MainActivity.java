package com.example.adam.adam_1;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.content.res.Resources;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

import static android.R.attr.id;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

public class MainActivity extends AppCompatActivity {
TextView pytanie;
    Button pierwszy,drugi,trzeci,czwarty;
    ImageView im;
    owocedbHelper mydb;
    String litera;
    byte[] zdjecie;
    String[] przyciskil=new String[4];
    String[] litery={"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","S","T","U","Y","Z","Ż","Ź"};
    String[] aaa=getResources().getStringArray(R.array.litery);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        przyciski();
        mydb=new owocedbHelper(getApplicationContext());
        dodaj_rekordy();
        podgląd();
        dobranie_przycisków();


    }
    public void sprButton(View view)
    {
        Button button=(Button)view;
        if(button.getText().toString()==litera)
        {
            Toast.makeText(getApplicationContext(),"ok",Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(getApplicationContext(),"Zła odpowiedź",Toast.LENGTH_SHORT).show();
            podgląd();
        }
    }
    public void dobranie_przycisków()
    {
        for(int i=0;i<=3;i++)
        {
            if(przyciskil[i]==null)
            {
                do {
                    Random b=new Random();
                    int bb=b.nextInt(23);

                    for(int j=0;j<=3;j++)
                    {
                        if(przyciskil[j]!=litery[bb])
                        {
                            przyciskil[i]=litery[bb];
                        }
                    }
                }
                while (przyciskil[i]==null);
            }
        }
        pierwszy.setText(przyciskil[0]);
        drugi.setText(przyciskil[1]);
        trzeci.setText(przyciskil[2]);
        czwarty.setText(przyciskil[3]);
    }
    public void podgląd()
    {
    Random random=new Random();
    random.nextInt(5);
    Cursor res=mydb.getAlldata("1");
    if(res.getCount()<=0)
    {
       showmessage ("ERROR","NO DATA");
        return;
    }
    else
    {
        int pom=0;
        litera=res.getString(1);
        Random zbazy=new Random();
        pom=zbazy.nextInt(4);
        przyciskil[pom]=litera;
        zdjecie=res.getBlob(2);
        Bitmap bitmap= BitmapFactory.decodeByteArray(zdjecie,0,zdjecie.length);
        im.setImageBitmap(bitmap);
    }
}
    public void dodaj_rekordy()
    {
        Cursor spr=mydb.getAlldata("1");
        if(spr.getCount()==0)
        {
            String st=getString(R.string.arbuz);
            byte[] bytes= Base64.decode(st,Base64.DEFAULT);
            mydb.dodaj_owoc("a",bytes);
        }
    }
    public void przyciski()
    {
     pytanie=(TextView)findViewById(R.id.pytanie);
     Typeface typeface=Typeface.createFromAsset(getAssets(),"sourcesans.otf");
     pytanie.setTypeface(typeface);
     pierwszy=(Button)findViewById(R.id.pierwsza);
     drugi=(Button)findViewById(R.id.druga);
     trzeci=(Button)findViewById(R.id.trzecia);
     czwarty=(Button)findViewById(R.id.czwarta);
     pierwszy.setTypeface(typeface);
     drugi.setTypeface(typeface);
        trzeci.setTypeface(typeface);
        czwarty.setTypeface(typeface);
     im=(ImageView)findViewById(R.id.zdjęcie);

   }
    public void showmessage(String title,String message)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}
