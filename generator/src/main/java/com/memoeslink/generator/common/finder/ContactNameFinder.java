package com.memoeslink.generator.common.finder;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.provider.ContactsContract;

import androidx.core.content.ContextCompat;

import com.memoeslink.generator.common.Binder;
import com.memoeslink.generator.common.StringHelper;

import java.util.ArrayList;
import java.util.List;

public class ContactNameFinder extends Binder {
    private static List<String> contactNames = null;

    public ContactNameFinder(Context context) {
        this(context, null);
    }

    public ContactNameFinder(Context context, Long seed) {
        super(context, seed);
    }

    private List<String> getContactNames() {
        List<String> contacts = new ArrayList<>();
        boolean proceed = false;
        Cursor c = null;

        if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED)
            proceed = true;

        try {
            if (proceed) {
                ContentResolver contentResolver = getContentResolver();
                c = contentResolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);

                if ((c != null ? c.getCount() : 0) > 0 && c.moveToFirst()) {
                    do {
                        int index = c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
                        contacts.add(c.getString(index));
                    } while (c.moveToNext());
                }
            }
        } catch (Exception ignored) {
        } finally {
            if (c != null)
                c.close();
        }
        return contacts;
    }

    public int getContactNamesSize() {
        return contactNames == null ? 0 : contactNames.size();
    }

    public String getContactName() {
        if (contactNames == null)
            contactNames = getContactNames();

        if (contactNames.size() == 0)
            return ResourceFinder.RESOURCE_NOT_FOUND;
        return StringHelper.trimToEmpty(r.getItem(contactNames));
    }
}
