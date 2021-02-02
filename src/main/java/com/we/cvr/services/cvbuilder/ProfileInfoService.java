package com.we.cvr.services.cvbuilder;

import com.we.cvr.models.auth.User;
import com.we.cvr.models.cvbuilder.BasicInfo;
import com.we.cvr.models.cvbuilder.ProfileInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileInfoService {
    @Autowired
    ProfileInfoRepository profileInfoRepository;

    public void addProfileInfo(ProfileInfo profileInfo, User user) {
        profileInfo.setUser(user);
        profileInfoRepository.save(profileInfo);
    }

    public ProfileInfo getProfileInfo(int id) {
        return profileInfoRepository.findById(id).orElse(null);
    }

    public ProfileInfo getProfileInfo(User user) {
        return profileInfoRepository.findFirstByUser(user);
    }

}
