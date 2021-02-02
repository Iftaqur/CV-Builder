package com.we.cvr.services.cvbuilder;

import com.we.cvr.models.cvbuilder.CV;
import com.we.cvr.models.cvbuilder.Template;
import org.springframework.data.repository.CrudRepository;

public interface TemplateRepository extends CrudRepository<Template, Integer> {

}
