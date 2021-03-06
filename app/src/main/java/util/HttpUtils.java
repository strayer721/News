package util;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/3.
 */

public class HttpUtils {
    public void connection(String uri, final OnladResponseListener listener,RequestQueue requestQueue){

    StringRequest request=new StringRequest(Request.Method.GET, uri, new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
listener.getResponse(response);
        }
    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
//log.e("---",""+error.getMessage());
            Log.e("---",""+error.getMessage());
        }
    });
        requestQueue.add(request);
    }
    public void connectionPost(String url, final String name, final String email, final String password, final OnladResponseListener listener, RequestQueue requestQueue){
StringRequest request=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
    @Override
    public void onResponse(String response) {
listener.getResponse(response);
    }
}, new Response.ErrorListener() {
    @Override
    public void onErrorResponse(VolleyError error) {

    }
}){
    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        HashMap<String, String> map = new HashMap<>();
        map.put("ver","1");
        map.put("uid",name);
        map.put("email",email);
        map.put("pwd",password);
        return map;
    }
};
        requestQueue.add(request);
    }
    public void enterPost(String url, final String nickName, final String password, final OnladResponseListener listener, RequestQueue requestQueue){
        StringRequest request=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
listener.getResponse(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String , String > map = new HashMap<>();
                map.put("ver","1");
                map.put("uid",nickName);
                map.put("pwd",password);
                map.put("device","0");
                return map;
            }
        };
requestQueue.add(request);
    }
    public void logPost(String url,final String token, final OnladResponseListener listener,RequestQueue requestQueue){
        StringRequest request=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                listener.getResponse(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String , String > map = new HashMap<>();
                map.put("ver","1");
                map.put("imei","10");
                map.put("token",token);
                return map;
            }
        };
        requestQueue.add(request);
    }
    public  void commentPost(String url,final String nid,final String cid,final OnladResponseListener listener,RequestQueue requestQueue){
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                listener.getResponse(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> map = new HashMap<>();
                map.put("ver","1");
                map.put("nid",nid);
                map.put("type","1");
                map.put("stamp","yyyyMMdd");
                map.put("cid",cid);
                map.put("dir","1");
                map.put("cnt","20");
                return map;
            }



        };
        requestQueue.add(request);
    }
    public void commentNumberPost(String url, final String nid, final OnladResponseListener onladResponseListener, RequestQueue requestQueue){
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                onladResponseListener.getResponse(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> map = new HashMap<>();
                map.put("ver","1");
                map.put("nid",nid);
                return map;
            }
        };
        requestQueue.add(request);
    }


}
