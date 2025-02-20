package com.ashutosh.Job.Scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class Worker /*implements Runnable*/{

    @Autowired
    private JobsCache jobsCache;

    @Scheduled(fixedRate = 5000)
    public void work() throws InterruptedException {
        System.out.println("worker running...");
        Job job = this.jobsCache.remove();
        if(job.getTriggerTime().compareTo(new Timestamp(System.currentTimeMillis())) < 0){
            job.execute();
            System.out.println("executed the job");
        } else {
            this.jobsCache.add(job);
            System.out.println("added the job back");
        }
    }

    /*@Override
    public void run() {
        while(true){
            try{
                Thread.sleep(5000);
                work();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }*/
}
