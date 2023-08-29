package com.journey.exceptions.testimonial;

public class TestimonialException extends RuntimeException{

    private String mensagem;

    public TestimonialException(String mensagem) {
        this.mensagem = mensagem;
    }

    @Override
    public String getMessage(){
        return this.mensagem;
    }
}
