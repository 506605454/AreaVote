package com.xiaoqu.areavote.view;

import android.content.Context;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xiaoqu.areavote.R;


/**
 * Created by Administrator on 2015/11/25.
 */
public class MyBottomLayout extends LinearLayoutCompat {
    RelativeLayout mVoteLayout;
    RelativeLayout mNoticeLayout;
    RelativeLayout mMeLayout;
    LayoutInflater mInflater;

    public MyBottomLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {
        mInflater = LayoutInflater.from(getContext());
        View view = mInflater.inflate(R.layout.bottom_bar, this);
        findView(view);
        initData();
        setOnClick();
    }

    /**
     *底部栏标题初始化
     */
    private void initData() {

        reSetStatus(mVoteLayout, R.drawable.ic_star_border_blue_grey_300_18dp,"投票");
        reSetStatus(mNoticeLayout, R.drawable.ic_assignment_blue_grey_300_18dp,"公告");
        reSetStatus(mMeLayout, R.drawable.ic_sentiment_satisfied_blue_grey_300_18dp,"我");
        ((TextView)mVoteLayout.findViewById(R.id.tv_barname)).setTextColor(getResources().getColor(R.color.blue_grey_300));

    }

    /**
     * 设置点击事件
     */
    private void setOnClick() {
        mVoteLayout.setOnClickListener(new Listener());
        mNoticeLayout.setOnClickListener(new Listener());
        mMeLayout.setOnClickListener(new Listener());

    }

    /**
     * 点击事件的处理
     */
    private class Listener implements OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.vote_layout:
                    // 更改图标
                    initPic(0);
                    //页面切换
                    break;
                case R.id.notice_layout:
                    initPic(1);
                    break;
                case R.id.me_layout:
                    initPic(2);
                    break;
            }
            icallBackListener.onClick(v.getId());
        }
    }

    /**
     * 重置图标颜色
     * @param layout
     */
    private void reSetStatus(RelativeLayout layout,int id,String name) {

        layout.findViewById(R.id.item_image).setBackgroundResource(id);
        TextView tv = (TextView) layout.findViewById(R.id.tv_barname);
        tv.setTextColor(getResources().getColor(R.color.blue_grey_300));
        if (name!=null){
            tv.setText(name);
        }
    }

    private void changeStatus(RelativeLayout layout,int id){
        // 切换成点击状态
        layout.findViewById(R.id.item_image).setBackgroundResource(id);
        TextView tv = (TextView) layout.findViewById(R.id.tv_barname);
        tv.setTextColor(getResources().getColor(R.color.cyan_600));
    }

    private void initPic(int i) {
        switch (i) {
            case 0:
                // 切换成点击状态
                changeStatus(mVoteLayout,R.drawable.ic_star_cyan_600_18dp);

                // 还原其他图标默认状态
                reSetStatus(mNoticeLayout,R.drawable.ic_assignment_blue_grey_300_18dp,null);
                reSetStatus(mMeLayout,R.drawable.ic_sentiment_satisfied_blue_grey_300_18dp,null);
                break;
            case 1:

                // 切换成点击状态
                changeStatus(mNoticeLayout,R.drawable.ic_assignment_ind_cyan_600_18dp);

                // 还原其他图标默认状态
                reSetStatus(mVoteLayout,R.drawable.ic_star_border_blue_grey_300_18dp,null);
                reSetStatus(mMeLayout,R.drawable.ic_sentiment_satisfied_blue_grey_300_18dp,null);
                break;
            case 2:
                // 切换成点击状态
                changeStatus(mMeLayout, R.drawable.ic_sentiment_very_satisfied_cyan_600_18dp);

                // 还原其他图标默认状态
                reSetStatus(mNoticeLayout,R.drawable.ic_assignment_blue_grey_300_18dp,null);
                reSetStatus(mVoteLayout,R.drawable.ic_star_border_blue_grey_300_18dp,null);

                break;
        }

    }

    /**
     * 寻找控件
     *
     * @param view
     */
    private void findView(View view) {
        mVoteLayout = (RelativeLayout) view.findViewById(R.id.vote_layout);
        mNoticeLayout = (RelativeLayout) view.findViewById(R.id.notice_layout);
        mMeLayout = (RelativeLayout) view.findViewById(R.id.me_layout);

    }
    public interface IcallBackListener{
        public void onClick(int id);
    }
    IcallBackListener icallBackListener = null;
    public void setOnCallBackListener(IcallBackListener icallBackListener){
        this.icallBackListener =icallBackListener;

    }
}
