/**
 * 
 */
package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.User;
import com.example.demo.repository.StringExampleRepository;
import com.example.demo.repository.UserRepository;

/**
 * @author balaj
 *
 */
@RestController
public class StringController {
	
	@RestController
	@RequestMapping("/strings")
	public class UserController {
		
		@Autowired
	    private StringExampleRepository stringRepository;

	    @PostMapping
	    public String save(@RequestParam("name") String name){
	    	stringRepository.addUserName(name);
	        return name;
	    }

	    @GetMapping
	    public String list(){
	        return stringRepository.getUserName();
	    }

	    @PutMapping
	    public String update(@RequestParam("name") String name){
	    	stringRepository.updateUserName(name);
	        return name;
	    }
	}
}