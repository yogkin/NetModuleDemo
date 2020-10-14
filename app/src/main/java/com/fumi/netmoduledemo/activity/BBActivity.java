package com.fumi.netmoduledemo.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.fumi.net_module.ApiUtils;
import com.fumi.net_module.bean.BaseHttpBean;
import com.fumi.netmoduledemo.R;
import com.fumi.netmoduledemo.activity.BaseActivity;
import com.fumi.netmoduledemo.bean.ResultBean;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Flowable;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class BBActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Flowable<BaseHttpBean<List<ResultBean>>> flowable = getMyService().getRoomLiveList(new HashMap<String, String>());
        ApiUtils.execute(flowable, this, 0, new Function1<List<ResultBean>, Unit>() {
            @Override
            public Unit invoke(List<ResultBean> resultBeans) {
                return null;
            }
        });

    }
}
