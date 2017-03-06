package com.bhailal.sony.sqlitedemo;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Button b1Insert,b2Display,b3next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b1Insert = (Button)findViewById(R.id.btn1);
        b2Display = (Button)findViewById(R.id.btn2);
        b3next = (Button)findViewById(R.id.btn3next);




        final EditText edt1,edt2,insertedt1,insertedt2;
        edt1 = (EditText)findViewById(R.id.edt1);
        edt2 = (EditText)findViewById(R.id.edt2);
        insertedt1 = (EditText)findViewById(R.id.Insertedt1);
        insertedt2 = (EditText)findViewById(R.id.Insertedt2);




        final SQLiteDatabase db = openOrCreateDatabase("myDB",MODE_PRIVATE,null);
        db.execSQL("CREATE TABLE IF NOT EXISTS myTable (FirstName VARCHAR,LastName VARCHAR)");


        b1Insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fname,lname;

                fname = insertedt1.getText().toString();
                lname = insertedt2.getText().toString();


                db.execSQL("INSERT INTO myTable VALUES ('"+fname+"','"+lname+"')");
                insertedt1.setText("");
                insertedt2.setText("");



            }
        });

        b2Display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             //   db.execSQL("DELETE FROM myTable");
                Cursor c = db.rawQuery("SELECT * FROM MyTable",null);
                c.moveToFirst();
                edt1.setText(c.getString(c.getColumnIndex("FirstName")));
                edt2.setText(c.getString(c.getColumnIndex("LastName")));
                b2Display.setVisibility(View.GONE);
                b3next.setVisibility(View.VISIBLE);

                db.close();
            }
        });
        b3next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor c = db.rawQuery("SELECT * FROM MyTable",null);
                if(c.moveToFirst()) {
                    c.moveToNext();
                }


                    edt1.setText(c.getString(c.getColumnIndex("FirstName")));
                    edt2.setText(c.getString(c.getColumnIndex("LastName")));
               // c.moveToNext();
                db.close();

            }
        });



    }
}
