package net.therap.command;

/**
 * Created by IntelliJ IDEA.
 * user: pritom
 * Date: 6/7/12
 * Time: 12:16 PM
 * To change this template use File | Settings | File Templates.
 */
public class UserCmd {

    String userName;
    String email;
    String password;
    String confirmPassword;
    boolean agreeRules;
    boolean agreeAgeLimit;

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAgreeRules() {
        return agreeRules;
    }

    public void setAgreeRules(boolean agreeRules) {
        this.agreeRules = agreeRules;
    }

    public boolean isAgreeAgeLimit() {
        return agreeAgeLimit;
    }

    public void setAgreeAgeLimit(boolean agreeAgeLimit) {
        this.agreeAgeLimit = agreeAgeLimit;
    }
}
