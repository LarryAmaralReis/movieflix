package br.com.larrydev.movieflix.controller.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Builder
public record MovieRequest(
        @Schema(type="string", example="Inception", description="Title of the movie")
        @NotBlank(message = "Movie title obrigatory")
        String title,

        @Schema(type="string", example="A mind-bending thriller about dreams within dreams.", description="Description of the movie")
        String description,

        @Schema(type="date", example="16/07/2010", description="Release date of the movie in dd/MM/yyyy format")
        @JsonFormat(shape =  JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
        LocalDate releaseDate,

        @Schema(type="number", format="double", example="8.8", description="Rating of the movie")
        double rating,

        @Schema(type="array", example="[1, 2]", description="List of category IDs associated with the movie")
        List<Long> categories,

        @Schema(type="array", example="[1, 2]", description="List of streaming platform IDs where the movie is available")
        List<Long> streamings) {
}
