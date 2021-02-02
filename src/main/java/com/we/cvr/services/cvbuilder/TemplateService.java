package com.we.cvr.services.cvbuilder;

import com.we.cvr.models.auth.User;
import com.we.cvr.models.cvbuilder.BasicInfo;
import com.we.cvr.models.cvbuilder.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TemplateService {
    List<Template> templates = new ArrayList<>();
    @Autowired
    TemplateRepository templateRepository;


    public List<Template> getAllTemplates(){
        templates.clear();
        templateRepository.findAll().forEach(template -> templates.add(template));
        return templates;
    }

    public Template getTemplate(Integer id) {
        return templateRepository.findById(id).orElse(null);
    }
}
