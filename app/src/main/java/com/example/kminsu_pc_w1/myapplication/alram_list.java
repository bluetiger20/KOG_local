package com.example.kminsu_pc_w1.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;


public class alram_list extends Activity implements View.OnClickListener {
    private ArrayAdapter<String> _arrAdapter ;
    private ListView listView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alram_list);

        settingListView() ;
        buttonHandle() ;
    }
    String[] arr = null;
    private void settingListView() {
        ArrayList<AlarmData> list  = new ArrayList<AlarmData>();
        list.add(new AlarmData("a"));
        list.add(new AlarmData("b"));
        list.add(new AlarmData("c"));
        list.add(new AlarmData("d"));
        //_arrAdapter = new ArrayAdapter<String>( alram_list.this, android.R.layout.simple_list_item_1, list) ;
        listView = (ListView) findViewById(R.id.listView_test) ;
        listView.setAdapter(new AlarmAdapter(this, list) ) ;
    }

    private void buttonHandle() {
        Button addItemBtn = (Button) findViewById( R.id.addBtn ) ;
        addItemBtn.setOnClickListener( this ) ;
    }

    @Override
    public void onClick(View v) {
        EditText editText = (EditText) findViewById( R.id.editField ) ;
        String inputValue = editText.getText().toString() ;
        refresh( inputValue ) ;
    }

    private void refresh( String $inputValue ) {
        _arrAdapter.add( $inputValue ) ;
        _arrAdapter.notifyDataSetChanged() ;
    }
}



//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_alram_list);
//        DBContactHelper db = new DBContactHelper(this);
//
//        /**
//         * CRUD Operations
//         * */
//        // 샘플데이타 넣기
//        Log.d("Insert: ", "Inserting ..");
//        db.addContact(new Contact("Ravi", "9100000000"));
//        db.addContact(new Contact("Srinivas", "9199999999"));
//        db.addContact(new Contact("Tommy", "9522222222"));
//        db.addContact(new Contact("Karthik", "9533333333"));
//
//        // 집어넣은 데이타 다시 읽어들이기
//        Log.d("Reading: ", "Reading all contacts..");
//        List<Contact> contacts = db.getAllContacts();
//
//        for (Contact cn : contacts) {
//            String log = "Id: "+cn.getID()+" ,Name: " + cn.getName() + " ,Phone: " + cn.getPhoneNumber();
//            Log.d("Name: ", log);
//        }
//
//    }
//
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.alram_list, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//        if (id == R.id.action_settings) {
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
//}
