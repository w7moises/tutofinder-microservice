package com.tutofinder.payment.repositories;
import java.util.Optional;

import com.tutofinder.payment.entities.Membership;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MembershipRepository extends JpaRepository<Membership, Long> {
    Optional<Membership> findById(Long id);

    Optional<Membership> findByTeacherId(Long teacherId);

    Optional<Membership> findByTeacherIdAndCardId(Long teacherId, Long cardId);
}