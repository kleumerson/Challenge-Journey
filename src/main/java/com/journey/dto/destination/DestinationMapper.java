package com.journey.dto.destination;

public class DestinationMapper {
    public static DestinationMapper fromDestinationToResponse;
    public static DestinationResponse fromDestinationToResponse(DestinationDto destinationDto) {
        return new DestinationResponse(
                destinationDto.getPhoto1(),
                destinationDto.getPhoto2(),
                destinationDto.getTitle(),
                destinationDto.getMeta(),
                destinationDto.getDescription()
        );
    }
}
