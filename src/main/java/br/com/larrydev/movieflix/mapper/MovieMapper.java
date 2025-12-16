package br.com.larrydev.movieflix.mapper;

import br.com.larrydev.movieflix.controller.request.MovieRequest;
import br.com.larrydev.movieflix.controller.response.CategoryResponse;
import br.com.larrydev.movieflix.controller.response.MovieResponse;
import br.com.larrydev.movieflix.controller.response.StreamingResponse;
import br.com.larrydev.movieflix.entity.Category;
import br.com.larrydev.movieflix.entity.Movie;
import br.com.larrydev.movieflix.entity.Streaming;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class MovieMapper {
    public static Movie toMovie(MovieRequest movieRequest) {

        List<Category> categories = movieRequest.categories().stream()
                .map(categoryId -> Category.builder().id(categoryId).build())
                .toList();

        List<Streaming> streamings = movieRequest.streamings().stream()
                .map(streamingId -> Streaming.builder().id(streamingId).build())
                .toList();

        return Movie
                .builder()
                .title(movieRequest.title())
                .description(movieRequest.description())
                .releaseDate(movieRequest.releaseDate())
                .rating(movieRequest.rating())
                .categories(categories)
                .streamings(streamings)
                .build();
    }

    public static MovieResponse toMovieResponse(Movie movie) {

        List<CategoryResponse> categoryResponses = movie.getCategories()
                .stream()
                .map(CategoryMapper::toCategoryResponse)
                .toList();

        List<StreamingResponse> streamingResponses = movie.getStreamings()
                .stream()
                .map(StreamingMapper::toStreamingResponse)
                .toList();

        return MovieResponse
                .builder()
                .id(movie.getId())
                .title(movie.getTitle())
                .description(movie.getDescription())
                .releaseDate(movie.getReleaseDate())
                .rating(movie.getRating())
                .categories(categoryResponses)
                .streamings(streamingResponses)
                .build();
    }
}
