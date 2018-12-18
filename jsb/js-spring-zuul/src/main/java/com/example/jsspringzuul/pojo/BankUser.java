package com.example.jsspringzuul.pojo;



import com.example.jsspringzuul.uitl.DateUtil;

import java.util.Date;

//用户信息
public class BankUser {
    private Integer userid;//用户ID
    private String name;//姓名
    private String idcard;//身份证号
    private String province;//省份
    private String city;//城市
    private String imgurl;//身份证图片路径
    private Integer credit;//个人信用度
    private String jingpwd;//个人静态密码
    private Integer sex;//性别（1是男，2是女）
    private String birthday;//出生日期
    private Integer married;//结婚状态（1未婚，2已婚，3离异，4丧偶）
    private Integer education;//学历（1本科，2硕士，3博士，4大专，5高中，6初中）
    private String occupation;//职业
    private String industry;//所在行业
    private String skill;//技能职称
    private String administrative;//行政级别
    private Integer people;//供养人口
    private String work;//工作单位
    private String worktime;//开始工作时间
    private Integer webstate;//网上银行开通状态（0未开通，1已开通）
    private String reservedms;//网银预留信息
    private Integer faststate;//是否快捷支付（0否，1是）
    private String idcarddate;//身份证到期日期
//    private String bankcarddate;//办卡日期
    private String e_mail;//邮箱
    private Double tallage;//所交税额
    private Integer integral;//个人积分

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public Integer getCredit() {
        return credit;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }

    public String getJingpwd() {
        return jingpwd;
    }

    public void setJingpwd(String jingpwd) {
        this.jingpwd = jingpwd;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return DateUtil.SchangeD(birthday);
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public Integer getMarried() {
        return married;
    }

    public void setMarried(Integer married) {
        this.married = married;
    }

    public Integer getEducation() {
        return education;
    }

    public void setEducation(Integer education) {
        this.education = education;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public String getAdministrative() {
        return administrative;
    }

    public void setAdministrative(String administrative) {
        this.administrative = administrative;
    }

    public Integer getPeople() {
        return people;
    }

    public void setPeople(Integer people) {
        this.people = people;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public Date getWorktime() {
        return DateUtil.SchangeD(worktime);
    }

    public void setWorktime(String worktime) {
        this.worktime = worktime;
    }

    public Integer getWebstate() {
        return webstate;
    }

    public void setWebstate(Integer webstate) {
        this.webstate = webstate;
    }

    public String getReservedms() {
        return reservedms;
    }

    public void setReservedms(String reservedms) {
        this.reservedms = reservedms;
    }

    public Integer getFaststate() {
        return faststate;
    }

    public void setFaststate(Integer faststate) {
        this.faststate = faststate;
    }

    public String getIdcarddate() {
        return idcarddate;
    }

    public void setIdcarddate(String idcarddate) {
        this.idcarddate = idcarddate;
    }

//    public Date getBankcarddate() {
//        return DateUtil.SchangeD(bankcarddate);
//    }
//
//    public void setBankcarddate(String bankcarddate) {
//        this.bankcarddate = bankcarddate;
//    }

    public String getE_mail() {
        return e_mail;
    }

    public void setE_mail(String e_mail) {
        this.e_mail = e_mail;
    }

    public Double getTallage() {
        return tallage;
    }

    public void setTallage(Double tallage) {
        this.tallage = tallage;
    }

    public Integer getIntegral() {
        return integral;
    }

    public void setIntegral(Integer integral) {
        this.integral = integral;
    }
}
