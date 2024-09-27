package beta.function.account.dto;


import org.springframework.format.annotation.DateTimeFormat;

public class AccountDTO {

    private int userCode;
    private String userId;
    private String userName;
    private String nickName;
    private String pwd;
    private String birthday;
    private String email;
    private String phone;
    private char suspension;
    private char deletion;
    private String profileimg;
    private int authorityCode;

    public AccountDTO() {
    }

    public AccountDTO(int userCode, String userId, String userName, String nickName, String pwd, String birthday, String email, String phone, char suspension, char deletion, String profileimg, int authorityCode) {
        this.userCode = userCode;
        this.userId = userId;
        this.userName = userName;
        this.nickName = nickName;
        this.pwd = pwd;
        this.birthday = birthday;
        this.email = email;
        this.phone = phone;
        this.suspension = suspension;
        this.deletion = deletion;
        this.profileimg = profileimg;
        this.authorityCode = authorityCode;
    }

    public int getUserCode() {
        return userCode;
    }

    public void setUserCode(int userCode) {
        this.userCode = userCode;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
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

    public char getSuspension() {
        return suspension;
    }

    public void setSuspension(char suspension) {
        this.suspension = suspension;
    }

    public char getDeletion() {
        return deletion;
    }

    public void setDeletion(char deletion) {
        this.deletion = deletion;
    }

    public String getProfileimg() {
        return profileimg;
    }

    public void setProfileimg(String profileimg) {
        this.profileimg = profileimg;
    }

    public int getAuthorityCode() {
        return authorityCode;
    }

    public void setAuthorityCode(int authorityCode) {
        this.authorityCode = authorityCode;
    }

    @Override
    public String toString() {
        return "AccountDTO{" +
                "userCode=" + userCode +
                ", userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", nickName='" + nickName + '\'' +
                ", pwd='" + pwd + '\'' +
                ", birthday='" + birthday + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", suspension=" + suspension +
                ", deletion=" + deletion +
                ", profileimg='" + profileimg + '\'' +
                ", authorityCode=" + authorityCode +
                '}';
    }
}
