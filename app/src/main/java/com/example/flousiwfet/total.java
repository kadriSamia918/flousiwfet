package com.example.flousiwfet;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import static com.example.flousiwfet.MainActivity.db;
import static com.example.flousiwfet.MainActivity.getAppFirstInstallTime;
import static com.example.flousiwfet.MainActivity.getDate;

public class total extends AppCompatActivity {
    Long date ;
    TextView t,dateview ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_total);

        t =findViewById(R.id.total);
        t.setText(Float.toString(db.total()));
        date= getAppFirstInstallTime(this);
        String d = getDate(date, "dd/MM/yyyy");
        dateview=findViewById(R.id.date);
        dateview.setText(d);


    }

    @Override
    public void onResume(){
        super.onResume();
        t.setText(Float.toString(db.total()));
    }
}
