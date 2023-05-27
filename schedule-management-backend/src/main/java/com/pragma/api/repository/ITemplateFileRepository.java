package com.pragma.api.repository;


import com.pragma.api.model.TemplateFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITemplateFileRepository extends JpaRepository<TemplateFile, Integer> {

}
