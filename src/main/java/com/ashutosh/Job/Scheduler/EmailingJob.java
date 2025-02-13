package com.ashutosh.Job.Scheduler;

import java.sql.Timestamp;

public class EmailingJob implements Job{
    private final EmailSender emailSender;

    private final EmailDetails emailDetails;

    public EmailingJob(EmailSender emailSender, EmailDetails emailDetails) {
        this.emailSender = emailSender;
        this.emailDetails = emailDetails;
    }

    @Override
    public void execute() {
        this.emailSender.send(emailDetails.getDestination(), emailDetails.getSubject(), emailDetails.getText());
    }

    @Override
    public Timestamp getTriggerTime() {
        return emailDetails.getTriggerTime();
    }
}
