package com.example.myaccountbook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;


public class new_cost extends AppCompatActivity {
    private DBHelper helper;
    private TextView tv_cost_title;
    private EditText et_cost_money;
    private EditText et_cost_note;
    private DatePicker dp_cost_date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_cost);
        initView();
    }

    private void initView(){
        helper=new DBHelper(new_cost.this);
        tv_cost_title=findViewById(R.id.tv_cost_title);
        et_cost_note=findViewById(R.id.et_cost_note);
        et_cost_money=findViewById(R.id.et_cost_money);
        dp_cost_date=findViewById(R.id.dp_cost_date);
    }

    public void chooseCost(View view){
        switch (view.getId()){
            case R.id.ib_default:
                tv_cost_title.setText("其他");
                break;
            case R.id.ib_food:
                tv_cost_title.setText("吃喝饮食");
                break;
            case R.id.ib_traffic:
                tv_cost_title.setText("交通出行");
                break;
            case R.id.ib_phone:
                tv_cost_title.setText("通信费用");
                break;
            case R.id.ib_debt:
                tv_cost_title.setText("欠债借款");
                break;
            case R.id.ib_gift:
                tv_cost_title.setText("人情礼物");
                break;
            case R.id.ib_house:
                tv_cost_title.setText("房租水电");
                break;
            case R.id.ib_medical:
                tv_cost_title.setText("医疗费用");
                break;
            case R.id.ib_redpacket:
                tv_cost_title.setText("社交红包");
                break;
            case R.id.ib_shopping:
                tv_cost_title.setText("逛街购物");
                break;
            case R.id.ib_sport:
                tv_cost_title.setText("运动健身");
                break;
            case R.id.ib_wage:
                tv_cost_title.setText("工资薪水");
                break;
            case R.id.ib_daily:
                tv_cost_title.setText("日常用品");
                break;
            case R.id.ib_party:
                tv_cost_title.setText("聚会聚餐");
                break;
            case R.id.ib_stock:
                tv_cost_title.setText("基金理财");
                break;
            case R.id.ib_travel:
                tv_cost_title.setText("旅游旅行");
                break;
        }
    }

    public void aib_okButton(View view){
        String titleStr=tv_cost_title.getText().toString();
        String noteStr=et_cost_note.getText().toString().trim();
        String moneyStr=et_cost_money.getText().toString().trim();
        String dateStr=dp_cost_date.getYear()+"-"+(dp_cost_date.getMonth())+"-"
                +dp_cost_date.getDayOfMonth();
        if("".equals(moneyStr)){
            Toast toast=Toast.makeText(this,"请填写金额",Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER,0,0);
            toast.show();
        }else{
            SQLiteDatabase db=helper.getWritableDatabase();
            ContentValues values=new ContentValues();
            values.put("Title",titleStr);
            values.put("Note",noteStr);
            values.put("Money",moneyStr);
            values.put("Date",dateStr);
            long account=db.insert("account",null,values);
            if(account>0){
                Toast toast=Toast.makeText(this,"保存成功",Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER,0,0);
                toast.show();
                setResult(1);
                finish();
            }else{
                Toast toast=Toast.makeText(this,"请重试",Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER,0,0);
                toast.show();
                db.close();
            }
            setResult(1);
            finish();
        }
    }

    public void aib_backButton(View view){
        Toast toast=Toast.makeText(this,"已取消",Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.show();
        setResult(0);
        finish();
    }
}