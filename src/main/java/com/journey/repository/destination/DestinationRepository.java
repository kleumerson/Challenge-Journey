package com.journey.repository.destination;

import com.journey.model.destination.Destination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DestinationRepository extends JpaRepository<Destination, Integer> {
    Optional<Destination> findByName(String name);
    @Query(value = "select d from Destination d where d.name =:name")
    List<Destination> findByDestination(String name);
    @Query(value = "select d from Destination d where d.id =:id")
    Destination findByDestinationIdAll(int id);
}
