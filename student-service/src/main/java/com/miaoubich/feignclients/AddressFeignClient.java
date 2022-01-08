package com.miaoubich.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.miaoubich.response.AddressResponse;

//To call address service via url
//value, is this current feign client name which we called it address-feign
@FeignClient(url = "${address.service.url}", value = "address-feign")
public interface AddressFeignClient {

	@GetMapping("/{addressId}")
	public AddressResponse printSingleAddress(@PathVariable Long addressId);
}
