package com.tutofinder.customer.repositories;

import com.tutofinder.customer.entities.Father;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FatherRepository extends JpaRepository<Father,Long> {
    @Modifying
    @Query(value="INSERT INTO favorite(father_id,teacher_id) VALUES(?1,?2)",nativeQuery =true)
    void registerFavorite(Long fatherId, Long teacherId);

    @Modifying
    @Query(value = "DELETE FROM favorite WHERE father_id = ?1 and teacher_id = ?1",nativeQuery = true)
    void deleteFavorite(Long fatherId,Long teacherId);

    Optional<Father> findByEmail(String email);
}
