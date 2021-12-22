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

        tv_title.setText(mList.get(position).getTitle());
        tv_note.setText(mList.get(position).getNote());
        tv_date.setText(mList.get(position).getDate());
        tv_money.setText(mList.get(position).getMoney());

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
