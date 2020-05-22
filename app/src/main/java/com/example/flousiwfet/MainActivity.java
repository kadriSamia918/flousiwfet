package com.example.flousiwfet;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {



    public static DBadapter db ;
    public  adapter arrayAdapter ;
    public static ArrayList<achat> MyList ;
    ListView liste ;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        liste=findViewById(R.id.liste);
        db= new DBadapter(this);
        MyList= db.afficher();
        MyList=new ArrayList<achat>();


        arrayAdapter=new adapter(this, R.layout.item,MyList);

        liste.setAdapter(arrayAdapter);

        liste.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, final View view, final int position, long id){
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("supression")
                        .setMessage("Voulez vous supprimer cet achat?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                db.supprimer(MyList.get(position).getId());
                                MyList=db.afficher();

                                arrayAdapter=new adapter(MainActivity.this,R.layout.item,MyList);

                                liste.setAdapter(arrayAdapter);

                                }})
                        .setNegativeButton(android.R.string.no, null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();


            }

        }     );


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu,menu);
        return true ;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if( item.getItemId()==R.id.ajout)
        {
            Intent intent = new Intent(MainActivity.this, ajout.class); startActivity(intent);
        }

        if( item.getItemId()==R.id.total)
        {
            Intent intent = new Intent(MainActivity.this, total.class); startActivity(intent);
        }
        return true ;
    }

    public static long getAppFirstInstallTime(Context context){
        PackageInfo packageInfo;
        try {
            if(Build.VERSION.SDK_INT>8/*Build.VERSION_CODES.FROYO*/ ){
                packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
                return packageInfo.firstInstallTime;
            }else{
                //firstinstalltime unsupported return last update time not first install time
                ApplicationInfo appInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 0);
                String sAppFile = appInfo.sourceDir;
                return new File(sAppFile).lastModified();
            }
        } catch (PackageManager.NameNotFoundException e) {
            //should never happen
            return 0;
        }
    }
    public static String getDate(long milliSeconds, String dateFormat) {
        // Create a DateFormatter object for displaying date in specified format.
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);

        // Create a calendar object that will convert the date and time value in milliseconds to date.
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return formatter.format(calendar.getTime());
    }

    @Override
    public void onResume(){
        super.onResume();
        MyList=db.afficher();
        arrayAdapter=new adapter(this,R.layout.item,MyList);

        liste.setAdapter(arrayAdapter);

    }

}
