package net.therap.validators;

import net.therap.command.ProcessRequestCmd;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by IntelliJ IDEA.
 * User: pritom
 * Date: 6/17/12
 * Time: 3:46 PM
 * To change this template use File | Settings | File Templates.
 */
public class TrackUserValidator implements Validator {

    public boolean supports(Class<?> aClass) {

        return ProcessRequestCmd.class.equals(aClass);

    }

    public void validate(Object o, Errors errors) {
        ProcessRequestCmd processRequestCmd = (ProcessRequestCmd) o;

        if(processRequestCmd.getProcessedRequestingUsers().length==0){
			errors.rejectValue("processedRequestingUsers","required.processedRequestingUsers");
		}
    }
}
