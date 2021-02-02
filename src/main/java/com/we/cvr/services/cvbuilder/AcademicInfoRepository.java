package com.we.cvr.services.cvbuilder;

import com.we.cvr.models.auth.User;
import com.we.cvr.models.cvbuilder.AcademicInfo;
import com.we.cvr.models.cvbuilder.BasicInfo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AcademicInfoRepository extends CrudRepository<AcademicInfo, Integer> {
    public List<AcademicInfo> findByUser(User user);
}
