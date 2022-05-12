package com.phase2.homeService.controller;

import com.phase2.homeService.entities.Offer;
import com.phase2.homeService.service.implementations.OfferServiceImple;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/offer")
public class OfferController {

    private OfferServiceImple offerService;

    public OfferController(OfferServiceImple offerService) {
        this.offerService = offerService;
    }

    @PostMapping("/save")
    public ResponseEntity<Offer> save(@RequestBody Offer offer){
        return ResponseEntity.ok(offerService.save(offer));
    }
}
