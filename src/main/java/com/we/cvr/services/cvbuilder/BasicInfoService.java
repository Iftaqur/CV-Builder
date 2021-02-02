package com.we.cvr.services.cvbuilder;

import com.we.cvr.models.auth.User;
import com.we.cvr.models.cvbuilder.BasicInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BasicInfoService {
    @Autowired
    BasicInfoRepository basicInfoRepository;

    public void addBasicInfo(BasicInfo basicInfo, User user){
        basicInfo.setUser(user);
        basicInfoRepository.save(basicInfo);
    }

    public BasicInfo getBasicInfo(int id){
        return basicInfoRepository.findById(id).orElse(null);
    }

    public BasicInfo getBasicInfo(User user){
        return basicInfoRepository.findFirstByUser(user);
    }

}
