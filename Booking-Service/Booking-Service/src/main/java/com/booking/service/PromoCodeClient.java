package com.booking.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.booking.dto.PromoCodeDto;

@FeignClient(name = "PROJECT-FOR-PROMOCODE")
public interface PromoCodeClient {

	@GetMapping("/promocode/{promoCode}")
	public PromoCodeDto getPromoCode(@PathVariable String promoCode);

}
