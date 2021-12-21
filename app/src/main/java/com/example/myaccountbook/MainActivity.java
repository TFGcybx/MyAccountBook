package com.example.myaccountbook;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
public class MainActivity extends AppCompatActivity {

    private DBHelper helper;
    private ListView listView;
    private ImageButton Add;
    private ImageView Delete;
    private TextView backtop;
    private List<costList>list;
    //private List<costList>rlist;
    private ListAdapter listAdapter;
    private int index = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();

    }

    @SuppressLint("Range")
    private void initData() {
        list=new ArrayList<>();
        //rlist=new ArrayList<>();
        SQLiteDatabase db=helper.getReadableDatabase();
        Cursor cursor=db.query("account",null,null,null,null,
                null,null);
        while (cursor.moveToNext()){
            costList clist=new costList();
            clist.set_id(cursor.getString(cursor.getColumnIndex("_id")));
            clist.setTitle(cursor.getString(cursor.getColumnIndex("Title")));
            clist.setNote(cursor.getString(cursor.getColumnIndex("Note")));
            clist.setDate(cursor.getString(cursor.getColumnIndex("Date")));
            clist.setMoney(cursor.getString(cursor.getColumnIndex("Money")));
            list.add(clist);
            Collections.sort(list, new Comparator<costList>() {
                @Override
                public int compare(costList o1, costList o2) {
                    int id1=Integer.parseInt(o1.get_id());
                    int id2=Integer.parseInt(o2.get_id());
                    if(id1<id2){
                        return 1;
                    }
                    return -1;
                }
            });
        }

        listView.setAdapter(new ListAdapter(this,list));
        db.close();
    }

    private void initView() {
        helper = new DBHelper(MainActivity.this);
        listView = findViewById(R.id.list_view);
        this.registerForContextMenu(listView);
        Add = findViewById(R.id.add);

        backtop = findViewById(R.id.backTop);
        backtop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.backTop:
                        setListViewPos(0);
                        break;
                }
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                view.findViewById(R.id.iv_up).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int rposition=list.size()-position-1;
                        passdata(rposition);
                    }
                });
                view.findViewById(R.id.iv_delete).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int rposition=list.size()-position-1;
                        String ret2=DeleteContentSQL(rposition);
                        if(ret2.equals("1")){
                            Toast.makeText(MainActivity.this,"删除成功",Toast.LENGTH_SHORT).show();
                            initData();
                        } else {
                            Toast.makeText(MainActivity.this,"删除失败",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    public void addAccount(View view){
        Intent intent=new Intent(MainActivity.this,new_cost.class);
        startActivityForResult(intent,1);
    }

    @SuppressLint("Range")
    public void passdata(int i){
        SQLiteDatabase db=helper.getReadableDatabase();
        Cursor cursor=db.query("account",null,null,
                null,null,null,null);
        if(cursor.moveToPosition(i)){
            String OID=cursor.getString(cursor.getColumnIndex("_id"));
            String OTitle=cursor.getString(cursor.getColumnIndex("Title"));
            String ONote=cursor.getString(cursor.getColumnIndex("Note"));
            String ODate=cursor.getString(cursor.getColumnIndex("Date"));
            String OMoney=cursor.getString(cursor.getColumnIndex("Money"));
            Intent intent=new Intent(MainActivity.this,up_cost.class);
            intent.putExtra("OID",OID);
            intent.putExtra("OTitle",OTitle);
            intent.putExtra("ONote",ONote);
            intent.putExtra("ODate",ODate);
            intent.putExtra("OMoney",OMoney);
            //startActivity(intent);
            startActivityForResult(intent,1);
        }

    }

    public String DeleteContentSQL(int i){
        String result="";
        SQLiteDatabase db=helper.getWritableDatabase();
        try{
            Cursor cursor=db.query("account",null,null,
                    null,null,null,null);
            if(cursor.moveToPosition(i)){
                @SuppressLint("Range") String id1=cursor.getString(cursor.getColumnIndex("_id"));
                db.execSQL("DELETE FROM account where _id=?",new String[]{id1});
                db.close();
                result="1";
            }
        } catch (SQLException e){
            e.printStackTrace();
            result+="查询数据异常!"+e.getMessage();
        }
        return result;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode==1&&requestCode==1)
        {
            this.initData();
        }
    }

    private void setListViewPos(int pos){
        if(Build.VERSION.SDK_INT>=3){
            listView.smoothScrollToPosition(pos);
        }else{
            listView.setSelection(pos);
        }
    }
}