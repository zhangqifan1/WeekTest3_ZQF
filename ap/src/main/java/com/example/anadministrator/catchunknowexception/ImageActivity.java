package com.example.anadministrator.catchunknowexception;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.anadministrator.catchunknowexception.Utils.ImageLoaderUtils;

public class ImageActivity extends Activity implements View.OnClickListener {

    private ImageView mImage;
    /**
     * 旋转
     */
    private Button mButRotate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        initView();
        String name = getIntent().getStringExtra("name");
        ImageLoaderUtils.setImageView(name, this, mImage);
    }

    private void initView() {
        mImage = (ImageView) findViewById(R.id.image);
        mButRotate = (Button) findViewById(R.id.butRotate);
        mButRotate.setOnClickListener(this);
        mImage.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.butRotate:
                ObjectAnimator
                        .ofFloat(mImage,"rotationY",0.0f,360f)
                        .setDuration(1000)
                        .start();
                break;
            case R.id.image:
                ObjectAnimator objectAnimatorBg = ObjectAnimator.ofInt(mImage, "backgroundColor", Color.BLUE, Color.YELLOW, Color.RED);
                objectAnimatorBg.setDuration(3000);
                objectAnimatorBg.start();
                break;
        }
    }
}
