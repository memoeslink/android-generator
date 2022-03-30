package com.memoeslink.generator.common;

public class Pair<A, B> {
    private A subKey = null;
    private B subValue = null;

    public Pair(A subKey, B subValue) {
        this.subKey = subKey;
        this.subValue = subValue;
    }

    public A getSubKey() {
        return subKey;
    }

    public void setSubKey(A subKey) {
        this.subKey = subKey;
    }

    public B getSubValue() {
        return subValue;
    }

    public void setSubValue(B subValue) {
        this.subValue = subValue;
    }
}
