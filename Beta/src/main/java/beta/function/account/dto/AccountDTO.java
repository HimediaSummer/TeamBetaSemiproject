package beta.function.account.dto;

import beta.function.order.dto.CartDTO;

public class AccountDTO {

    private int userCode;
    private String userId;
    private String userName;
    private String nickName;
    private String pwd;
    private String birthday;
    private String email;
    private String phone;
    private String suspension;
    private String deletion;
    private String profileimg;
    private int authorityCode;
    private CartDTO cartDTO;

    public AccountDTO() {
    }

    public AccountDTO(int userCode, String userId, String userName, String nickName, String pwd, String birthday, String email, String phone, String suspension, String deletion, String profileimg, int authorityCode, CartDTO cartDTO) {
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
        this.cartDTO = cartDTO;
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

    public String getSuspension() {
        return suspension;
    }

    public void setSuspension(String suspension) {
        this.suspension = suspension;
    }

    public String getDeletion() {
        return deletion;
    }

    public void setDeletion(String deletion) {
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

    public CartDTO getCartDTO() {
        return cartDTO;
    }

    public void setCartDTO(CartDTO cartDTO) {
        this.cartDTO = cartDTO;
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
                ", suspension='" + suspension + '\'' +
                ", deletion='" + deletion + '\'' +
                ", profileimg='" + profileimg + '\'' +
                ", authorityCode=" + authorityCode +
                ", cartDTO=" + cartDTO +
                '}';
    }
}
