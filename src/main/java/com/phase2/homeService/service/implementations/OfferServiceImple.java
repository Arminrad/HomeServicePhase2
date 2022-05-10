package com.phase2.homeService.service.implementations;

import com.phase2.homeService.entities.Offer;
import com.phase2.homeService.entities.Order;
import com.phase2.homeService.repository.OfferRepository;
import com.phase2.homeService.repository.OrderRepository;
import com.phase2.homeService.service.interfaces.OfferService;
import org.springframework.stereotype.Service;


@Service
public class OfferServiceImple implements OfferService {
    private final OfferRepository offerRepository;
    private final OrderRepository orderRepository;

    public OfferServiceImple(OfferRepository offerRepository, OrderRepository orderRepository) {
        this.offerRepository = offerRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public Offer save(Offer offer) {
        return offerRepository.save(offer);
    }

    @Override
    public Boolean saveAndUpdate(Offer offer, Order order) {
        offerRepository.save(offer);
        orderRepository.save(order);
        return true;
    }
}
