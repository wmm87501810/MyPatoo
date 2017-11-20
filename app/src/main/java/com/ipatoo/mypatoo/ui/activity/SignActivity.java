package com.ipatoo.mypatoo.ui.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.FrameLayout.LayoutParams;

import com.ipatoo.mypatoo.R;
import com.ipatoo.mypatoo.bean.AbsResponse;
import com.ipatoo.mypatoo.bean.User;
import com.ipatoo.mypatoo.bean.VerifyMsg;
import com.ipatoo.mypatoo.net.HttpUtils;
import com.ipatoo.mypatoo.net.UrlConstant;
import com.ipatoo.mypatoo.net.ok_http.callback.ResultCallback;
import com.mob.MobApplication;
import com.mob.MobSDK;
import com.squareup.okhttp.Request;

import java.util.HashMap;
import java.util.Map;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

public class SignActivity extends AppCompatActivity implements View.OnClickListener {
    String APPKEY = "227b81873bdb0";
    String APPSECRETE = "a4293c7ca8af94b205c02addbf9fa17d";
    //手机号输入框
    private EditText inputPhoneEt;
    //验证码输入框
    private EditText inputCodeEt;
    //获取验证码按钮
    private TextView requestCodeTv;
    //下一步按钮
    private TextView nextStepTv;
    //倒计时
    int i = 30;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);
        init();
    }

    /**
     * 初始化控件
     */
    private void init() {
        inputPhoneEt = (EditText) findViewById(R.id.input_phone_et);
        inputCodeEt = (EditText) findViewById(R.id.input_code_et);
        requestCodeTv = (TextView) findViewById(R.id.request_code_tv);
        nextStepTv = (TextView) findViewById(R.id.next_step_tv);
        requestCodeTv.setOnClickListener(this);
        nextStepTv.setOnClickListener(this);

        //启动短信验证sdk
        MobSDK.init(this, APPKEY, APPSECRETE);
        EventHandler eventHandler = new EventHandler() {
            @Override
            public void afterEvent(int event, int result, Object data) {
                Message msg = new Message();
                msg.arg1 = event;
                msg.arg2 = result;
                msg.obj = data;
                handler.sendMessage(msg);
            }
        };
        //注册回调监听接口
        SMSSDK.registerEventHandler(eventHandler);
    }

    @Override
    public void onClick(View v) {
        String phoneNums = inputPhoneEt.getText().toString();
        switch (v.getId()) {
            case R.id.request_code_tv:
                // 1. 通过规则判断手机号
                if (!judgePhoneNums(phoneNums)) {
                    return;
                }
                postSendMsg(inputPhoneEt.getText().toString());

                // 2. 通过sdk发送短信验证
                SMSSDK.getVerificationCode("86", phoneNums);

                // 3. 把按钮变成不可点击，并且显示倒计时（正在获取）
                requestCodeTv.setClickable(false);
                requestCodeTv.setText("重新发送(" + i + ")");
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for (; i > 0; i--) {
                            handler.sendEmptyMessage(-9);
                            if (i <= 0) {
                                break;
                            }
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        handler.sendEmptyMessage(-8);
                    }
                }).start();
                break;

            case R.id.next_step_tv:
                //将收到的验证码和手机号提交再次核对
                SMSSDK.submitVerificationCode("86", phoneNums, inputCodeEt
                        .getText().toString());
                //createProgressBar();
                break;
        }
    }

    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            if (msg.what == -9) {
                requestCodeTv.setText("重新发送(" + i + ")");
            } else if (msg.what == -8) {
                requestCodeTv.setText("获取验证码");
                requestCodeTv.setClickable(true);
                i = 30;
            } else {
                int event = msg.arg1;
                int result = msg.arg2;
                Object data = msg.obj;
                Log.e("event", "event=" + event);
                if (result == SMSSDK.RESULT_COMPLETE) {
                    // 短信注册成功后，返回MainActivity,然后提示
                    if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {// 提交验证码成功
                        Toast.makeText(getApplicationContext(), "提交验证码成功",
                                Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(SignActivity.this,
                                MainActivity.class);
                        startActivity(intent);
                    } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                        Toast.makeText(getApplicationContext(), "正在获取验证码",
                                Toast.LENGTH_SHORT).show();
                    } else {
                        ((Throwable) data).printStackTrace();
                    }
                }
            }
        }
    };


    /**
     * 判断手机号码是否合理
     *
     * @param phoneNums
     */
    private boolean judgePhoneNums(String phoneNums) {
        if (isMatchLength(phoneNums, 11)
                && isMobileNO(phoneNums)) {
            return true;
        }
        Toast.makeText(this, "手机号码输入有误！", Toast.LENGTH_SHORT).show();
        return false;
    }

    /**
     * 判断一个字符串的位数
     *
     * @param str
     * @param length
     * @return
     */
    public static boolean isMatchLength(String str, int length) {
        if (str.isEmpty()) {
            return false;
        } else {
            return str.length() == length ? true : false;
        }
    }

    /**
     * 验证手机格式
     */
    public static boolean isMobileNO(String mobileNums) {
        /*
         * 移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
         * 联通：130、131、132、152、155、156、185、186 电信：133、153、180、189、（1349卫通）
         * 总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9
         */
        String telRegex = "[1][358]\\d{9}";// "[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        if (TextUtils.isEmpty(mobileNums))
            return false;
        else
            return mobileNums.matches(telRegex);
    }

    /**
     * progressbar
     */
    private void createProgressBar() {
        FrameLayout layout = (FrameLayout) findViewById(android.R.id.content);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = Gravity.CENTER;
        ProgressBar mProBar = new ProgressBar(this);
        mProBar.setLayoutParams(layoutParams);
        mProBar.setVisibility(View.VISIBLE);
        layout.addView(mProBar);
    }

    /**
     * 发送验证码
     * @param mobile 手机号码
     */
    public void postSendMsg(String mobile) {
        Map<String, String> params = new HashMap();
        params.put("mobile", mobile);
        HttpUtils.doOkHttpPostRequest(UrlConstant.GET_SEND_MES, params, new ResultCallback<AbsResponse<VerifyMsg>>() {
            @Override
            public void onError(Request request, Exception e) {
                Toast.makeText(SignActivity.this,"失败",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onResponse(AbsResponse<VerifyMsg> response) {
                if (response != null && response.isSuccess()) {
                    Toast.makeText(SignActivity.this,response.getData().getCode(),Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    public void postLogin(String name) {
        Map<String, String> params = new HashMap();
        params.put("mobile", name);
        params.put("code", name);
        params.put("clientId", name);
        params.put("deviceToken", name);

        HttpUtils.doOkHttpPostRequest(UrlConstant.POST_LOGIN, params, new ResultCallback<AbsResponse<User>>() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(AbsResponse<User> response) {
                if (response != null && response.isSuccess()) {

                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        SMSSDK.unregisterAllEventHandler();
        super.onDestroy();
    }
}

