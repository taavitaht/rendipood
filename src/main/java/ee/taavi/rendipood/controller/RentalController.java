package ee.taavi.rendipood.controller;

import ee.taavi.rendipood.entity.Film;
import ee.taavi.rendipood.entity.Rental;
import ee.taavi.rendipood.model.RentalFilm;
import ee.taavi.rendipood.repository.FilmRepository;
import ee.taavi.rendipood.repository.RentalRepository;
import ee.taavi.rendipood.service.RentalService;
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
    private RentalService rentalService;

    @GetMapping("rentals")
    public List<Rental> findAll(){
        return rentalRepository.findAll();
    }

    @PostMapping("start-rental")
    public Rental startRental(@RequestBody List<RentalFilm> rentalFilms){

        Rental rental = new Rental();
        List<Film> films = new ArrayList<>();

        double sum = rentalService.getSumAndAddRentalToFilm(rentalFilms, rental, films);

        rental.setInitialFee(sum);
        rental.setFilms(films);

        return rentalRepository.save(rental);
    }
    @PostMapping("end-rental")
    public Rental endRental(@RequestBody List<RentalFilm> rentalFilms){

        //List<Film> films = rentalService.getAllFilmsFromDB(rentalFilms);

        //Rental rental = rentalService.checkifAllFilmsFromSameRental(films);

        ee.taavi.rendipood.entity.Rental rental = rentalService.calculateLateFee(rentalFilms);

        //rental.setInitialFee(sum);

        return rentalRepository.save(rental);
    }

}
