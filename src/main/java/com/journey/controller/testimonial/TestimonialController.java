package com.journey.controller.testimonial;

import com.journey.dto.testimonial.TestimonialDto;
import com.journey.service.testimonial.TestimonialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/testimonials")
public class TestimonialController {
    @Autowired
    private TestimonialService testimonialService;
    @PostMapping
    public ResponseEntity<TestimonialDto> createTestimonial(@RequestBody TestimonialDto testimonialDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(testimonialService.createTestimonial(testimonialDto));

    }
    @GetMapping
    public List<TestimonialDto> getTestimonial() {
        return testimonialService
                .getTestimonial();
    }

    @PutMapping("{id}")
    public ResponseEntity<TestimonialDto> updateTestimonial(@RequestBody TestimonialDto testimonialDto,@PathVariable("id") int id) {
        return ResponseEntity
                .ok(testimonialService.updateTestimonial(testimonialDto, id));
    }

    @DeleteMapping("{id}")
    public void deleteTestimonial(@PathVariable("id") int id){
        testimonialService
                .deleteTestimonial(id);
    }
}
