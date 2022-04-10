package com.memoeslink.generator.common;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;

import com.memoeslink.generator.R;
import com.memoeslink.generator.common.finder.ContactNameFinder;
import com.memoeslink.generator.common.finder.ResourceFinder;

import java.util.Locale;

public abstract class Explorer extends Binder {
    protected ResourceFinder resourceFinder;
    protected ContactNameFinder contactNameFinder;
    protected Device device;

    protected Explorer(Context context) {
        this(context, null);
    }

    protected Explorer(Context context, Long seed) {
        super(context, seed);
        device = new Device(context);
        resourceFinder = new ResourceFinder(context);
        contactNameFinder = new ContactNameFinder(context);
        initializeFinders(seed);
    }

    public ResourceFinder getResourceFinder() {
        return resourceFinder;
    }

    public ContactNameFinder getContactNameFinder() {
        return contactNameFinder;
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

    public String findRes(int id) {
        return findRes(id, -1);
    }

    public String findRes(int id, int index) {
        if (!resourceFinder.isResource(id))
            return ResourceFinder.RESOURCE_NOT_FOUND;
        else if (getResources().getResourceTypeName(id).equals("array"))
            return index >= 0 ? resourceFinder.getStrFromStrArrayRes(id, index) : resourceFinder.getStrFromStrArrayRes(id);
        else if (getResources().getResourceTypeName(id).equals("string"))
            return index >= 0 ? resourceFinder.getStrFromSplitStrRes(id, index) : resourceFinder.getStrFromSplitStrRes(id);
        return ResourceFinder.RESOURCE_NOT_FOUND;
    }

    public String findResByName(String name) {
        return findResByName(name, -1);
    }

    public String findResByName(String name, int index) {
        if (StringHelper.isNullOrBlank(name))
            return ResourceFinder.RESOURCE_NOT_FOUND;
        int id = resourceFinder.getArrayResourceId(name);

        if (resourceFinder.getArrayResourceId(name) >= 0)
            return index >= 0 ? resourceFinder.getStrFromStrArrayRes(id, index) : resourceFinder.getStrFromStrArrayRes(id);
        id = resourceFinder.getStringResourceId(name);

        if (resourceFinder.getStringResourceId(name) >= 0)
            return index >= 0 ? resourceFinder.getStrFromSplitStrRes(id, index) : resourceFinder.getStrFromSplitStrRes(id);
        return ResourceFinder.RESOURCE_NOT_FOUND;
    }

    public String findByRef(ExplorerReference reference) {
        if (reference == null)
            return ResourceFinder.RESOURCE_NOT_FOUND;

        switch (reference) {
            case EMOJI:
                int unicode = resourceFinder.getIntFromIntArrayRes(R.array.emojis);
                return new String(Character.toChars(unicode));
            case EMOTICON:
                return resourceFinder.getStrFromStrArrayRes(R.array.emoticons);
            case KAOMOJI:
                return resourceFinder.getStrFromStrArrayRes(R.array.kaomojis);
            default:
                return ResourceFinder.RESOURCE_NOT_FOUND;
        }
    }

    public int findArrayLength(int id) {
        if (!resourceFinder.isResource(id))
            return 0;
        else if (getResources().getResourceTypeName(id).equals("array"))
            return resourceFinder.getArrayResLength(id);
        else if (getResources().getResourceTypeName(id).equals("string"))
            return resourceFinder.getSplitStrResLength(id);
        return 0;
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
