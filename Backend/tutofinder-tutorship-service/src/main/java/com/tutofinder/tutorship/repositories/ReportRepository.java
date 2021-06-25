package com.tutofinder.tutorship.repositories;

import java.util.Optional;

import com.tutofinder.tutorship.entities.Report;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepository extends JpaRepository<Report,Long> {
     Optional<Report> findById(Long id);
}