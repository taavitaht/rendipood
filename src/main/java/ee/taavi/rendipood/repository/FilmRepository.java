package ee.taavi.rendipood.repository;

import ee.taavi.rendipood.entity.Film;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FilmRepository extends JpaRepository <Film, Long>{

    //List<Film> findByAvailableTrue();

    // SELECT * FROM films WHERE days = 0")
    List<Film> findByDays(int days);
}
