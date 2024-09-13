package com.app.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.app.Entity.User;

public interface UserRespository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.full_Name LIKE %:query% OR u.email LIKE %:query%")
    List<User> SearchUser(@Param("query") String query);
}
