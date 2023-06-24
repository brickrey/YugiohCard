package com.brickrey.yugiohcard.network;

import android.content.Context;
import android.util.Log;

import com.android.volley.Cache;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.brickrey.yugiohcard.SharedPreferenceManager;

public class VolleyManager {

    public final static String VOLLEY_REQUESTS_TAG = VolleyManager.class.getCanonicalName();

    private static VolleyManager singleton;
    private RequestQueue requestQueue;
    private static Context context;

    private VolleyManager(Context context){
        VolleyManager.context = context;
        requestQueue = getRequestQueue();
    }

    public static synchronized VolleyManager getInstance(Context context) {
        if (singleton == null) {
            singleton = new VolleyManager(context);
        }
        return singleton;
    }

    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {

            /* Incrementing concurrent connections and complementing Volley with OkHttp */
            Cache cache = new DiskBasedCache(context.getCacheDir(), 1024 * 1024 * 10);   // 10 Mb
            Network network = new BasicNetwork(new HurlStack());
            int concurrentConnections = SharedPreferenceManager.getVolleyConcurrentConnections(context); // Default 15
            requestQueue = new RequestQueue(cache, network, concurrentConnections);
            requestQueue.start();

            Log.d(VolleyManager.class.getName(), requestQueue.toString());
        }
        return requestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(VOLLEY_REQUESTS_TAG);
        req.setShouldCache(false);
        req.setRetryPolicy(new DefaultRetryPolicy(NetworkConstants.WS_SOCKET_TIMEOUT, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        req.setTag(tag);
        req.setShouldCache(false);
        req.setRetryPolicy(new DefaultRetryPolicy(NetworkConstants.WS_SOCKET_TIMEOUT, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        getRequestQueue().add(req);
    }
}
