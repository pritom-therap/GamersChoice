package net.therap.validators;

import net.therap.command.GameCmd;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by IntelliJ IDEA.
 * User: pritom
 * Date: 6/18/12
 * Time: 10:11 AM
 * To change this template use File | Settings | File Templates.
 */
public class GameAdditionValidator implements Validator {

    public boolean supports(Class<?> aClass) {
        return GameCmd.class.equals(aClass);
    }

    public void validate(Object o, Errors errors) {
        GameCmd gameCmd = (GameCmd) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"gameName","required.gameName","A game name is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"developer","required.developer","A developer is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"synopsis","required.synopsis","Synopsis must not be empty");

        if(gameCmd.getGenre().length==0) {

            errors.rejectValue("genre","required.genre");

        }

        if(gameCmd.getFile()==null || gameCmd.getFile().getSize() == 0){

            errors.rejectValue("file","required.file");
        }

        if(gameCmd.getSynopsis().length()>1000) {
            errors.rejectValue("synopsis","max.synopsisSize");
        }


    }
}
