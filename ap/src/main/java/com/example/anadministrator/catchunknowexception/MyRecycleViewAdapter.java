package com.example.anadministrator.catchunknowexception;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.anadministrator.catchunknowexception.JavaBean.Bean;
import com.example.anadministrator.catchunknowexception.Utils.ImageLoaderUtils;

public class MyRecycleViewAdapter extends RecyclerView.Adapter<MyRecycleViewAdapter.MyViewHolder> implements View.OnClickListener, View.OnLongClickListener {
    private Bean bean;
    private Context context;
    private View view;

    public MyRecycleViewAdapter(Bean bean, Context context) {
        this.bean = bean;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = View.inflate(context, R.layout.item, null);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        myViewHolder.iv_icon.setOnClickListener(this);
        view.setOnLongClickListener(this);
        return myViewHolder;
    }

    @Override
    public void onClick(View view1) {
        if(mMyItemclickListener!=null){
            mMyItemclickListener.itemclick(view1, (Integer) view.getTag());
        }
    }

    @Override
    public boolean onLongClick(View v) {
        if(mMyItemclickListener!=null){
            mMyItemclickListener.itemLongclick(v, (Integer) view.getTag());
        }

        return false;
    }

    public interface MyItemclickListener{
        void itemclick(View view,int position);
        void itemLongclick(View view,int position);
    }
    public MyItemclickListener mMyItemclickListener;

    public void setmMyItemclickListener(MyItemclickListener mMyItemclickListener) {
        this.mMyItemclickListener = mMyItemclickListener;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Bean.TopStoriesBean topStoriesBean = bean.top_stories.get(position);
        holder.tv_title.setText(topStoriesBean.title);
        ImageLoaderUtils.setImageView(topStoriesBean.image,context,holder.iv_icon);
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return bean.top_stories.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        private  ImageView iv_icon;
        private final TextView tv_title;

        public MyViewHolder(View itemView) {
            super(itemView);
            iv_icon = itemView.findViewById(R.id.iv_icon);
            tv_title = itemView.findViewById(R.id.tv_title);
        }
    }


}