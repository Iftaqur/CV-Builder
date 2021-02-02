package com.we.cvr.services.cvbuilder;

import com.we.cvr.models.auth.User;
import com.we.cvr.models.cvbuilder.AcademicInfo;
import com.we.cvr.models.cvbuilder.CareerInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CareerInfoService {
    @Autowired
    CareerInfoRepository careerInfoRepository;

    public void addCareerInfo(CareerInfo careerInfo, User user) {
        careerInfo.setUser(user);
        careerInfoRepository.save(careerInfo);
    }

    public void deleteCareerInfo(int id, User user) {
        CareerInfo careerInfo = careerInfoRepository.findById(id).orElse(null);
        if(careerInfo==null || careerInfo.getUser().getUserID()!=user.getUserID())
            return;
        careerInfoRepository.delete(careerInfo);
    }

    public CareerInfo getCareerInfo(int id) {
        return careerInfoRepository.findById(id).orElse(null);
    }

    public List<CareerInfo> getCareerInfo(User user) {
        return careerInfoRepository.findByUser(user);
    }

}
