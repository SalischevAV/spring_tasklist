package com.task_app.task_app;

import com.task_app.task_app.Config.EnvConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class TaskAppApplication {

	public static void main(String[] args) {
		EnvConfig.loadEnv();
		SpringApplication.run(TaskAppApplication.class, args);
	}

}
