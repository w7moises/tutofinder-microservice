package com.tutofinder.customer.repositories;

import com.tutofinder.customer.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;



@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);

    @Modifying
    @Query(value="INSERT INTO users_authorities(user_id,role_id) VALUES(?1,?2)",nativeQuery =true)
    void register(Long userId, Long roleId );
}
