package ru.buzynnikov.bidservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.buzynnikov.bidservice.models.Bid;

@Repository
public interface BidRepository extends JpaRepository<Bid, Long> {
}
