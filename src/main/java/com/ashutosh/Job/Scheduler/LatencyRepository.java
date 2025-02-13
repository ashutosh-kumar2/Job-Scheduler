package com.ashutosh.Job.Scheduler;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface LatencyRepository extends JpaRepository<Latency, Long> {

    @Query("SELECT l FROM Latency l WHERE l.timestamp >= :startTime and l.timestamp <= :endTime")
    List<Latency> findLatenciesInRange(Timestamp startTime, Timestamp endTime);
}
