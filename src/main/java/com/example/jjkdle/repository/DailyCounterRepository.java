package com.example.jjkdle.repository;

import com.example.jjkdle.model.DailyCounter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DailyCounterRepository extends JpaRepository<DailyCounter,Long> {
}
