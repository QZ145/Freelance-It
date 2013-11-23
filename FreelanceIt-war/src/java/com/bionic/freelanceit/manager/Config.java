/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bionic.freelanceit.manager;

import java.util.ResourceBundle;

/**
 *
 * @author MAXIM
 */
public class Config {

    private static Config instance;
    private ResourceBundle resource;
    private static final String BUNDLE_NAME = "com.bionic.freelanceit.manager.config";
    public static final String DRIVER = "DRIVER";
    public static final String URL = "URL";
    public static final String MAIN = "MAIN";
    public static final String ERROR = "ERROR";
    public static final String LOGIN = "LOGIN";
    public static final String PROFILE = "PROFILE";
    public static final String DATASOURCE = "DATASOURCE";
    public static final String CHANGE_PROFILE = "CHANGE_PROFILE";
    public static final String CREATE_TASK = "CREATE_TASK";
    public static final String VIEW_TASKS_CREATED_BY_USER = "VIEW_TASKS_CREATED_BY_USER";

    public static Config getInstance() {
        if (instance == null) {
            instance = new Config();
            instance.resource = ResourceBundle.getBundle(BUNDLE_NAME);
        }
        return instance;
    }

    public String getProperty(String key) {
        return (String) resource.getObject(key);
    }
}
