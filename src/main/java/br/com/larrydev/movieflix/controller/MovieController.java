package br.com.larrydev.movieflix.controller;

import br.com.larrydev.movieflix.controller.request.MovieRequest;
import br.com.larrydev.movieflix.controller.response.MovieResponse;
import br.com.larrydev.movieflix.mapper.MovieMapper;
import br.com.larrydev.movieflix.service.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movieflix/movie")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public ResponseEntity<List<MovieResponse>> getAllMovies() {
        List<MovieResponse> movieResponses = movieService.getAllMovies()
                .stream()
                .map(MovieMapper::toMovieResponse)
                .toList();
        return ResponseEntity.ok().body(movieResponses);
    }

    @PostMapping
    public ResponseEntity<MovieResponse> postMovie(@RequestBody MovieRequest request) {
        MovieResponse createdMovie = MovieMapper
                .toMovieResponse(movieService
                        .createMovie(MovieMapper
                                .toMovie(request)
                        )
                );
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMovie);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<MovieResponse> getMovie(@PathVariable Long id) {
        return movieService.getMovieById(id)
                .map(movie -> ResponseEntity.ok().body(MovieMapper.toMovieResponse(movie)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<?> deleteMovie(@PathVariable Long id) {
        return movieService.getMovieById(id)
                .map(movie -> {
                    movieService.deleteMovieById(id);
                    return ResponseEntity.noContent().build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
