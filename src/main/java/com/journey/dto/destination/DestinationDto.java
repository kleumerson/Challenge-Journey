package com.journey.dto.destination;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class DestinationDto {
    private int id;

    @NotNull(message = "Name is mandatory")
    private String name;

    @NotNull(message = "Photo is mandatory")
    private String photo;

    @NotNull(message = "Price is mandatory")
    @DecimalMin("1.00")
    private float price;

    @NotNull(message = "Photo Destination [1] is mandatory")
    private String photo1;

    @NotNull(message = "Photo Destination [2] is mandatory")
    private String photo2;

    @NotNull(message = "Title is mandatory")
    private String title;

    @NotNull(message = "Meta is mandatory")
    @Length(max = 160, message = "The meta should have in max {max} words")
    private String meta;

    private String description;
}
