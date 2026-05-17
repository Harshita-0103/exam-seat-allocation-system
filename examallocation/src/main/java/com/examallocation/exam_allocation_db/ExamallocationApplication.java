package com.examallocation.exam_allocation_db;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.examallocation.exam_allocation_db")
public class ExamallocationApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExamallocationApplication.class, args);
	}
}