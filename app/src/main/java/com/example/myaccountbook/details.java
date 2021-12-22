package com.example.myaccountbook;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class details extends AppCompatActivity {
    private DBHelper helper;
    private TextView dtv_money;
    private TextView dtv_title;
    private TextView dtv_date;
    private TextView dtv_note;
    private String OMoney;
    private String OTitle;
    private String ODate;
    private String ONote;
    private int OID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        initView();
    }

    private void initView(){
        helper=new DBHelper(details.this);
        dtv_money=findViewById(R.id.dtv_money);
        dtv_title=findViewById(R.id.dtv_title);
        dtv_date=findViewById(R.id.dtv_date);
        dtv_note=findViewById(R.id.dtv_note);

        Intent intent=getIntent();
        OMoney=intent.getStringExtra("OMoney");
        OTitle=intent.getStringExtra("OTitle");
        ODate=intent.getStringExtra("ODate");
        ONote=intent.getStringExtra("ONote");
        dtv_money.setText(intent.getStringExtra("OMoney"));
        dtv_title.setText(intent.getStringExtra("OTitle"));
        dtv_date.setText(intent.getStringExtra("ODate"));
        dtv_note.setText(intent.getStringExtra("ONote"));
        OID= Integer.parseInt(intent.getStringExtra("OID"));
    }

    public void backButton(View view){
        setResult(0);
        finish();
    }

    public void deleteAccount(View view){
        DeleteContentSQL(OID);
        setResult(1);
        finish();
    }
    public void DeleteContentSQL(int i){
        SQLiteDatabase db=helper.getWritableDatabase();
        try{
            Cursor cursor=db.query("account",null,null,
                    null,null,null,null);
            if(cursor.moveToPosition(i)){
                @SuppressLint("Range") String id1=cursor.getString(cursor.getColumnIndex("_id"));
                db.execSQL("DELETE FROM account where _id=?",new String[]{id1});
                db.close();
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void alterAccount(View view){
        Intent intent=new Intent(details.this,up_cost.class);
        intent.putExtra("OID",OID);
        intent.putExtra("OTitle",OTitle);
        intent.putExtra("ONote",ONote);
        intent.putExtra("ODate",ODate);
        intent.putExtra("OMoney",OMoney);
        startActivity(intent);
    }
}