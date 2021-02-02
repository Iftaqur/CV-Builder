package com.we.cvr.controllers.cvbuilder;

import com.we.cvr.models.cvbuilder.CV;
import com.we.cvr.services.cvbuilder.CvManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CvApiController {
    @Autowired
    private CvManagerService cvManagerService;

    @RequestMapping("/api/cv")
    public List<CV> allCV() {
        return cvManagerService.getAllCV();
    }

    @RequestMapping("/api/cv/{id}")
    public CV detailCV(@PathVariable int id) {
        return cvManagerService.getCV(id);
    }

    @RequestMapping("/api/user/{userID}/cv")
    public List<CV> usersCV(@PathVariable int userID) {
        return cvManagerService.getUsersCV(userID);
    }

    @RequestMapping(value = "/api/user/{userID}/cv", method = RequestMethod.POST)
    public void addCV(@RequestBody CV cv, @PathVariable int userID){
        cvManagerService.addCV(cv, userID);
    }

    @RequestMapping(value = "/api/user/{userID}/cv/edit/{cvID}", method = RequestMethod.PUT)
    public void updateCV(@RequestBody CV cv, @PathVariable int userID, @PathVariable int cvID){
        cvManagerService.updateCV(cv, cvID, userID);
    }

    @RequestMapping(value = "/api/user/{userID}/cv/delete/{cvID}", method = RequestMethod.DELETE)
    public void deleteCV(@PathVariable int userID, @PathVariable int cvID){
        cvManagerService.deleteCV(cvID, userID);
    }
}
