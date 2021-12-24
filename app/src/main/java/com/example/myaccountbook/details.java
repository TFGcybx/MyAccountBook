package com.example.myaccountbook;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class details extends AppCompatActivity {
    private DBHelper helper;
    private TextView dtv_money;
    private TextView dtv_title;
    private TextView dtv_date;
    private TextView dtv_note;
    private ImageView iv_title;
    private String OMoney;
    private String OTitle;
    private String ODate;
    private String ONote;
    private String OID;
    private int i;

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
        iv_title=findViewById(R.id.iv_title);

        Intent intent=getIntent();
        OMoney=intent.getStringExtra("OMoney");
        OTitle=intent.getStringExtra("OTitle");
        ODate=intent.getStringExtra("ODate");
        ONote=intent.getStringExtra("ONote");
        dtv_money.setText(intent.getStringExtra("OMoney"));
        dtv_title.setText(intent.getStringExtra("OTitle"));
        dtv_date.setText(intent.getStringExtra("ODate"));
        dtv_note.setText(intent.getStringExtra("ONote"));
        OID= intent.getStringExtra("OID");
        switch (OTitle){
            case "其他":
                iv_title.setImageResource(R.drawable.cost_default);
                break;
            case "吃喝饮食":
                iv_title.setImageResource(R.drawable.cost_food);
                break;
            case "交通出行":
                iv_title.setImageResource(R.drawable.cost_traffic);
                break;
            case "通信费用":
                iv_title.setImageResource(R.drawable.cost_phone);
                break;
            case "欠债借款":
                iv_title.setImageResource(R.drawable.cost_debt);
                break;
            case "人情礼物":
                iv_title.setImageResource(R.drawable.cost_gift);
                break;
            case "房租水电":
                iv_title.setImageResource(R.drawable.cost_house);
                break;
            case "医疗费用":
                iv_title.setImageResource(R.drawable.cost_medical);
                break;
            case "社交红包":
                iv_title.setImageResource(R.drawable.cost_redpacket);
                break;
            case "逛街购物":
                iv_title.setImageResource(R.drawable.cost_shopping);
                break;
            case "运动健身":
                iv_title.setImageResource(R.drawable.cost_sport);
                break;
            case "工资薪水":
                iv_title.setImageResource(R.drawable.cost_wage);
                break;
            case "日常用品":
                iv_title.setImageResource(R.drawable.cost_daily);
                break;
            case "聚会聚餐":
                iv_title.setImageResource(R.drawable.cost_party);
                break;
            case "基金理财":
                iv_title.setImageResource(R.drawable.cost_stock);
                break;
            case "旅游旅行":
                iv_title.setImageResource(R.drawable.cost_travel);
                break;
        }
        i=Integer.parseInt(intent.getStringExtra("i"));
    }

    public void dib_backButton(View view){
        setResult(0);
        finish();
    }

    public void deleteAccount(View view){
        DeleteContentSQL(i);
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
        finish();
    }
}