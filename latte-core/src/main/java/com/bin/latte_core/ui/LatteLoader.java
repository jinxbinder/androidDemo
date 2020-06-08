package com.bin.latte_core.ui;

import android.content.Context;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatDialog;

import com.bin.latte_core.R;
import com.bin.latte_core.utils.DimenUtil;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;

/**
 * @author： libd
 * @date： 2020/6/8 11:13
 * @version: 1.0
 */
public class LatteLoader {
    //8倍缩放
    private static final int LOADER_SIZE_SCALE = 8;
    //偏移量10
    private static final int LOADER_OFFSET_SCALE = 10;

    private static final ArrayList<AppCompatDialog> LOADERS = new ArrayList<>();

    private static final String DEFAULT_LOADER = LoaderStyle.BallClipRotateIndicator.name();

    public static void showLoading(Context context,Enum<LoaderStyle> type){
        showLoading(context, type.name());
    }

    public static void showLoading(Context context,String type){

        final AppCompatDialog dialog = new AppCompatDialog(context, R.style.dialog);

        final AVLoadingIndicatorView avLoadingIndicatorView = LoaderCreator.create(type, context);
        dialog.setContentView(avLoadingIndicatorView);

/*        int deviceWidth = DimenUtil.getScreenWidth();
        int deviceHeight = DimenUtil.getScreenHeight();*/
        int deviceWidth = 1000;
        int deviceHeight = 1500;

        final Window dialogWindow = dialog.getWindow();

        if(dialogWindow != null){
            WindowManager.LayoutParams lp = dialogWindow.getAttributes();
            lp.width = deviceWidth/LOADER_SIZE_SCALE;
            lp.height = deviceHeight/LOADER_SIZE_SCALE;
            lp.height = lp.height+deviceHeight/LOADER_OFFSET_SCALE;
            //居中
            lp.gravity = Gravity.CENTER;
        }
        LOADERS.add(dialog);
        dialog.show();
    }

    public static void showLoading(Context context){
        showLoading(context, DEFAULT_LOADER);
    }

    public static void stopLoading(){
        for(AppCompatDialog dialog:LOADERS){
            if(dialog != null){
                if(dialog.isShowing()){
                    dialog.cancel();
                }
            }
        }
    }
}
