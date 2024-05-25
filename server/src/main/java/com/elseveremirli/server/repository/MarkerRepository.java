package com.elseveremirli.server.repository;

import com.elseveremirli.server.entities.Marker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MarkerRepository extends JpaRepository<Marker,Integer> {
    Optional<Marker> findByUsername(String username);
}
