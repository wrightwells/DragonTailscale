package com.example.dragontailscaleboot;
import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.widget.TextView;
public class MainActivity extends Activity {
    @Override public void onCreate(Bundle state) {
        super.onCreate(state);
        TextView t = new TextView(this);
        t.setText("Dragon Tailscale Boot\n\nStarting Tailscale service.\nCheck with ADB: tailscale status and logcat -s DragonTailscaleBoot");
        t.setPadding(24,24,24,24);
        setContentView(t);
        startService(new Intent(this, TailscaleService.class));
    }
}
