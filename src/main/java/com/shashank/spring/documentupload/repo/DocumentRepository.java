package com.shashank.spring.documentupload.repo;


import com.shashank.spring.documentupload.entity.Document;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DocumentRepository extends CrudRepository<Document,Long> {
}
