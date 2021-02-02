package com.we.cvr.services.cvbuilder;

import com.we.cvr.models.auth.User;
import com.we.cvr.models.cvbuilder.AcademicInfo;
import com.we.cvr.models.cvbuilder.CareerInfo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CareerInfoRepository extends CrudRepository<CareerInfo, Integer> {
    public List<CareerInfo> findByUser(User user);
}
