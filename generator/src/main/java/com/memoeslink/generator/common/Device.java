package com.memoeslink.generator.common;

import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.SupplicantState;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;

import com.memoeslink.generator.R;
import com.memoeslink.generator.common.finder.ResourceFinder;

import java.lang.reflect.Field;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class Device extends ContextWrapper {
    private static final Field[] VERSION_CODES;

    static {
        VERSION_CODES = Build.VERSION_CODES.class.getFields();
    }

    public Device(Context context) {
        super(context);
    }

    public String getInfo(int type) {
        type = IntegerHelper.defaultInt(type, 1, 9);

        switch (type) {
            case 1:
                String name = getAndroidVersionName();

                if (StringHelper.isNullOrEmpty(name)) {
                    try {
                        name = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
                    } catch (PackageManager.NameNotFoundException ignored) {
                    }
                }

                if (StringHelper.isNullOrEmpty(name))
                    return getString(R.string.device_android);
                return getString(R.string.device_version, name + " " + "(" + Build.VERSION.RELEASE + ")");
            case 2:
                return getString(R.string.device, StringHelper.defaultWhenEmpty(Build.MANUFACTURER) + StringHelper.prependSpaceIfNotEmpty(Build.MODEL));
            case 3:
                return getString(R.string.device, StringHelper.defaultWhenEmpty(Build.BRAND) + StringHelper.prependSpaceIfNotEmpty(Build.MODEL));
            case 4:
                return getString(R.string.device, StringHelper.defaultWhenEmpty(Build.PRODUCT));
            case 5:
                return getString(R.string.device_id, getDeviceId());
            case 6:
                return getString(R.string.device_brand, StringHelper.defaultWhenEmpty(Build.BRAND));
            case 7:
                String networkName = getNetworkName();

                if (StringHelper.isNullOrEmpty(networkName))
                    return getString(R.string.device_network_disconnected);
                else if (networkName.equals("<unknown ssid>"))
                    return getString(R.string.device_network_unknown_ssid);
                return getString(R.string.device_network_ssid, networkName);
            case 8:
                String networkOperator = getNetworkOperator();
                return StringHelper.isNullOrBlank(networkOperator) ? getString(R.string.device_network_operator_disconnected) :
                        getString(R.string.device_network_operator, networkOperator);
            case 9:
                String ipAddress = getLocalIpAddress();
                return StringHelper.isNullOrEmpty(ipAddress) ? getString(R.string.device_unknown_ip) :
                        getString(R.string.device_ip, ipAddress);
            default:
                return getString(R.string.device_unidentified);
        }
    }

    public String getNetworkName() {
        if (!isNetworkAvailable())
            return ResourceFinder.RESOURCE_NOT_FOUND;

        WifiManager wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInfo = null;

        if (wifiManager != null)
            wifiInfo = wifiManager.getConnectionInfo();

        if (wifiInfo != null && wifiInfo.getSupplicantState() == SupplicantState.COMPLETED)
            return StringHelper.trimToDefault(wifiInfo.getSSID());
        return ResourceFinder.RESOURCE_NOT_FOUND;
    }

    public String getNetworkOperator() {
        TelephonyManager manager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);

        if ((manager != null ? manager.getSimState() : 0) == TelephonyManager.SIM_STATE_READY)
            return manager.getNetworkOperatorName();
        return ResourceFinder.RESOURCE_NOT_FOUND;
    }

    public String getNetworkCountry() {
        TelephonyManager manager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);

        if ((manager != null ? manager.getSimState() : 0) == TelephonyManager.SIM_STATE_READY)
            return manager.getNetworkCountryIso();
        return ResourceFinder.RESOURCE_NOT_FOUND;
    }

    public String getLocalIpAddress() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface networkInterface = en.nextElement();

                for (Enumeration<InetAddress> enumIpAddress = networkInterface.getInetAddresses(); enumIpAddress.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddress.nextElement();

                    if (!inetAddress.isLoopbackAddress())
                        return inetAddress.getHostAddress();
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return ResourceFinder.RESOURCE_NOT_FOUND;
    }

    public String getTestDeviceId() {
        return StringHelper.md5(getDeviceId()).toUpperCase();
    }

    public String getDeviceId() {
        String androidId = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);

        if (StringHelper.isNullOrBlank(androidId) || androidId.length() <= 4)
            return getDevicePseudoId();
        return StringHelper.mask(androidId);
    }

    public String getDevicePseudoId() {
        return String.format("%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c",
                CharHelper.getHexDigit(Build.BOARD.length()),
                CharHelper.getHexDigit(Build.BOOTLOADER.length()),
                CharHelper.getHexDigit(Build.BRAND.length()),
                CharHelper.getHexDigit(Build.DEVICE.length()),
                CharHelper.getHexDigit(Build.DISPLAY.length()),
                CharHelper.getHexDigit(Build.FINGERPRINT.length()),
                CharHelper.getHexDigit(Build.HARDWARE.length()),
                CharHelper.getHexDigit(Build.HOST.length()),
                CharHelper.getHexDigit(Build.ID.length()),
                CharHelper.getHexDigit(Build.MANUFACTURER.length()),
                CharHelper.getHexDigit(Build.MODEL.length()),
                CharHelper.getHexDigit(Build.PRODUCT.length()),
                CharHelper.getHexDigit(Build.TAGS.length()),
                CharHelper.getHexDigit(Build.TYPE.length()),
                CharHelper.getHexDigit(Build.USER.length())
        );
    }

    public String getAndroidVersionName() {
        if (VERSION_CODES == null || VERSION_CODES.length == 0)
            return ResourceFinder.RESOURCE_NOT_FOUND;

        for (Field versionCode : VERSION_CODES) {
            String versionName = versionCode.getName();
            int fieldValue = -1;

            try {
                fieldValue = versionCode.getInt(new Object());
            } catch (IllegalArgumentException | IllegalAccessException | NullPointerException e) {
                e.printStackTrace();
            }

            if (fieldValue == Build.VERSION.SDK_INT)
                return versionName;
        }
        return ResourceFinder.RESOURCE_NOT_FOUND;
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null;
    }
}
