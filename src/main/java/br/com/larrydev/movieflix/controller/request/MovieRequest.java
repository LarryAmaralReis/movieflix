package br.com.larrydev.movieflix.controller.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Builder
public record MovieRequest(
        @NotBlank(message = "Movie title obrigatory")
        String title,
        String description,
        @JsonFormat(shape =  JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
        LocalDate releaseDate,
        double rating,
        List<Long> categories,
        List<Long> streamings) {
}
