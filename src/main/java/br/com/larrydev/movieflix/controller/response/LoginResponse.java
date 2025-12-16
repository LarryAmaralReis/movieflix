package br.com.larrydev.movieflix.controller.response;

import lombok.Builder;

@Builder
public record LoginResponse(String token) {
}
