package br.com.larrydev.movieflix.controller.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Builder
public record MovieResponse(
        @Schema(type="long", example="1", description="Unique identifier of the movie")
        Long id,
        @Schema(type="string", example="Inception", description="Title of the movie")
        String title,
        String description,
        @JsonFormat(shape =  JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
        LocalDate releaseDate,
        double rating,
        List<CategoryResponse> categories,
        List<StreamingResponse> streamings) {
}
