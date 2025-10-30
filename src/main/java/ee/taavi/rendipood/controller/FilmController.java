package ee.taavi.rendipood.controller;

import ee.taavi.rendipood.entity.Film;
import ee.taavi.rendipood.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FilmController {

    @Autowired
    private FilmRepository filmRepository;

    @GetMapping("allfilms")
    public List<Film> getAllFilms(){
        return filmRepository.findAll();
    }
    @GetMapping("availablefilms")
    public List<Film> getAvailableFilms(){
        return filmRepository.findByAvailableTrue();
    }


}
