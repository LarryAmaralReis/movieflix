package br.com.larrydev.movieflix.controller.request;

import lombok.Builder;

@Builder
public record StreamingRequest(String name) {
}
