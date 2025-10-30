package ee.taavi.rendipood.controller;

import ee.taavi.rendipood.entity.Film;
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
    @GetMapping("availablefilms")
    public List<Film> getAvailableFilms(){
        return filmRepository.findByAvailableTrue();
    }

    // All films
    @GetMapping("films")
    public List<Film> getAllFilms(){
        return filmRepository.findAll();
    }

    // Add film
    @PostMapping("films")
    public List<Film> addFilm(@RequestBody Film film){
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

    // Change film type, Patch request with text body
    // localhost:8080/films/1
    @PatchMapping("films/{id}")
    public List<Film> patchFilm(@PathVariable Long id, @RequestBody String newType) {
        Optional<Film> filmOptional = filmRepository.findById(id);
        Film film = filmOptional.get();
        film.setType(newType);
        filmRepository.save(film);
        return filmRepository.findAll();
    }

}
