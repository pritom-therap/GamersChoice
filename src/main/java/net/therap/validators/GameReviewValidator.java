package net.therap.validators;

import net.therap.command.ReviewCmd;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by IntelliJ IDEA.
 * User: pritom
 * Date: 6/18/12
 * Time: 11:20 AM
 * To change this template use File | Settings | File Templates.
 */
public class GameReviewValidator implements Validator{

    public boolean supports(Class<?> aClass) {
        return ReviewCmd.class.equals(aClass);
    }

    public void validate(Object o, Errors errors) {

        ReviewCmd reviewCmd = (ReviewCmd) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"pros","required.pros","Give pros");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"cons","required.cons","Give cons");

        if(reviewCmd.getGameLength()<=0.0){
            errors.rejectValue("gameLength","required.length");
        }

        if(reviewCmd.getGameLength()>100){
            errors.rejectValue("gameLength","max.gameLength");
        }

        if(reviewCmd.getRatingPresentation()==-1.0){
			errors.rejectValue("ratingPresentation", "required.ratingPresentation");
		}
        if(reviewCmd.getRatingGraphics()==-1.0){
			errors.rejectValue("ratingGraphics", "required.ratingGraphics");
		}
        if(reviewCmd.getRatingGamePlay()==-1.0){
			errors.rejectValue("ratingGamePlay", "required.ratingGamePlay");
		}
        if(reviewCmd.getRatingSound()==-1.0){
			errors.rejectValue("ratingSound", "required.ratingSound");
		}
        if(reviewCmd.getRatingLongevity()==-1.0){
			errors.rejectValue("ratingLongevity", "required.ratingLongevity");
		}

        if(reviewCmd.getDifficulty()==-1){
			errors.rejectValue("difficulty", "required.difficulty");
		}




    }
}
