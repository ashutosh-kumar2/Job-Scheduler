package com.ashutosh.Job.Scheduler;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

public class ProfilingJob implements Job{

    private static final String logFilePath = "D:\\Studies\\JavaSpringBoot\\Job-Scheduler\\src\\main\\java\\com\\ashutosh\\Job\\Scheduler\\logs.txt";

    private final ProfilingDetails profilingDetails;

    private final Profiler profiler;

    public ProfilingJob(ProfilingDetails profilingDetails, Profiler profiler) {
        this.profilingDetails = profilingDetails;
        this.profiler = profiler;
    }

    @Override
    public void execute() {
        List<Latency> latencies = this.profiler.profile(profilingDetails.getStartTime(), profilingDetails.getEndTime());
        FileWriter fileWriter = null;
        try{
            fileWriter = new FileWriter(logFilePath);
            for(Latency l : latencies){
                fileWriter.write(new ObjectMapper().writeValueAsString(l) + "\n");
                fileWriter.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Timestamp getTriggerTime() {
        return profilingDetails.getTriggerTime();
    }
}
