package ee.taavi.rendipood.controller;

import ee.taavi.rendipood.entity.Film;
import ee.taavi.rendipood.entity.FilmType;
import ee.taavi.rendipood.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

        List<Film> allfilms = filmRepository.findAll();
        System.out.println("All films are:");
        for(Film film : allfilms){
            System.out.println("    " + film.getTitle());
        }

        return filmRepository.findAll();
    }

    // Add film
    @PostMapping("films")
    public List<Film> addFilm(@RequestBody Film film){
        if(film.getTitle()==null || film.getTitle().isEmpty()){
            throw new RuntimeException("Title must be provided!");
        }
        if(film.getType()==null){
            throw new RuntimeException("Film type must be provided!");
        }
        film.setDays(0);
        film.setRental(null);
        filmRepository.save(film);
        return filmRepository.findAll();
    }

    // Delete film
    // localhost:8080/films/1
    @DeleteMapping("films/{id}")
    public List<Film> removeFilm(@PathVariable Long id){
        if (id == null){
            throw new RuntimeException("Provide ID of film to be deleted!");
        }
        filmRepository.deleteById(id);
        return filmRepository.findAll();
    }

    // Change film type
    // localhost:8080/change-type?id=1?newType=OLD
    @PatchMapping("change-type")
    public List<Film> changeFilmType(@RequestParam Long id, @RequestParam FilmType newType) {
        if (id == null){
            throw new RuntimeException("Provide ID of film to be edited!");
        }
        if (newType == null){
            throw new RuntimeException("New type not provided!");
        }
        System.out.println("Id = " + id);
        System.out.println("newType = " + newType);

        Film film = filmRepository.findById(id).orElseThrow();
        film.setType(newType);
        filmRepository.save(film);
        return filmRepository.findAll();
    }

}
