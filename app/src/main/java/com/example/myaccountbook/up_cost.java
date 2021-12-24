package com.example.myaccountbook;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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
import android.widget.TextView;
import android.widget.Toast;

public class up_cost extends AppCompatActivity {
    private DBHelper helper;
    private TextView tbu_cost_title;
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
        tbu_cost_title=findViewById(R.id.A_tv_title);
        ebu_cost_note=findViewById(R.id.A_et_note);
        ebu_cost_money=findViewById(R.id.A_et_money);
        dbu_cost_date=findViewById(R.id.A_dp_date);

        Intent intent=getIntent();
        tbu_cost_title.setText(intent.getStringExtra("OTitle"));
        ebu_cost_note.setText(intent.getStringExtra("ONote"));
        ebu_cost_money.setText(intent.getStringExtra("OMoney"));
        OID=intent.getStringExtra("OID");
    }

    public void chooseCost(View view){
        switch (view.getId()){
            case R.id.ib_default:
                tbu_cost_title.setText("其他");
                break;
            case R.id.ib_food:
                tbu_cost_title.setText("吃喝饮食");
                break;
            case R.id.ib_traffic:
                tbu_cost_title.setText("交通出行");
                break;
            case R.id.ib_phone:
                tbu_cost_title.setText("通信费用");
                break;
            case R.id.ib_debt:
                tbu_cost_title.setText("欠债借款");
                break;
            case R.id.ib_gift:
                tbu_cost_title.setText("人情礼物");
                break;
            case R.id.ib_house:
                tbu_cost_title.setText("房租水电");
                break;
            case R.id.ib_medical:
                tbu_cost_title.setText("医疗费用");
                break;
            case R.id.ib_redpacket:
                tbu_cost_title.setText("社交红包");
                break;
            case R.id.ib_shopping:
                tbu_cost_title.setText("逛街购物");
                break;
            case R.id.ib_sport:
                tbu_cost_title.setText("运动健身");
                break;
            case R.id.ib_wage:
                tbu_cost_title.setText("工资薪水");
                break;
            case R.id.ib_daily:
                tbu_cost_title.setText("日常用品");
                break;
            case R.id.ib_party:
                tbu_cost_title.setText("聚会聚餐");
                break;
            case R.id.ib_stock:
                tbu_cost_title.setText("基金理财");
                break;
            case R.id.ib_travel:
                tbu_cost_title.setText("旅游旅行");
                break;
        }
    }

    public void up_okButton(View view){
        String titleStr=tbu_cost_title.getText().toString();
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

    public void up_backButton(View view){
        setResult(0);
        finish();
    }
}