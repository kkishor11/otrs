/**
 * 
 */
package com.otrs.restaurant.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.otrs.restaurant.model.Pricing;
import com.otrs.restaurant.repository.PricingRepository;
import com.otrs.restaurant.service.PricingService;
import com.otrs.restaurant.utils.SaveStatus;

/**
 * @author Kundan
 *
 */

@Service("pricingService")
public class PricingServiceImpl implements PricingService {
	
	@Autowired
	PricingRepository pricingRepository;
	
	private Logger logger = Logger.getLogger(PricingServiceImpl.class);

	@Override
	public String savePricing(Pricing pricing) {
		try {
			pricingRepository.save(pricing);
		} catch(Exception e) {
			if (e instanceof DataIntegrityViolationException) {
				logger.error(e.getMessage());
				return SaveStatus.DUPLICATE.statusText();
			}
			return SaveStatus.FAILED.statusText();
		}
		return SaveStatus.SUCCESS.statusText();
	}

	@Override
	public List<Pricing> getPricing(Integer restaurantId) {
		return pricingRepository.getPricing(restaurantId);
	}

}
