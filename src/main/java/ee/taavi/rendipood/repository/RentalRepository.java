package ee.taavi.rendipood.repository;

import ee.taavi.rendipood.entity.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalRepository extends JpaRepository<Rental, Long> {
}
