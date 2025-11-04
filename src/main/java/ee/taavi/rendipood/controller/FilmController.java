package ee.taavi.rendipood.controller;

import ee.taavi.rendipood.entity.Film;
import ee.taavi.rendipood.entity.FilmType;
import ee.taavi.rendipood.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class FilmController {

    @Autowired
    private FilmRepository filmRepository;

    // Available films
    @GetMapping("available-films")
    public List<Film> availableFilms(){
        return filmRepository.findByDays(0);
    }

    // All films
    @GetMapping("films")
    public List<Film> getAllFilms(){
        return filmRepository.findAll();
    }

    // Add film
    @PostMapping("films")
    public List<Film> addFilm(@RequestBody Film film){
        film.setDays(0);
        film.setRental(null);
        filmRepository.save(film);
        return filmRepository.findAll();
    }

    // Delete film
    // localhost:8080/films/1
    @DeleteMapping("films/{id}")
    public List<Film> removeFilm(@PathVariable Long id){
        filmRepository.deleteById(id);
        return filmRepository.findAll();
    }

    // Change film type
    // localhost:8080/change-type?id=1?newType=OLD
    @PatchMapping("change-type")
    public List<Film> changeFilmType(@RequestParam Long id, @RequestParam FilmType newType) {
        Film film = filmRepository.findById(id).orElseThrow();
        film.setType(newType);
        filmRepository.save(film);
        return filmRepository.findAll();
    }

}
