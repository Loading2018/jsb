package com.example.js_deposit_provider.entity;

public class Information_card {
    private Integer id =0;
    private String banknumber;
    private String banknumber_pwd;
    private String username;
    private String verification;
    private String e_state;
    private String login_pwd;
    private String telephone;
    private String site;
    private String banktype;
    private String currency;
    private double balance;
    private String  handling_time;
    private String channel;
    private Integer state;
    private Integer fk_id;

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public Integer getFk_id() {
        return fk_id;
    }

    public void setFk_id(Integer fk_id) {
        this.fk_id = fk_id;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getBanktype() {
        return banktype;
    }

    public void setBanktype(String banktype) {
        this.banktype = banktype;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getHandling_time() {
        return handling_time;
    }

    public void setHandling_time(String handling_time) {
        this.handling_time = handling_time;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    private Integer person_id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBanknumber() {
        return banknumber;
    }

    public void setBanknumber(String banknumber) {
        this.banknumber = banknumber;
    }

    public String getBanknumber_pwd() {
        return banknumber_pwd;
    }

    public void setBanknumber_pwd(String banknumber_pwd) {
        this.banknumber_pwd = banknumber_pwd;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getVerification() {
        return verification;
    }

    public void setVerification(String verification) {
        this.verification = verification;
    }

    public String getE_state() {
        return e_state;
    }

    public void setE_state(String e_state) {
        this.e_state = e_state;
    }

    public String getLogin_pwd() {
        return login_pwd;
    }

    public void setLogin_pwd(String login_pwd) {
        this.login_pwd = login_pwd;
    }

    public Integer getPerson_id() {
        return person_id;
    }

    public void setPerson_id(Integer person_id) {
        this.person_id = person_id;
    }
}
