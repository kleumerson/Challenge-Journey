package com.journey.repository.testimonial;

import com.journey.model.testimonial.Testimonial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TestimonialRepository extends JpaRepository<Testimonial, Integer> {
    @Query(value = "select t from Testimonial t where t.id=:id")
    Testimonial findByRegister(int id);


}
