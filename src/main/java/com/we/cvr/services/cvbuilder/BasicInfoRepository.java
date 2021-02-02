package com.we.cvr.services.cvbuilder;

import com.we.cvr.models.auth.User;
import com.we.cvr.models.cvbuilder.BasicInfo;
import com.we.cvr.models.cvbuilder.CV;
import org.springframework.data.repository.CrudRepository;

public interface BasicInfoRepository extends CrudRepository<BasicInfo, Integer> {
    public BasicInfo findFirstByUser(User user);
}
