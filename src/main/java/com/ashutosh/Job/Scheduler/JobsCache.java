package com.ashutosh.Job.Scheduler;

import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.concurrent.PriorityBlockingQueue;

@Component
public class JobsCache {
    private final PriorityBlockingQueue<Job> queuedJobs;

    public JobsCache() {
        this.queuedJobs = new PriorityBlockingQueue<>(10, new Comparator<Job>() {
            @Override
            public int compare(Job o1, Job o2) {
                return o1.getTriggerTime().compareTo(o2.getTriggerTime());
            }
        });
        // logic to load pending jobs from DB
    }

    public void add(Job job) { this.queuedJobs.add(job); }

    public Job remove() throws InterruptedException {
        return this.queuedJobs.take();
    }
}
