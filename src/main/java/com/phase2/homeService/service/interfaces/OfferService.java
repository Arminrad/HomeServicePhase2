package com.phase2.homeService.service.interfaces;

import com.phase2.homeService.entities.Offer;
import com.phase2.homeService.entities.Order;

import java.util.List;

public interface OfferService {

    Offer save(Offer offer);

    Boolean saveAndUpdate(Offer offer, Order order);

    List<Offer> getOrderOffers(Integer id);

    Offer getById(Integer id);
}
