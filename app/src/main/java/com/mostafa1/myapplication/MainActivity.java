package com.mostafa1.myapplication;

import android.content.DialogInterface;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        final FragmentManager fragmentManager=getSupportFragmentManager();
        final FragmentTransaction transaction=fragmentManager.beginTransaction();
        transaction.add(R.id.fragment_container,new SampleFragment());
        transaction.commit();
        final SampleFragment2 sampleFragment2=new SampleFragment2();

        Button btnReplaceFragment=(Button)findViewById(R.id.replace_fragment);
        btnReplaceFragment.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction replaceTransaction=fragmentManager.beginTransaction();
                replaceTransaction.replace(R.id.fragment_container,sampleFragment2);
                replaceTransaction.commit();

            }
        } );
        Button btnRemoveFragment=(Button)findViewById(R.id.remove_fragment);
        btnRemoveFragment.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction removeTransaction=fragmentManager.beginTransaction();
                removeTransaction.remove(sampleFragment2);
                removeTransaction.commit();
            }
        } );
        Button btnShowDialog=(Button)findViewById(R.id.btn_show_dialog);
        btnShowDialog.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("سلام")
                        .setMessage("به اپ مصطفی خوش امدین")
                        .setPositiveButton( "خیلی ممنون", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(MainActivity.this,"خیلی ممنون کلیک شد",Toast.LENGTH_SHORT).show();

                            }
                        } )
                        .setNegativeButton( "انصراف", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(MainActivity.this,"انصراف کلیک شد",Toast.LENGTH_SHORT).show();


                            }
                        } )
                        .setCancelable(false);
                AlertDialog alertDialog=builder.create();
                alertDialog.show();




            }
        } );






    }
}
