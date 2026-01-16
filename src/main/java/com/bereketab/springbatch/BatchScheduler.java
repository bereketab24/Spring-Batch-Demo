package com.bereketab.springbatch;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.job.Job;
import org.springframework.batch.core.job.parameters.JobParameters;
import org.springframework.batch.core.job.parameters.JobParametersBuilder;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;

@Configuration
@EnableScheduling
@RequiredArgsConstructor
public class BatchScheduler {

    private final JobOperator jobOperator;
    private final Job importCustomerJob;

    @Scheduled(cron = "0 0 2 * * *") //run the job at 02:00 am every day
    public void runJob(){
        try{
            JobParameters jobParameters = new JobParametersBuilder()
                    .addDate("launch date", new Date())
                    .toJobParameters();
            jobOperator.start(importCustomerJob,jobParameters);
        }catch (Exception e){
            e.printStackTrace();
        }

    }


}
