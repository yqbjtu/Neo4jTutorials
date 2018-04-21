package com.yq.controller;

import com.yq.domain.*;
;
import com.yq.repository.MovieRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 */
@RestController
@RequestMapping("/movie")
public class MovieController {
    private static Logger log =  LoggerFactory.getLogger(MovieController.class);
    @Autowired
    MovieRepository  movieRepository;

    @GetMapping("/all")
    public Iterable<Movie> findAll() {
        return movieRepository.findAll();
    }

    @GetMapping("/findByReveueGT")
    public Iterable<Movie> findByRevenueGT(@RequestParam Integer revenue) {
        return movieRepository.findByRevenueGreaterThan(revenue);
    }

    @GetMapping("/customized")
    public Iterable<Movie> findCustomized() {
        return movieRepository.someCustomMethod();
    }

    @GetMapping("/customizedFindById")
    public Iterable<Movie> findCustomizedById(@RequestParam Integer id) {
        return movieRepository.findUseClassMethod(id);
    }

    @GetMapping("/customizedFindPersonByName")
    public Iterable<Person> customizedFindPersonByName(@RequestParam String name) {
        return movieRepository.findPersonMethod(name);
    }

    @GetMapping("/findMovieDataByMovieTitle")
    public String findMovieDataByMovieTitle(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                            @RequestParam(value = "size", defaultValue = "15") Integer size,
                                            @RequestParam String title) {
        MovieData data = movieRepository.getMovieData(title);
        log.info("data:" + data.toString());
        return data==null ? "no record" : data.toString();
    }

    @GetMapping("/myFindByTitle")
    public MovieTitleAndRevenue myfindByTitle(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                            @RequestParam(value = "size", defaultValue = "15") Integer size,
                                            @RequestParam String title) {
        MovieTitleAndRevenue data = movieRepository.findByTitle(title);
        log.info("data:" + data.toString());
        return data;
    }

    @GetMapping("/findByCategory")
    public Collection<MovieNameAndRevenue> findByCategory(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                         @RequestParam(value = "size", defaultValue = "15") Integer size,
                                                         @RequestParam String category) {
        Collection<MovieNameAndRevenue> data = movieRepository.findByCategory(category);
        return data;
    }
}
