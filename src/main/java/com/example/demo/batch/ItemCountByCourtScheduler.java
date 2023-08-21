package com.example.demo.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class ItemCountByCourtScheduler {
	private final JobLauncher jobLauncher;
    private final Job itemCountByCourtJob;

    @Autowired
    public ItemCountByCourtScheduler(JobLauncher jobLauncher, Job itemCountByCourtJob) {
        this.jobLauncher = jobLauncher;
        this.itemCountByCourtJob = itemCountByCourtJob;
    }

    @Scheduled(cron = "0 */30 * * * *") // 每30分钟执行一次
    public void runItemCountByCourtJob() throws Exception {
    	 JobParameters jobParameters = new JobParametersBuilder()
                 .addLong("timestamp", System.currentTimeMillis())
                 .toJobParameters();
        jobLauncher.run(itemCountByCourtJob, jobParameters);
    }

}
