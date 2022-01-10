package com.miaoubich.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.miaoubich.response.AddressResponse;

//To call address service via url
//value: is this current feign client name which we called it address-feign (call it whatever you like)
//Before registering the Address microservice with eureka we use its url
//@FeignClient(url = "${address.service.url}", value = "address-feign", path="/api/address") 
//After registering Address microservice with eurka we use its name as a value
@FeignClient(value = "address-service", path="/api/address") 
public interface AddressFeignClient {

	@GetMapping("/{addressId}")
	public AddressResponse printSingleAddress(@PathVariable Long addressId);
	
}
