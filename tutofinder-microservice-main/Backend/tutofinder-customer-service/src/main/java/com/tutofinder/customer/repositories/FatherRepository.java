package com.tutofinder.customer.repositories;

import com.tutofinder.customer.entities.Father;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FatherRepository extends JpaRepository<Father,Long> {
}
