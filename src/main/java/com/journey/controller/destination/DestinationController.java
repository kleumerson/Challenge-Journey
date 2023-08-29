package com.journey.controller.destination;

import com.journey.dto.destination.DestinationDto;
import com.journey.dto.destination.DestinationMapper;
import com.journey.dto.destination.DestinationResponse;
import com.journey.exceptions.destination.DestinationNotFoundException;
import com.journey.service.destination.DestinationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@Validated
@RequestMapping("/api/v1/destinations")
public class DestinationController {
    @Autowired
    DestinationService destinationService;

    @PostMapping
    public ResponseEntity<DestinationDto> createDestination(@Valid @RequestBody DestinationDto destinationDto) throws Exception{
        DestinationDto createDestination = destinationService.createDestination(destinationDto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{destinations}")
                .buildAndExpand(createDestination.getId()).toUri();

        return ResponseEntity.created(uri).body(createDestination);
    }
    @PutMapping("/{id}")
    public ResponseEntity<DestinationDto> updateDestination(@RequestBody DestinationDto destinationDto, @PathVariable int id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(destinationService.updateDestination(destinationDto, id));
    }
    @DeleteMapping("/{id}")
    public void deleteDestination(@PathVariable int id) {
        destinationService.deleteDestination(id);
    }

    @GetMapping
    public List<DestinationDto> getDestination() {
        return destinationService.getDestination();
    }
    @GetMapping(path = "/")
    public ResponseEntity<List<DestinationDto>> getNameDestination(@RequestParam("name") String name) {
        List<DestinationDto> destinationDto = destinationService.getNameDestination(name);
        if (destinationDto.size() == 0)
            throw new DestinationNotFoundException();

        return new ResponseEntity<>(destinationDto, HttpStatus.OK);
    }
    @GetMapping("{id}")
    public ResponseEntity<DestinationResponse> getDetailsDestinations(@PathVariable int id){
        DestinationResponse destinationResponse = DestinationMapper
                .fromDestinationToResponse(destinationService.getdetailsDestinations(id));

        return new ResponseEntity<>(destinationResponse, HttpStatus.OK);
    }
}
