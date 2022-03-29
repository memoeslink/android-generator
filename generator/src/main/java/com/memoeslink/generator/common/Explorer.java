package com.memoeslink.generator.common;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;

import com.memoeslink.generator.R;

import java.util.Locale;

public class Explorer extends Binder {
    ResourceFinder resourceFinder;
    ContactNameFinder contactNameFinder;
    Device device;

    public Explorer(Context context) {
        this(context, null);
    }

    public Explorer(Context context, Long seed) {
        super(context, seed);
        device = new Device(context);
        initializeFinders(seed);
    }

    public void initializeFinders(Long seed) {
        resourceFinder.bindSeed(seed);
        contactNameFinder.bindSeed(seed);
    }

    @Override
    public void bindSeed(Long seed) {
        super.bindSeed(seed);
        initializeFinders(seed);
    }

    @Override
    public void unbindSeed() {
        super.unbindSeed();
        initializeFinders(null);
    }

    public String findByRef(ExplorerReference reference) {
        if (reference == null)
            return ResourceFinder.RESOURCE_NOT_FOUND;

        switch (reference) {
            case COLOR:
                return ResourceGetter.with(r).getString(Constant.COLORS);
            case EMOJI:
                int unicode = resourceFinder.getIntFromIntArrayRes(R.array.emojis);
                return new String(Character.toChars(unicode));
            default:
                return ResourceFinder.RESOURCE_NOT_FOUND;
        }
    }

    public String findGenderName(Gender gender, int type) {
        gender = gender != null ? gender : Gender.UNDEFINED;
        type = IntegerHelper.defaultInt(type, 1, 4);

        switch (type) {
            case 1:
                return resourceFinder.getStrArrayRes(R.array.genders)[gender.ordinal()];
            case 2:
                return StringHelper.capitalizeFirst(resourceFinder.getStrArrayRes(R.array.genders)[gender.ordinal()]);
            case 3:
                return resourceFinder.getStrArrayRes(R.array.genders)[gender.ordinal()].toUpperCase(Locale.ROOT);
            case 4:
                return StringHelper.getStart(resourceFinder.getStrArrayRes(R.array.genders)[gender.ordinal()]).toUpperCase(Locale.ROOT);
            default:
                return gender.toString();
        }
    }

    public String findDeviceInfo(int type) {
        type = IntegerHelper.defaultInt(type, 1, 9);

        switch (type) {
            case 1:
                String name = device.getAndroidVersionName();

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
                return getString(R.string.device_id, device.getDeviceId());
            case 6:
                return getString(R.string.device_brand, StringHelper.defaultWhenEmpty(Build.BRAND));
            case 7:
                String networkName = device.getNetworkName();

                if (StringHelper.isNullOrEmpty(networkName))
                    return getString(R.string.device_network_disconnected);
                else if (networkName.equals("<unknown ssid>"))
                    return getString(R.string.device_network_unknown_ssid);
                return getString(R.string.device_network_ssid, networkName);
            case 8:
                String networkOperator = device.getNetworkOperator();
                return StringHelper.isNullOrBlank(networkOperator) ? getString(R.string.device_network_operator_disconnected) :
                        getString(R.string.device_network_operator, networkOperator);
            case 9:
                String ipAddress = device.getLocalIpAddress();
                return StringHelper.isNullOrEmpty(ipAddress) ? getString(R.string.device_unknown_ip) :
                        getString(R.string.device_ip, ipAddress);
            default:
                return getString(R.string.device_unidentified);
        }
    }
}
