package com.ashutosh.Job.Scheduler.controller;

import com.ashutosh.Job.Scheduler.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.Map;

@RestController
@RequestMapping(value = "/schedule")
public class SchedulerController {

    @Autowired
    private EmailSender emailSender;

    @Autowired
    private Profiler profiler;

    @Autowired
    private JobsCache jobsCache;

    @RequestMapping(method = RequestMethod.POST, path = "/email")
    public String scheduleEmail(@RequestBody Map<String, String> details){
        System.out.println("received at /schedule/email");
        EmailDetails emailDetails = new EmailDetails();
        emailDetails.setDestination(details.get("destination"));
        emailDetails.setSubject(details.get("subject"));
        emailDetails.setText(details.get("text"));
        emailDetails.setTriggerTime(Timestamp.valueOf(details.get("triggerTime")));
        EmailingJob emailingJob = new EmailingJob(emailSender, emailDetails);
        this.jobsCache.add(emailingJob);
        return "Job added successfully";
    }

    @RequestMapping(method = RequestMethod.POST, path = "/profile")
    public String scheduleProfiling(@RequestBody Map<String, String> details){
        System.out.println("received at /schedule/profile");
        ProfilingDetails profilingDetails = new ProfilingDetails();
        profilingDetails.setStartTime(Timestamp.valueOf(details.get("startTime")));
        profilingDetails.setEndTime(Timestamp.valueOf(details.get("endTime")));
        profilingDetails.setTriggerTime(Timestamp.valueOf(details.get("triggerTime")));
        ProfilingJob profilingJob = new ProfilingJob(profilingDetails, profiler);
        this.jobsCache.add(profilingJob);
        return "Job added successfully";
    }
}
