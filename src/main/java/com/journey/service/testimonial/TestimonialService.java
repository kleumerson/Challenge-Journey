package com.journey.service.testimonial;

import com.journey.dto.testimonial.TestimonialDto;
import com.journey.exceptions.testimonial.TestimonialException;
import com.journey.model.testimonial.Testimonial;
import com.journey.repository.testimonial.TestimonialRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TestimonialService {
    private static final int RANGER_REG = 3;
    List<Integer> lstRnd = new ArrayList<>();
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private TestimonialRepository testimonialRepository;

    public TestimonialDto createTestimonial(TestimonialDto testimonialDto) {
        Testimonial testimonial = modelMapper
                .map(testimonialDto, Testimonial.class);
        testimonialRepository.save(testimonial);

        return modelMapper
                .map(testimonial, TestimonialDto.class);
    }

    public List<TestimonialDto> getTestimonial() {
        return testimonialRepository.findAll()
                .stream()
                .map(m -> modelMapper.map(m, TestimonialDto.class))
                .collect(Collectors.toList());
    }

    public TestimonialDto updateTestimonial(TestimonialDto testimonialDto, int id) {
        Testimonial testimonial = modelMapper.map(testimonialDto, Testimonial.class);
        Optional<Testimonial> optionalTestimonial = testimonialRepository
                .findById(id);
        if (optionalTestimonial.isPresent()) {
            testimonial.setId(id);
            testimonialRepository.save(testimonial);
        }

        return modelMapper.map(testimonial, TestimonialDto.class);
    }

    public void deleteTestimonial(int id) {
        boolean exists = testimonialRepository.existsById(id);
        if (!exists) {
            new TestimonialException("Testimonial not found");
        }
        testimonialRepository.deleteById(id);
    }

    public List<TestimonialDto> inicialTestimonial() {
        List<Integer> IdTestimonial = testimonialRepository
                .findAll().stream().map(Testimonial::getId).collect(Collectors.toList());
        initialRandom(IdTestimonial, RANGER_REG);

        List<Testimonial> testimonial = new ArrayList<>();
        for (int i = 0; i < lstRnd.size(); i++) {
            testimonial.add(testimonialRepository
                    .findByRegister(lstRnd.get(i)));
        }

        lstRnd.clear();

        return testimonial.stream()
                .map(m -> modelMapper.map(m, TestimonialDto.class))
                .collect(Collectors.toList());
    }

    public List<Integer> initialRandom(List<Integer> bound, int range) {
        Random rnd = new Random();
        for (int i = 0; i < range; i++) {
            int numRnd = bound.get(rnd.nextInt(bound.size()));
            if (!lstRnd.contains(numRnd))
                lstRnd.add(numRnd);
        }
        if (lstRnd.size() < range) {
            lstRnd.clear();
            initialRandom(bound, range);
        }
        return lstRnd;
    }
}


