package com.bin;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.bin.latte_core.delegates.LatteDelegate;

/**
 * @author： libd
 * @date： 2020/6/7 13:29
 * @version: 1.0
 */
public class ExampleDelegate extends LatteDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_example;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
