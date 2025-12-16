package br.com.larrydev.movieflix.service;

import br.com.larrydev.movieflix.config.JWTUserData;
import br.com.larrydev.movieflix.entity.Category;
import br.com.larrydev.movieflix.entity.Movie;
import br.com.larrydev.movieflix.entity.Streaming;
import br.com.larrydev.movieflix.repository.MovieRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    private final MovieRepository movieRepository;
    private final CategoryService categoryService;
    private final StreamingService streamingService;

    public MovieService(MovieRepository movieRepository, CategoryService categoryService, StreamingService streamingService) {
        this.streamingService = streamingService;
        this.categoryService = categoryService;
        this.movieRepository = movieRepository;
    }

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public Movie createMovie(Movie movie) {
        movie.setCategories(this.findCategories(movie.getCategories()));
        movie.setStreamings(this.findStreamings(movie.getStreamings()));
        return movieRepository.save(movie);
    }

    public Optional<Movie> getMovieById(Long id) {
        return movieRepository.findById(id);
    }

    public Optional<Movie> updateMovie(Long id, Movie movieUpdate) {
        Optional<Movie> optMovie = movieRepository.findById(id);
        if (optMovie.isPresent()) {
            List<Category> categories = this.findCategories(movieUpdate.getCategories());
            List<Streaming> streamings = this.findStreamings(movieUpdate.getStreamings());

            //JWTUserData jwtUserData = (JWTUserData) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            Movie movie = optMovie.get();
            movie.setTitle(movieUpdate.getTitle());
            movie.setDescription(movieUpdate.getDescription());
            movie.setReleaseDate(movieUpdate.getReleaseDate());
            movie.setRating(movieUpdate.getRating());

            movie.getCategories().clear();
            movie.getCategories().addAll(categories);

            movie.getStreamings().clear();
            movie.getStreamings().addAll(streamings);

            movieRepository.save(movie);
            return Optional.of(movie);
        }
        return Optional.empty();
    }

    public void deleteMovieById(Long id) {
        movieRepository.deleteById(id);
    }

    public List<Movie> findByCategory(Long categoryId) {
        return movieRepository.findMovieByCategories(List.of(Category.builder().id(categoryId).build()));

    }

    private List<Category> findCategories(List<Category> categories) {
        List<Category> categoryList = new ArrayList<>();
        categories.forEach(category -> {
            categoryService.getCategoryById(category.getId()).ifPresent(categoryList::add);
        });
        return categoryList;
    }

    private List<Streaming> findStreamings(List<Streaming> streamings) {
        List<Streaming> streamingList = new ArrayList<>();
        streamings.forEach(streaming -> {
            streamingService.getStreamingById(streaming.getId()).ifPresent(streamingList::add);
        });
        return streamingList;
    }
}
