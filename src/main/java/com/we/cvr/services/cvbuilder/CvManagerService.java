package com.we.cvr.services.cvbuilder;


import com.we.cvr.models.auth.User;
import com.we.cvr.models.cvbuilder.CV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CvManagerService {

    @Autowired
    private CvRepository cvRepository;

    private List<CV> allCV = new ArrayList<>(
            /*Arrays.asList(
                    new CV(1, new User(1, "Mahmudul Hasan", "m.hasan@gmail.com", "pass1234"), "CV developer"),
                    new CV(2, new User(1, "Mahmudul Hasan", "m.hasan@gmail.com", "pass1234"), "CV QA"),
                    new CV(3, new User(2, "Mayeesha Mahzabin", "mayeesha@gmail.com", "pass1234"), "CV Team Lead"),
                    new CV(4, new User(3, "Alif B Ekram", "alif@gmail.com", "pass1234"), "Researcher"),
                    new CV(5, new User(4, "Rifah Alvy", "ra@gmail.com", "pass1234"), "Documentation team lead")
            )*/
    );

    public List<CV> getAllCV() {
        allCV.clear();
        cvRepository.findAll().forEach(cv -> allCV.add(cv));
        return allCV;
    }

    public CV getCV(int id) {
        return cvRepository.findById(id).get();
    }

    public List<CV> getUsersCV(int userID) {
        return getAllCV().stream().filter(cv -> (cv.getUser().getUserID() == userID)).collect(Collectors.toList());
    }

    public void addCV(CV cv, int userID) {
        cv.setUser(new User(userID));
        cvRepository.save(cv);
    }

    public void updateCV(CV cv, int cvID, int userID) {
        CV found = cvRepository.findById(cvID).get();
        if (found.getUser().getUserID() == userID) {
            cv.setCvID(cvID);
            cv.setUser(new User(userID));
            cvRepository.save(cv);
        }
    }

    public void deleteCV(int cvID, int userID) {
        CV found = cvRepository.findById(cvID).get();
        if (found.getUser().getUserID() == userID)
            cvRepository.deleteById(cvID);
    }

}
