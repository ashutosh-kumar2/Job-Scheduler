package com.ashutosh.Job.Scheduler;

import java.sql.Timestamp;

public interface Job {
    void execute();

    Timestamp getTriggerTime();
}
