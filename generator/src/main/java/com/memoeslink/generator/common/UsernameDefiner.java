package com.memoeslink.generator.common;

public interface UsernameDefiner {

    public String getEmptyUsername();

    public String getUsername();

    public String getCompositeUsername();

    public String getDerivedUsername();

    public String getPatternUsername();

    public String getAdminUsername();

    public String getAnonymousName();

    public String getAnonymousAnimal();
}
