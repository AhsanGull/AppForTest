package com.example.mywhatsappfetcher;

import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends AppCompatActivity {
    //Création d'une ListView pour le display
    ListView l1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //On bind la Listview XML avec la ListView Java
        l1=(ListView) findViewById(R.id.listView);
        get(l1);
    }
    //Méthode qui permet de récupérer les contacts présents dans l'Android database
    public void get(View v){
        //Query sur l'android database avec comme Acount type WhatsApp
        Cursor cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null, ContactsContract.RawContacts.ACCOUNT_TYPE + "= ?",new String[] { "com.whatsapp" },null);

        //Récupération du Nom et du Num
        String[] from = {ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,ContactsContract.CommonDataKinds.Phone.NUMBER,ContactsContract.CommonDataKinds.Phone._ID};
        int[] to ={android.R.id.text1,android.R.id.text2};
        SimpleCursorAdapter simpleCursorAdapter=new SimpleCursorAdapter(this,android.R.layout.simple_list_item_2,cursor,from,to);

        l1.setAdapter(simpleCursorAdapter);
        l1.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
    }
}
