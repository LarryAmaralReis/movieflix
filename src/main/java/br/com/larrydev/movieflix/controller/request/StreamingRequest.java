package br.com.larrydev.movieflix.controller.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record StreamingRequest(
        @NotBlank(message = "Streaming name obrigatory")
        @Size(max = 100, message = "Streaming name must be up to 100 characters")
        String name
) {
}
