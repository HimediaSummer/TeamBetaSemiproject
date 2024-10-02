package beta.function.account.dto;


import beta.function.auth.userRole.UserRole;

public class AccountDTO {

    private int userCode;
    private String username;
    private String fullname;
    private String nickName;
    private String password;
    private String birthday;
    private String email;
    private String phone;
    private char suspension;
    private char deletion;
    private String profileimg;
    private int authorityCode;

    public AccountDTO() {
    }

    public AccountDTO(int userCode, String username, String fullname, String nickName, String password, String birthday, String email, String phone, char suspension, char deletion, String profileimg, int authorityCode) {
        this.userCode = userCode;
        this.username = username;
        this.fullname = fullname;
        this.nickName = nickName;
        this.password = password;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
        if (phone != null && phone.length() == 11) {
            return phone.substring(0, 3) + "-" + phone.substring(3, 7) + "-" + phone.substring(7);
        }
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
                ", username='" + username + '\'' +
                ", fullname='" + fullname + '\'' +
                ", nickName='" + nickName + '\'' +
                ", password='" + password + '\'' +
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
