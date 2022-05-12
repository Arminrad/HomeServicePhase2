package com.phase2.homeService.controller;

import com.phase2.homeService.dto.CustomerDto;
import com.phase2.homeService.dto.OfferDto;
import com.phase2.homeService.entities.Customer;
import com.phase2.homeService.entities.Offer;
import com.phase2.homeService.service.implementations.OfferServiceImple;
import org.dozer.DozerBeanMapper;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/offer")
public class OfferController {

    private final OfferServiceImple offerService;
    private final DozerBeanMapper mapper;
    private final ModelMapper modelMapper;

    public OfferController(OfferServiceImple offerService) {
        this.offerService = offerService;
        this.mapper = new DozerBeanMapper();
        this.modelMapper = new ModelMapper();
    }

    @PostMapping("/save")
    public ResponseEntity<OfferDto> save(@RequestBody OfferDto offerDto){
        Offer offer = mapper.map(offerDto, Offer.class);
        Offer savedOffer = offerService.save(offer);
        OfferDto savedOfferDto = modelMapper.map(savedOffer, OfferDto.class);
        return ResponseEntity.ok(savedOfferDto);
    }
}
