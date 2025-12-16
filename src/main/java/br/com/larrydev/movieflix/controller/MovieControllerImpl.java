package br.com.larrydev.movieflix.controller;

import br.com.larrydev.movieflix.controller.request.MovieRequest;
import br.com.larrydev.movieflix.controller.response.MovieResponse;
import br.com.larrydev.movieflix.mapper.MovieMapper;
import br.com.larrydev.movieflix.service.MovieService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movieflix/movie")
public class MovieControllerImpl implements  MovieController {

    private final MovieService movieService;

    public MovieControllerImpl(MovieService movieService) {
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
    public ResponseEntity<MovieResponse> postMovie(@Valid @RequestBody MovieRequest request) {
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

    @PutMapping(value = "{id}")
    public ResponseEntity<MovieResponse> putMovie(@PathVariable Long id, @Valid @RequestBody MovieRequest request) {
        return movieService.updateMovie(id, MovieMapper.toMovie(request))
                .map(updatedMovie -> ResponseEntity.ok().body(MovieMapper.toMovieResponse(updatedMovie)))
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

    @GetMapping("/search")
    public ResponseEntity<List<MovieResponse>> findByCategory(@RequestParam("category") Long category) {
        List<MovieResponse> movieResponses = movieService.findByCategory(category)
                .stream()
                .map(MovieMapper::toMovieResponse)
                .toList();
        return ResponseEntity.ok().body(movieResponses);
    }
}
