package com.memoeslink.generator;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.memoeslink.generator.common.Database;
import com.memoeslink.generator.common.Session;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class SessionTest {

    @Test
    public void initDatabase() {
        assertNotNull(Session.getInstance().getDatabase());
        assertNotEquals(Database.DEFAULT_VALUE, Database.selectUsername());
    }
}