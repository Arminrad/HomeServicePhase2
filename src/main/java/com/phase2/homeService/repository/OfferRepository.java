package com.phase2.homeService.repository;

import com.phase2.homeService.entities.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OfferRepository extends JpaRepository<Offer, Integer> {

/*    @Query("FROM Offer o, Comment c JOIN AVG(c.rating) WHERE o.order.id = :orderId ORDER BY o.proposedOfferPrice ASC  ")
    List<Offer> getOrderOffers(@Param("orderId") Integer orderId);*/

    /*@Query("SELECT AVG (c.rating) FROM Comment c GROUP BY c.professional.id HAVING :id")
    Double getProfessionalRating(@Param("id") Integer id);*/
}
