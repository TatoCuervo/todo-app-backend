package com.tatocuervo.todoappbackend.repositories;

import com.tatocuervo.todoappbackend.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Long> {

}
