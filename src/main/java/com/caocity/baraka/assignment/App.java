package com.caocity.baraka.assignment;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {
	
	public static void main(String[] args) {
		Executor executor = Executors.newSingleThreadExecutor();
		Collector collector=new Collector();
		executor.execute(collector);
		SpringApplication.run(App.class, args);
    }
}
