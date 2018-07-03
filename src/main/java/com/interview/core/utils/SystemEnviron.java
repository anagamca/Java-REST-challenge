package com.interview.core.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.Properties;

/**
 * System environment retriever and service.
 */
public class SystemEnviron {
    private static SystemEnviron env;

    private final Properties props = new Properties();

    protected SystemEnviron(){
        loadVariables();
    }

    public static SystemEnviron getInstance(){
        if (env == null){
            env = new SystemEnviron();
        }
        return env;
    }

    private void loadVariables() {
        props.putAll(System.getenv());
    }

    public String getEnvVarFor(String key){
        return getEnvVarFor(key, StringUtils.EMPTY);
    }

    public String getEnvVarFor(String key, String defaultVal){
        String val = StringUtils.EMPTY;
        val = getEnvironmentVariable(key);
        return (StringUtils.isNotBlank(val)) ? val : defaultVal;
    }

    public void setEnvVarFor(String key, String value){
        props.setProperty(key, value);
    }

    private String getEnvironmentVariable(String key){
        String value = StringUtils.EMPTY;
        if(StringUtils.isNotBlank(key))
            value = props.getProperty(key);
        return value;
    }
}
