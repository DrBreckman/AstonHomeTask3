package ru.sedov.task3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.sedov.task3.entity.Review;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("""
        SELECT r
        FROM Review r
        WHERE r.mark = (SELECT MAX(t.mark) FROM Review t)
    """) //
    List<Review> findAllBestReviews();

    @Query("""
        SELECT r
        FROM Review r
        WHERE r.mark = (SELECT MIN(t.mark) FROM Review t)
    """) //
    List<Review> findAllWorstReviews();
}
