package com.master.recycleviewuse;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;


    private PeoriaAdapter mAdapter;
    private Context context;
    private List<Object> mPics = new ArrayList<>();
    private List<Object> mLogos = new ArrayList<>();
    private List<Object> mTexts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 准备数据
        initData();
        // 初始化View
        initView();
    }

    private void initData() {
        Object[] arrayItemPic = new Object[]{R.mipmap.pic2442, R.mipmap.pic2443, R.mipmap.pic2444, R.mipmap.pic2445, R.mipmap.pic2446};
        Object[] arrayItemLogo = new Object[]{R.mipmap.logo1, R.mipmap.logo2, R.mipmap.logo3, R.mipmap.logo4, R.mipmap.logo3};
        Object[] arrayItemText = new Object[]{"我如果爱你,绝不像攀援的凌霄花,借你的高枝炫耀自己:", "我如果爱你,绝不学痴情的鸟儿,为绿荫重复单调的歌曲:", "也不止像泉源,常年送来清凉的慰籍;也不止像险峰,增加你的高度,衬托你的威仪. ", "我们分担寒潮,风雷,霹雳: 我们共享雾霭、流岚、虹霓.", "仿佛永远分离,却又终身相依,这才是伟大的爱情."};

        addData(arrayItemPic, mPics);
        addData(arrayItemLogo, mLogos);
        addData(arrayItemText, mTexts);
    }

    private void initView() {
        context = this;
        mRecyclerView = (RecyclerView) findViewById(R.id.rv);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        // 设置分割线(自定义颜色详见values->style->android:listDivider属性)
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL));
        // 设置item动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        // 设置适配器
        mRecyclerView.setAdapter(mAdapter = new PeoriaAdapter(context, mPics, mLogos, mTexts));
        // 设置触摸监听
        mRecyclerView.addOnItemTouchListener(new SwipeItemLayout.OnSwipeItemTouchListener(context));
        // 初始化刷新控件
        RefreshLayout refreshLayout = (RefreshLayout) findViewById(R.id.refreshLayout);
        // 设置刷新的头布局
        refreshLayout.setRefreshHeader(new com.master.recycleviewuse.ClassicsHeader(this));
        // 设置刷新的头布局高度
        refreshLayout.setHeaderHeight(60);
        // 设置刷新的脚布局
        refreshLayout.setRefreshFooter(new ClassicsFooter(this));
        // 添加刷新监听 ->下拉刷新
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                mPics.clear();
                mLogos.clear();
                mTexts.clear();
                initData();
                mAdapter.notifyDataSetChanged();

                refreshlayout.finishRefresh(2000);
            }
        });
        // 添加刷新监听 ->上拉加载更多
        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {

                loadMore();
                mAdapter.notifyDataSetChanged();
                refreshlayout.finishLoadmore(2000);
            }
        });
    }

    private void loadMore() {
        Object[] arrayItemPic = new Object[]{R.mipmap.pic2442, R.mipmap.pic2443, R.mipmap.pic2444, R.mipmap.pic2445, R.mipmap.pic2446};
        Object[] arrayItemLogo = new Object[]{R.mipmap.logo1, R.mipmap.logo2, R.mipmap.logo3, R.mipmap.logo4, R.mipmap.logo3};
        Object[] arrayItemText = new Object[]{"我如果爱你,绝不像攀援的凌霄花,借你的高枝炫耀自己:", "我如果爱你,绝不学痴情的鸟儿,为绿荫重复单调的歌曲:", "也不止像泉源,常年送来清凉的慰籍;也不止像险峰,增加你的高度,衬托你的威仪. ", "我们分担寒潮,风雷,霹雳: 我们共享雾霭、流岚、虹霓.", "仿佛永远分离,却又终身相依,这才是伟大的爱情."};

        addData(arrayItemPic, mPics);
        addData(arrayItemLogo, mLogos);
        addData(arrayItemText, mTexts);
    }

    private void addData(Object[] obarray, List<Object> mlist) {
        for (int i = 0; i < 25; i++) {
            mlist.add((obarray[i % 5]));
        }
    }
}
