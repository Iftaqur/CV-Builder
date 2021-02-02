package com.we.cvr.services.cvbuilder;

import com.we.cvr.models.auth.User;
import com.we.cvr.models.cvbuilder.BasicInfo;
import com.we.cvr.models.cvbuilder.ProfileInfo;
import org.springframework.data.repository.CrudRepository;

public interface ProfileInfoRepository extends CrudRepository<ProfileInfo, Integer> {
    public ProfileInfo findFirstByUser(User user);
}
