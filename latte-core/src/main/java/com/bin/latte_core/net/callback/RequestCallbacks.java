package com.bin.latte_core.net.callback;

import android.os.Handler;

import com.bin.latte_core.ui.LatteLoader;
import com.bin.latte_core.ui.LoaderStyle;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author： libd
 * @date： 2020/6/7 23:00
 * @version: 1.0
 */
public class RequestCallbacks implements Callback<String> {
    private final IRequest IREQUEST;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final LoaderStyle LOADER_STYLE;
    //static handler 避免内存泄漏
    private static final Handler HANDLER = new Handler();

    public RequestCallbacks(IRequest request,
                            ISuccess success,
                            IFailure failure,
                            IError error,
                            LoaderStyle style) {
        this.IREQUEST = request;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
        this.LOADER_STYLE = style;
    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        if(response.isSuccessful()){
            if(call.isExecuted()){
                if(SUCCESS != null){
                    SUCCESS.onSuccess(response.body());
                }
            }
        }else {
            if(ERROR != null){
                ERROR.onError(response.code(), response.message());
            }
        }

        if(LOADER_STYLE != null){
            HANDLER.postDelayed(new Runnable() {
                @Override
                public void run() {
                    LatteLoader.stopLoading();
                }
            }, 1000);
        }
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
        if(FAILURE != null){
            FAILURE.onFailure();
        }
        if(IREQUEST != null){
            IREQUEST.onRequestEnd();
        }
    }
}
