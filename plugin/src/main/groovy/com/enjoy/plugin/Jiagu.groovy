package com.enjoy.plugin;

class Jiagu {
    String userName
    String password
    String jiaguTools
    String uKey
    String apiKey

    String getuKey() {
        return uKey
    }

    void setuKey(String uKey) {
        this.uKey = uKey
    }

    String getApiKey() {
        return apiKey
    }

    void setApiKey(String apiKey) {
        this.apiKey = apiKey
    }

    String getUserName() {
        return userName
    }

    void setUserName(String userName) {
        this.userName = userName
    }

    String getPassword() {
        return password
    }

    void setPassword(String password) {
        this.password = password
    }

    String getJiaguTools() {
        return jiaguTools
    }

    void setJiaguTools(String jiaguTools) {
        this.jiaguTools = jiaguTools
    }
}
