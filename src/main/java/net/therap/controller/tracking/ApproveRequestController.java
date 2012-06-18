package net.therap.controller.tracking;

import net.therap.command.ProcessRequestCmd;
import net.therap.domain.User;
import net.therap.service.tracking.TrackUserService;
import org.apache.log4j.Logger;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * user: tahmid
 * Date: 6/13/12
 * Time: 5:25 PM
 * To change this template use File | Settings | File Templates.
 */
public class ApproveRequestController extends SimpleFormController {

    protected final Logger logger = Logger.getLogger(this.getClass());

    TrackUserService trackUserService;

    public TrackUserService getTrackUserService() {
        return trackUserService;
    }

    public void setTrackUserService(TrackUserService trackUserService) {
        this.trackUserService = trackUserService;
    }


    @Override
    protected Object formBackingObject(HttpServletRequest request) throws Exception {
        ProcessRequestCmd processRequestCmd = new ProcessRequestCmd();

        return processRequestCmd;
    }

    protected Map referenceData(HttpServletRequest request) throws Exception {
        Map referenceData = new HashMap();

        HttpSession session = request.getSession();

        User user = (User) session.getAttribute("User");

        List<User> requestingUsers = trackUserService.getRequestingUsers(user);

        referenceData.put("requestingUsers", requestingUsers);


        return referenceData;
    }

    @Override
    protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {
        ProcessRequestCmd processRequestCmd = (ProcessRequestCmd) command;

        HttpSession session = request.getSession();

        User user = (User) session.getAttribute("User");

        logger.info(processRequestCmd.getProcessedRequestingUsers());

        for (String userId : processRequestCmd.getProcessedRequestingUsers()) {
            logger.info("Approved: " + userId);
        }

        String isApprove = ServletRequestUtils.getStringParameter(request, "approve", null);
        String isReject = ServletRequestUtils.getStringParameter(request, "reject", null);

        if (isApprove != null) {
            trackUserService.approveUsers(processRequestCmd.getProcessedRequestingUsers(), user);
        }

        if (isReject != null) {
            trackUserService.rejectUsers(processRequestCmd.getProcessedRequestingUsers(), user);
        }

        return new ModelAndView("Tracking/TrackRequests");
    }


}
