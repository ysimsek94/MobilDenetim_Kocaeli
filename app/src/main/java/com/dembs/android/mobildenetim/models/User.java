package com.dembs.android.mobildenetim.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class User implements Serializable
{

    @SerializedName("UserName")
    @Expose
    private String userName;
    @SerializedName("Password")
    @Expose
    private String password;
    @SerializedName("ServiceUserName")
    @Expose
    private String serviceUserName;
    @SerializedName("ServicePassword")
    @Expose
    private String servicePassword;
    private final static long serialVersionUID = 7377794859102068590L;

    /**
     * No args constructor for use in serialization
     *
     */
    public User() {
    }

    /**
     *
     * @param password
     * @param servicePassword
     * @param userName
     * @param serviceUserName
     */
    public User(String userName, String password, String serviceUserName, String servicePassword) {
        super();
        this.userName = userName;
        this.password = password;
        this.serviceUserName = serviceUserName;
        this.servicePassword = servicePassword;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getServiceUserName() {
        return serviceUserName;
    }

    public void setServiceUserName(String serviceUserName) {
        this.serviceUserName = serviceUserName;
    }

    public String getServicePassword() {
        return servicePassword;
    }

    public void setServicePassword(String servicePassword) {
        this.servicePassword = servicePassword;
    }

}