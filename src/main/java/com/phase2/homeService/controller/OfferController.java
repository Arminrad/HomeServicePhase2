package com.phase2.homeService.controller;
import com.phase2.homeService.dto.OfferDto;
import com.phase2.homeService.entities.Offer;
import com.phase2.homeService.entities.Order;
import com.phase2.homeService.entities.Professional;
import com.phase2.homeService.service.implementations.OfferServiceImple;
import com.phase2.homeService.service.implementations.OrderServiceImple;
import com.phase2.homeService.service.implementations.ProfessionalServiceImple;
import org.dozer.DozerBeanMapper;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/offer")
public class OfferController {

    private final OfferServiceImple offerService;
    private final DozerBeanMapper mapper;
    private final ModelMapper modelMapper;
    private final ProfessionalServiceImple professionalService;
    private final OrderServiceImple orderService;

    public OfferController(OfferServiceImple offerService,
                           ProfessionalServiceImple professionalService, OrderServiceImple orderService) {
        this.offerService = offerService;
        this.professionalService = professionalService;
        this.orderService = orderService;
        this.mapper = new DozerBeanMapper();
        this.modelMapper = new ModelMapper();
    }

    @PostMapping("/save")
    public ResponseEntity<OfferDto> save(@Valid @RequestBody OfferDto offerDto) {
        Offer offer = mapper.map(offerDto, Offer.class);
        Professional professional = professionalService.getById(offerDto.getProfessional_id());
        offer.setProfessional(professional);
        Order order = orderService.getById(offerDto.getOrder_id());
        offer.setOrder(order);
        Offer savedOffer = offerService.save(offer);
        OfferDto savedOfferDto = modelMapper.map(savedOffer, OfferDto.class);
        return ResponseEntity.ok(savedOfferDto);
    }
}

/*    @GetMapping("/getOrderOffers/{offerId}")
    public ResponseEntity<List<OfferDto>> getOrderOffers(@PathVariable("offerId") Long offerId) {
        Order order = orderService.getById(offerDto.getOrder_id());
        List<Offer> offerList = offerService.getOrderOffers(order.getId());
        List<OfferDto> savedOffersDto = new ArrayList<>();
        for (Offer o:offerList) {
            OfferDto savedOfferDto = modelMapper.map(o, OfferDto.class);
            savedOffersDto.add(savedOfferDto);
        }
        return ResponseEntity.ok(savedOffersDto);
    }
}*/
