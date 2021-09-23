package com.saraya.FirstOrmDemo.service;
import java.util.List;

import com.saraya.FirstOrmDemo.DTO.CustomerDTO;

public interface CustomerService {
	// Method to access the repository layer method to insert Customer record
	public void insert(CustomerDTO customer);
	// Method to access the repository layer method to delete Customer record
	public int remove(Long phoneNo);
    // Method to get all the Customer record from the db
    public List<CustomerDTO> getAll();
    // Method to update a Customer record from the db
    public void update(Long phoneNo, String address);
}
