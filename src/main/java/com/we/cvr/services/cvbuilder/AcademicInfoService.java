package com.we.cvr.services.cvbuilder;

import com.we.cvr.models.auth.User;
import com.we.cvr.models.cvbuilder.AcademicInfo;
import com.we.cvr.models.cvbuilder.BasicInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AcademicInfoService {
    @Autowired
    AcademicInfoRepository academicInfoRepository;

    public void addAcademicInfo(AcademicInfo academicInfo, User user) {
        academicInfo.setUser(user);
        academicInfoRepository.save(academicInfo);
    }

    public void deleteAcademicInfo(int id, User user) {
        AcademicInfo academicInfo = academicInfoRepository.findById(id).orElse(null);
        if(academicInfo==null || academicInfo.getUser().getUserID()!=user.getUserID())
            return;
        academicInfoRepository.delete(academicInfo);
    }

    public AcademicInfo getAcademicInfo(int id) {
        return academicInfoRepository.findById(id).orElse(null);
    }

    public List<AcademicInfo> getAcademicInfo(User user) {
        return academicInfoRepository.findByUser(user);
    }

}
