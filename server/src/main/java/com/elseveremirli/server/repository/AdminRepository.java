package com.elseveremirli.server.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.elseveremirli.server.entities.Admin;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer>{
    Optional<Admin> findByUsername(String username);
}
