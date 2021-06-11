package com.tutofinder.tutorship.repositories;

import com.tutofinder.tutorship.entities.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface ReportRepository extends JpaRepository<Report,Long> {
     Optional<Report> findById(Long id);
}