package com.cl.mykowel.model.model_my_kovel.model_user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class User {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("login")
    @Expose
    private String login;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("is_admin")
    @Expose
    private String is_admin;



//    public User(String login, String name, String email, String phone, String password) {
//        this.login = login;
//        this.name = name;
//        this.email = email;
//        this.phone = phone;
//        this.password = password;
////        this.is_admin = is_admin;
//    }

    public User(String login, String name, String email, String phone, String password) {
        this.login = login;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
//        this.token = token;
//        this.is_admin = is_admin;
    }
    public User(String id, String login, String name, String email, String phone, String password, String token) {
        this.id = id;
        this.login = login;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.token = token;
        this.is_admin = is_admin;
    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


    public String getIs_admin() {
        return is_admin;
    }

    public void setIs_admin(String is_admin) {
        this.is_admin = is_admin;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
