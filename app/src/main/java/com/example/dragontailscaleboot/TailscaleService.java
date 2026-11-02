package com.example.dragontailscaleboot;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
public class TailscaleService extends Service {
    private static final String TAG = "DragonTailscaleBoot";
    @Override public int onStartCommand(Intent intent, int flags, int startId) {
        new Thread(() -> {
            try {
                // su may request permission. The bundled start.sh is owned by root.
                Process p = new ProcessBuilder("/system/xbin/su", "-c", "/system/bin/sh /data/local/tailscale/start.sh").redirectErrorStream(true).start();
                int code = p.waitFor();
                Log.i(TAG, "startup script exited " + code);
            } catch (Exception e) { Log.e(TAG, "startup failed", e); }
        }, "tailscale-start").start();
        return START_STICKY;
    }
    @Override public IBinder onBind(Intent intent) { return null; }
}
