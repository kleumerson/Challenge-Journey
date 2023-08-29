package com.journey.controller.testimonial;


import com.journey.dto.testimonial.TestimonialDto;
import com.journey.repository.testimonial.TestimonialRepository;
import com.journey.service.testimonial.TestimonialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/testimonials-home")
public class TestimonialHomeController {
    @Autowired
    private TestimonialRepository testimonialRepository;
    @Autowired
    private TestimonialService testimonialService;
    @GetMapping
    public List<TestimonialDto> inicialTestimonial(){
        return testimonialService.inicialTestimonial();
    }
}
