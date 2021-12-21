package com.example.myaccountbook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

public class up_cost extends AppCompatActivity {
    private DBHelper helper;
    private EditText ebu_cost_title;
    private EditText ebu_cost_money;
    private EditText ebu_cost_note;
    private DatePicker dbu_cost_date;
    private String OID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_up_cost);
        initView();
    }

    private void initView(){
        helper=new DBHelper(up_cost.this);
        ebu_cost_title=findViewById(R.id.ebu_cost_title);
        ebu_cost_note=findViewById(R.id.ebu_cost_note);
        ebu_cost_money=findViewById(R.id.ebu_cost_money);
        dbu_cost_date=findViewById(R.id.dbu_cost_date);

        Intent intent=getIntent();
        ebu_cost_title.setText(intent.getStringExtra("OTitle"));
        ebu_cost_note.setText(intent.getStringExtra("ONote"));
        ebu_cost_money.setText(intent.getStringExtra("OMoney"));
        OID=intent.getStringExtra("OID");
    }

    public void upButton(View view){
        String titleStr=ebu_cost_title.getText().toString().trim();
        String noteStr=ebu_cost_note.getText().toString().trim();
        String moneyStr=ebu_cost_money.getText().toString().trim();
        String dateStr=dbu_cost_date.getYear()+"-"+(dbu_cost_date.getMonth())+"-"
                +dbu_cost_date.getDayOfMonth();
        if("".equals(moneyStr)){
            Toast toast=Toast.makeText(this,"请填写金额",Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER,0,0);
            toast.show();
        }else{
            SQLiteDatabase db=helper.getWritableDatabase();
            String sql4 = "update account set Title=?,Note=?,Date=?,Money=? where _id=?";
            db.execSQL (sql4,new String[]{titleStr,noteStr,dateStr,moneyStr,OID});
            Toast toast=Toast.makeText(this,"修改成功",Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER,0,0);
            toast.show();
            setResult(1);
            finish();
        }
    }

    public void nuButton(View view){
        Toast toast=Toast.makeText(this,"已取消",Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.show();
        setResult(0);
        finish();
    }
}