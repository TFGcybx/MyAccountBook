package com.example.myaccountbook;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListAdapter extends BaseAdapter {
    List<costList>mList;
    public ListAdapter(List<costList>list)
    {
        mList=list;
    }
    private DBHelper helper;

    @Override
    public int getCount(){
        return mList.size();
    }

    @Override
    public Object getItem(int position){
        return mList.get(position);
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    @Override
    public View getView(int position,View convertView,ViewGroup parent){
        View view=mLayoutInflacter.inflate(R.layout.list_item,null);

        costList item= mList.get(position);
        TextView tv_title=view.findViewById(R.id.tv_title);
        TextView tv_note=view.findViewById(R.id.tv_note);
        TextView tv_date=view.findViewById(R.id.tv_date);
        TextView tv_money=view.findViewById(R.id.tv_money);
        ImageView iv_photo=view.findViewById(R.id.iv_photo);

        tv_title.setText(mList.get(position).getTitle());
        tv_note.setText(mList.get(position).getNote());
        tv_date.setText(mList.get(position).getDate());
        tv_money.setText(mList.get(position).getMoney());
        String title=mList.get(position).getTitle();
        switch (title){
            case "其他":
                iv_photo.setImageResource(R.drawable.cost_default);
                break;
            case "吃喝饮食":
                iv_photo.setImageResource(R.drawable.cost_food);
                break;
            case "交通出行":
                iv_photo.setImageResource(R.drawable.cost_traffic);
                break;
            case "通信费用":
                iv_photo.setImageResource(R.drawable.cost_phone);
                break;
            case "欠债借款":
                iv_photo.setImageResource(R.drawable.cost_debt);
                break;
            case "人情礼物":
                iv_photo.setImageResource(R.drawable.cost_gift);
                break;
            case "房租水电":
                iv_photo.setImageResource(R.drawable.cost_house);
                break;
            case "医疗费用":
                iv_photo.setImageResource(R.drawable.cost_medical);
                break;
            case "社交红包":
                iv_photo.setImageResource(R.drawable.cost_redpacket);
                break;
            case "逛街购物":
                iv_photo.setImageResource(R.drawable.cost_shopping);
                break;
            case "运动健身":
                iv_photo.setImageResource(R.drawable.cost_sport);
                break;
            case "工资薪水":
                iv_photo.setImageResource(R.drawable.cost_wage);
                break;
            case "日常用品":
                iv_photo.setImageResource(R.drawable.cost_daily);
                break;
            case "聚会聚餐":
                iv_photo.setImageResource(R.drawable.cost_party);
                break;
            case "基金理财":
                iv_photo.setImageResource(R.drawable.cost_stock);
                break;
            case "旅游旅行":
                iv_photo.setImageResource(R.drawable.cost_travel);
                break;
        }

        return view;
    }

    private List<costList>getmList;
    private LayoutInflater mLayoutInflacter;

    public ListAdapter(Context context,List<costList>list)
    {
        mList=list;

        mLayoutInflacter=LayoutInflater.from(context);
    }
}
