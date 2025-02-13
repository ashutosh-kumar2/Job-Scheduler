package com.ashutosh.Job.Scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Component
public class Profiler {

    @Autowired
    private LatencyRepository latencyRepository;

    public List<Latency> profile(Timestamp startTime, Timestamp endTime){
        List<Latency> latencies = latencyRepository.findLatenciesInRange(startTime, endTime);
        Collections.sort(latencies, new Comparator<Latency>() {
            @Override
            public int compare(Latency o1, Latency o2) {
                return (int)o1.getLatencyInMillis() - (int)o2.getLatencyInMillis();
            }
        });
        return latencies;
    }
}
