/**
 * 
 */
package com.example.demo.controller;

import java.util.Iterator;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.repository.SetsExampleRepository;
import com.example.demo.repository.StringExampleRepository;

/**
 * @author balaj
 *
 */

@RestController
@RequestMapping("/sets")
public class SetExampleController {

	@Autowired
	private SetsExampleRepository setRepository;

	@PostMapping
	public Set<String> save(@RequestBody Set<String> cities) {

		for (String city : cities) {
			System.out.println(city);
			setRepository.addMembers(city);
		}
		return cities;
	}


	 @GetMapping 
	 public Set<String> list() { 
		 return setRepository.getMembers(); 
	}
	  


}
