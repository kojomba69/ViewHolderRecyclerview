package com.example.edu.viewholderrecyclerview;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {
    ArrayList<HashMap<String,Object>> arrayList=null;
    private SQLiteDatabase mdb;

    public RecyclerAdapter(SQLiteDatabase db){
        this.mdb = db;
        String query=new StringBuilder().append("select * frome myorder ").toString();
        Cursor cursor=mdb.rawQuery(query,null);
        ArrayList<HashMap<String, Object>>arrayListTemp = new ArrayList<>();
        HashMap<String, Object> hashMap = null;
        while (cursor.moveToNext()){
            hashMap = new HashMap<String, Object>();
            hashMap.put("itemTile",cursor.getString(0));
            hashMap.put("itemdetail", cursor.getString(1));
            arrayListTemp.add(hashMap);
        }
        this.arrayList=arrayListTemp;
    }

    /*public RecyclerAdapter(ArrayList<HashMap<String,Object>>arrayList){
        this.arrayList=new ArrayList<HashMap<String,Object>>();
        this.arrayList=arrayList;
    }*/
    /*public void addItem(int position HashMap<String, Object> hashmap){
        this.arrayList.add(hashMap);
        notifyItemInserted(position);
    }연쌤꺼-hashmap에 빨간줄. 왤까?*/

    public class MyViewHolder extends RecyclerView.ViewHolder{

        public ImageView item_Image;
        public TextView item_title;
        public TextView item_detail;

        public MyViewHolder(View itemView) {
            super(itemView);
            item_Image = (ImageView)itemView.findViewById(R.id.item_image);
            item_title= (TextView)itemView.findViewById(R.id.item_title);
            item_detail= (TextView)itemView.findViewById(R.id.item_detail);
        }

    }

        @NonNull
    @Override
    public RecyclerAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_cardlayout,parent,false);
        MyViewHolder viewHolder=new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerAdapter.MyViewHolder holder, int position) {
        HashMap<String,Object> hashMap = arrayList.get(position);
        holder.item_title.setText((String)hashMap.get("title"));
        holder.item_detail.setText((String)hashMap.get("detail"));
        holder.item_Image.setImageResource((Integer) hashMap.get("image"));
        holder.item_title.setText("0");
        holder.item_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Integer count = Integer.parseInt(
                        ((TextView)holder.item_title).getText().toString())+1;
                ((TextView)holder.item_title).setText(count.toString());
                Toast.makeText(v.getContext(),((TextView)v).getText(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }



}
