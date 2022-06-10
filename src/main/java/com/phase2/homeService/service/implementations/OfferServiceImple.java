package com.phase2.homeService.service.implementations;

import com.phase2.homeService.entities.Offer;
import com.phase2.homeService.entities.Order;
import com.phase2.homeService.repository.OfferRepository;
import com.phase2.homeService.repository.OrderRepository;
import com.phase2.homeService.service.interfaces.OfferService;
import org.dozer.DozerBeanMapper;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;


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

    @Override
    public List<Offer> getOrderOffers(Integer id) {
        return null;
    }

    @Override
    public Offer getById(Integer id) {
        return offerRepository.getById(id);
    }

/*    @Override
    public List<Offer> getOrderOffers(Integer id, Sort.by()) {
        return offerRepository.getOrderOffers(id);
    }*/
}
