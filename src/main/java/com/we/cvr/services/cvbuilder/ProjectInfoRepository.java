package com.we.cvr.services.cvbuilder;

import com.we.cvr.models.auth.User;
import com.we.cvr.models.cvbuilder.AcademicInfo;
import com.we.cvr.models.cvbuilder.ProjectInfo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProjectInfoRepository extends CrudRepository<ProjectInfo, Integer> {
    public List<ProjectInfo> findByUser(User user);
}
