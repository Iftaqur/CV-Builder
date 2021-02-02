package com.we.cvr.services.cvbuilder;

import com.we.cvr.models.auth.User;
import com.we.cvr.models.cvbuilder.AcademicInfo;
import com.we.cvr.models.cvbuilder.ProjectInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectInfoService {
    @Autowired
    ProjectInfoRepository projectInfoRepository;

    public void addProjectInfo(ProjectInfo projectInfo, User user) {
        projectInfo.setUser(user);
        projectInfoRepository.save(projectInfo);
    }

    public ProjectInfo getProjectInfo(int id) {
        return projectInfoRepository.findById(id).orElse(null);
    }

    public List<ProjectInfo> getProjectInfo(User user) {
        return projectInfoRepository.findByUser(user);
    }

}
