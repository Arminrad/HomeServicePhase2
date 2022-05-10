package com.phase2.homeService.service.interfaces;

import com.phase2.homeService.entities.Offer;
import com.phase2.homeService.entities.Order;

public interface OfferService {

    Offer save(Offer offer);

    Boolean saveAndUpdate(Offer offer, Order order);
}
