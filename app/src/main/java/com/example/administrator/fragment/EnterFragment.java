package com.example.administrator.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.administrator.news.R;
import com.example.administrator.news.UserActivity;

import org.json.JSONException;
import org.json.JSONObject;

import HttpInfo.HttpInfo;
import util.HttpUtils;
import util.OnladResponseListener;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Administrator on 2016/11/3.
 */

public class EnterFragment extends Fragment implements View.OnClickListener, OnladResponseListener {
    EditText mEt_nickame;
    EditText mEt_password;
    Button mBtn_register;
    Button mBtn_forgetPassword;
    Button mBtn_enter;
    DrawerLayout mDrawerLayout;
    RequestQueue mRequestQueue;

//    public static final String PREFS_NAME="config";
    SharedPreferences mSharedPreferences;
    int mResult;
int mStatus;
    String mToken;
    String mMes;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.enter,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initer(view);

    }

    private void initer(View view) {
        mEt_nickame= (EditText) view.findViewById(R.id.et_enter_nickname);
        mEt_password= (EditText) view.findViewById(R.id.et_enter_password);
        mBtn_register= (Button) view.findViewById(R.id.btn_register);
        mBtn_forgetPassword= (Button) view.findViewById(R.id.btn_forgetPassword);
        mBtn_enter= (Button) view.findViewById(R.id.btn_enter);
        mEt_nickame.setOnClickListener(this);
        mEt_password.setOnClickListener(this);
        mBtn_register.setOnClickListener(this);
        mBtn_forgetPassword.setOnClickListener(this);
        mBtn_enter.setOnClickListener(this);
        mRequestQueue = Volley.newRequestQueue(getActivity());

        mDrawerLayout= (DrawerLayout) view.findViewById(R.id.drawerLayout);
    }



    @Override
    public void getResponse(String message) {
        Log.e("---=-=-=-=-=-=-=-=-=",message);
        try {
             mSharedPreferences=this.getActivity().getSharedPreferences("user",MODE_PRIVATE);
            SharedPreferences.Editor editor = mSharedPreferences.edit();

            JSONObject object=new JSONObject(message);
             int status=object.getInt("status");
if (status==-1)  {
        Log.e("-------","status==++++++++++++++"+status);
        Toast.makeText(getActivity(),"用户名或密码错误！",Toast.LENGTH_SHORT).show();

                }

            Log.e("-------","status=="+status);
            String da=object.getString("data");
            String mes=object.getString("message");
            editor.putString("message",mes);


            JSONObject data=new JSONObject(da);
            int result=data.getInt("result");
                    String token=data.getString("token");
            Log.e("ffffffffffffffffffffff",""+token);
            editor.putInt("status",status);
            editor.putInt("result",result);
            editor.putString("token",token);
Log.e("-----------------",""+editor.putString("token",token));
            Log.e("-----------------","sssssssss"+editor.putInt("result",result));
            editor.commit();
            mStatus= mSharedPreferences.getInt("status",-1);
             mResult= mSharedPreferences.getInt("result",-1);
             mToken=mSharedPreferences.getString("token","");
            mMes=mSharedPreferences.getString("message","");
            Log.e("========","++++++"+mStatus);
            Log.e("====","mToken=="+mToken);
                Log.e("-------------------","mMes========="+status);
            if(status==0
                    //&&mResult==0
                    ){

        Toast.makeText(getActivity(),"登录成功！",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(getActivity(), UserActivity.class);
                    startActivity(intent);
                Log.e("-------------------","mMes========="+mMes);
            }





        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_register:

                FragmentTransaction fragmentRegister = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentRegister.replace(R.id.fragLayout,new RegisterFragment());
                fragmentRegister.commit();
               // mDrawerLayout.closeDrawer(Gravity.RIGHT);
                break;
            case R.id.btn_forgetPassword:
                break;
            case R.id.btn_enter:

                String nickName=mEt_nickame.getText().toString();
                String password=mEt_password.getText().toString();
                new HttpUtils().enterPost(HttpInfo.BASE_URI+HttpInfo.ENTER,nickName,password,this,mRequestQueue);

//Log.e("sssssssssss","aaaaaaaaaaaaaaaaaaaaaa====="+mStatus);

                break;
        }
    }
}
