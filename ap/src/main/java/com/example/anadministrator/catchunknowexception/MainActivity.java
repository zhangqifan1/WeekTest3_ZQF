package com.example.anadministrator.catchunknowexception;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.anadministrator.catchunknowexception.JavaBean.Bean;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 *
 */
public class MainActivity extends AppCompatActivity {

    /**
     * 钒哥千秋万载,万载千秋
     */
    private TextView mTv;
    private String path = "https://news-at.zhihu.com/api/4/news/latest";
    private RecyclerView mRecycler;
    private MyRecycleViewAdapter adapter;
    private Bean bean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        SynRequest();

    }

    private void SynRequest() {
        new Thread() {
            @Override
            public void run() {
                //创建Client
                OkHttpClient client = new OkHttpClient.Builder().build();
                //创建请求对象
                Request request = new Request.Builder().url(path).build();
                try {
                    //同步
                    Response response = client.newCall(request).execute();
                    String string = response.body().string();
                    Gson gson=new Gson();
                    bean = gson.fromJson(string, Bean.class);
                    adapter = new MyRecycleViewAdapter(bean, MainActivity.this);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mRecycler.setAdapter(adapter);
                            mRecycler.setLayoutManager(new LinearLayoutManager(MainActivity.this,LinearLayoutManager.VERTICAL,false));
                            adapter.setmMyItemclickListener(new MyRecycleViewAdapter.MyItemclickListener() {
                                @Override
                                public void itemclick(View view, int position) {
                                    Intent intent = new Intent(MainActivity.this, ImageActivity.class);
                                    intent.putExtra("name",bean.top_stories.get(position).image);
                                    startActivity(intent);
                            }

                                @Override
                                public void itemLongclick(View view, int position) {
                                    //空指针异常
                                    Object o=null;
                                    System.out.println(o.toString());
                                }
                            });
                        }
                    });

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }.start();
    }


    private void initView() {
        mRecycler = (RecyclerView) findViewById(R.id.recycler);
    }
}
