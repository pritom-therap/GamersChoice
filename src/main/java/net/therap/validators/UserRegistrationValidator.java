package net.therap.validators;


import net.therap.command.UserCmd;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by IntelliJ IDEA.
 * User: pritom
 * Date: 6/7/12
 * Time: 12:41 PM
 * To change this template use File | Settings | File Templates.
 */
public class UserRegistrationValidator implements Validator {

    public boolean supports(Class<?> aClass) {

        return UserCmd.class.equals(aClass);
    }


    public void validate(Object o, Errors errors) {
        UserCmd userCmd = (UserCmd) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "required.userName", "User Name is required.");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "required.password", "Password is required.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword", "required.confirmPassword", "Confirm the password.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "required.email", "Email address is required.");
        //ValidationUtils.rejectIfEmptyOrWhitespace(errors,"agreeRules","required.agreeRules","All registered user must agree to our rules and regulations");

        //ValidationUtils.rejectIfEmptyOrWhitespace(errors,"agreeAgeLimit","required.agreeAgeLimit","user must be at least 12 years old");

        if (!(userCmd.getPassword().equals(userCmd.getConfirmPassword()))) {
            errors.rejectValue("password", "notmatch.password");
        }

        if (userCmd.isAgreeRules() == false) {
            errors.rejectValue("agreeRules", "required.agreeRules");
        }

        if (userCmd.isAgreeAgeLimit() == false) {

            ValidationUtils.rejectIfEmptyOrWhitespace(errors,"agreeAgeLimit","required.agreeAgeLimit","User must be at least 12 years old");


        }
    }
}