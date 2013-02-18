package org.yousense.shared.data;

import android.content.Context;
import android.telephony.TelephonyManager;

public class TelephonyData {
    String device_id;
    String network_operator_name;
    String sim_operator_name;

    public TelephonyData(Context context) {
        TelephonyManager tm = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
        // Catch exceptions in case SecurityException is thrown on some devices, instead of returning null.
        try { this.device_id = tm.getDeviceId(); } catch (Exception e) {}
        try { this.network_operator_name = tm.getNetworkOperatorName(); } catch (Exception e) {}
        try { this.sim_operator_name = tm.getSimOperatorName(); } catch (Exception e) {}
    }

}
