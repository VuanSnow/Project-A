package com.myproject.projecta.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface MessageRepository extends CrudRepository<Message, Long> {
    Message findByMessage(String message);
}
