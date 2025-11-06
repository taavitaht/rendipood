package ee.taavi.rendipood.controller;

import ee.taavi.rendipood.entity.Film;
import ee.taavi.rendipood.entity.Rental;
import ee.taavi.rendipood.model.RentalFilm;
import ee.taavi.rendipood.repository.FilmRepository;
import ee.taavi.rendipood.repository.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RentalController {

    @Autowired
    private RentalRepository rentalRepository;

    @Autowired
    private FilmRepository filmRepository;

    @GetMapping("rentals")
    public List<Rental> findAll(){
        return rentalRepository.findAll();
    }

    @PostMapping("start-rental")
    public Rental startRental(@RequestBody List<RentalFilm> rentalFilms){

        Rental rental = new Rental();
        //Rental dbRental = rentalRepository.save(rental);

        List<Film> films = new ArrayList<>();
        for(RentalFilm rentalFilm : rentalFilms){
            if(rentalFilm.getRentedDays() <= 0){
                throw new RuntimeException("Rental days must be greater than 0!");
            }
            Film film = filmRepository.findById(rentalFilm.getFilmId()).orElseThrow();
            if(film.getDays() > 0){
                throw new RuntimeException("Film is already rented!");
            }
            film.setDays(rentalFilm.getRentedDays());
            film.setRental(rental);
            films.add(film);
        }

        rental.setFilms(films);
        double sum = 0;
        // TODO: for loopis leiame summa
        rental.setInitialFee(sum);

        return rentalRepository.save(rental);

    }
}
