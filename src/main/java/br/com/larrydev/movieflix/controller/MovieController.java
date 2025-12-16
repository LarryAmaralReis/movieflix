package br.com.larrydev.movieflix.controller;

import br.com.larrydev.movieflix.controller.request.MovieRequest;
import br.com.larrydev.movieflix.controller.response.MovieResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Tag(name = "Movie Controller", description = "Endpoints for managing movies")
public interface MovieController {

    @Operation(summary = "Get all movies", description = "Retrieves a list of all movies",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "200", description = "List of movies retrieved successfully",
            content = @Content(
                    array = @ArraySchema(
                            schema = @Schema(
                                    implementation = MovieResponse.class))))
    ResponseEntity<List<MovieResponse>> getAllMovies();

    @Operation(summary = "Create a new movie", description = "Creates a new movie with the provided details",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "201", description = "Movie created successfully",
            content = @Content(
                    schema = @Schema(
                            implementation = MovieResponse.class)))
    ResponseEntity<MovieResponse> postMovie(@Valid @RequestBody MovieRequest request);

    @Operation(summary = "Get one movie by id", description = "Retrieve a one movie by its id",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "200", description = "Movie retrieved successfully",
            content = @Content(
                    schema = @Schema(
                            implementation = MovieResponse.class)))
    @ApiResponse(responseCode = "404", description = "Movie not found", content = @Content())
    ResponseEntity<MovieResponse> getMovie(@PathVariable Long id);

    @Operation(summary = "Update a movie", description = "Updates the details of an existing movie",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "200", description = "Movie updated successfully",
            content = @Content(
                    schema = @Schema(
                            implementation = MovieResponse.class)))
    @ApiResponse(responseCode = "404", description = "Movie not found", content = @Content())
    ResponseEntity<MovieResponse> putMovie(@PathVariable Long id, @Valid @RequestBody MovieRequest request);

    @Operation(summary = "Delete a movie", description = "Deletes an existing movie by its id",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "204", description = "Movie deleted successfully", content = @Content())
    @ApiResponse(responseCode = "404", description = "Movie not found", content = @Content())
    ResponseEntity<?> deleteMovie(@PathVariable Long id);

    @Operation(summary = "Get movie by category", description = "Get movies by category",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "200", description = "Movie retrieved successfully",
            content = @Content(
                    array = @ArraySchema(
                            schema = @Schema(
                                    implementation = MovieResponse.class))))
    @ApiResponse(responseCode = "204", description = "Movie deleted successfully", content = @Content())
    ResponseEntity<List<MovieResponse>> findByCategory(@RequestParam("category") Long category);
}
