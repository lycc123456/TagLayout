package com.dl7.taglayout;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.SparseIntArray;

import com.dl7.tag.TagView;

public class TagViewActivity extends AppCompatActivity {

    private TagView mTagGoodOrBad;
    private TagView mTagRightOrError;
    private TagView mTagSmileOrCry;

    private Handler mHandler = new Handler();
    private SparseIntArray mTimeSparse = new SparseIntArray();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tag_view);
        initView();
    }

    private void initView() {
        mTagGoodOrBad = (TagView) findViewById(R.id.tag_good_or_bad);
        mTagRightOrError = (TagView) findViewById(R.id.tag_right_or_error);
        mTagSmileOrCry = (TagView) findViewById(R.id.tag_smile_or_cry);
        mTagGoodOrBad.setTagClickListener(new TagView.OnTagClickListener() {
            @Override
            public void onTagClick(String text, @TagView.TagMode int tagMode) {
                if (_isClickedNow(R.id.tag_good_or_bad)) {
                    return;
                }
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mTagGoodOrBad.setChecked(!mTagGoodOrBad.isChecked());
                    }
                }, 2000);
            }
        });
        mTagRightOrError.setTagClickListener(new TagView.OnTagClickListener() {
            @Override
            public void onTagClick(String text, @TagView.TagMode int tagMode) {
                if (_isClickedNow(R.id.tag_right_or_error)) {
                    return;
                }
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mTagRightOrError.setChecked(!mTagRightOrError.isChecked());
                    }
                }, 2000);
            }
        });
        mTagSmileOrCry.setTagClickListener(new TagView.OnTagClickListener() {
            @Override
            public void onTagClick(String text, @TagView.TagMode int tagMode) {
                if (_isClickedNow(R.id.tag_smile_or_cry)) {
                    return;
                }
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mTagSmileOrCry.setChecked(!mTagSmileOrCry.isChecked());
                    }
                }, 2000);
            }
        });
    }


    private boolean _isClickedNow(int id) {
        int lastTime = mTimeSparse.get(id);
        boolean isClickedNow = false;
        int curTime = (int) System.currentTimeMillis();
        if (curTime - lastTime < 2000) {
            isClickedNow = true;
        }
        Log.w("TagViewActivity", curTime + " - " + lastTime);
        Log.e("TagViewActivity", ""+(curTime - lastTime));
        mTimeSparse.put(id, curTime);
        return isClickedNow;
    }
}