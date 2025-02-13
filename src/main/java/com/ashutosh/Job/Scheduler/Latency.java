package com.ashutosh.Job.Scheduler;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.sql.Timestamp;

@Entity
@Table(name = "latencies")
public class Latency {

    @Id
    @GeneratedValue
    private Long id;

    private Timestamp timestamp;

    private long latencyInMillis;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public long getLatencyInMillis() {
        return latencyInMillis;
    }

    public void setLatencyInMillis(long latencyInMillis) {
        this.latencyInMillis = latencyInMillis;
    }
}
