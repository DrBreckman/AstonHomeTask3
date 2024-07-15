package ru.sedov.task3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.sedov.task3.entity.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("""
        SELECT u
        FROM User u
        JOIN u.reviewSet r
        GROUP BY u.id
        HAVING AVG(r.mark) >= 3
    """)
    List<User> findAllWithGoodAverageMarkReview();
}
