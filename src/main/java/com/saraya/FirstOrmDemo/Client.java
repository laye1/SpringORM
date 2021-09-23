package com.saraya.FirstOrmDemo;
import java.util.Scanner;
import org.apache.log4j.Logger;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.AbstractApplicationContext;

import com.saraya.FirstOrmDemo.DTO.CustomerDTO;
import com.saraya.FirstOrmDemo.service.CustomerService;

@SpringBootApplication
public class Client implements CommandLineRunner {
    static Logger logger = Logger.getLogger(Client.class);
	@Autowired
	AbstractApplicationContext context;
	public static void main(String[] args) {
		SpringApplication.run(Client.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
		CustomerService customerService = (CustomerService)context.getBean("customerService");
		
		CustomerDTO customer1= new CustomerDTO(9009009009L, "Debashis", 27, 'M', "BBSR", 1);
		CustomerDTO customer2= new CustomerDTO(9009009010L, "Robert", 25, 'M', "PUNE", 2);
		CustomerDTO customer3= new CustomerDTO(9009009011L, "Lucy", 48, 'F', "MUMBAI", 3);
		
		customerService.insert(customer1);
		customerService.insert(customer2);
		customerService.insert(customer3);
		logger.info("Records are successfully added..");
		
		System.out.println("Enter the phone Number of the Customer which has to be deleted.");
		Scanner scanner = new Scanner(System.in);
		Long phoneNo = scanner.nextLong();
		// Invoking Service layer method to remove Customer details from
		// Customer table
		int result = customerService.remove(phoneNo);
		if (result == 1) {
			logger.info("Success : Record deleted successfully ");
		} else {
			logger.info("Error : No record found for the given phone number ");
		}
        logger.info("Viewing All Customer Details");
		ArrayList<CustomerDTO> cList = (ArrayList<CustomerDTO>)customerService.getAll();
		for (CustomerDTO customer : cList) {
			logger.info(customer);
		}
		
		logger.info("Display completed");
		logger.info("");
		logger.info("Let's update a Customer with new Address details");
		System.out.println("Enter the phone number of the Customer to be updated:");
		Scanner sc = new Scanner(System.in);
		Long phoneNo1 =  sc.nextLong();
		System.out.println("Enter new Address");
		String newAddress =  sc.next();
		customerService.update(phoneNo1, newAddress);
		logger.info("Customer's address updated successfully!");
		scanner.close();
		context.close();
	}
}
