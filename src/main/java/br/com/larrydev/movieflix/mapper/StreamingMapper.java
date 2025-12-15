package br.com.larrydev.movieflix.mapper;

import br.com.larrydev.movieflix.controller.request.StreamingRequest;
import br.com.larrydev.movieflix.controller.response.StreamingResponse;
import br.com.larrydev.movieflix.entity.Streaming;
import lombok.experimental.UtilityClass;

@UtilityClass
public class StreamingMapper {
    public static Streaming toStreaming(StreamingRequest streamingRequest) {
        return Streaming
                .builder()
                .name(streamingRequest.name())
                .build();
    }

    public static StreamingResponse toStreamingResponse(Streaming streaming) {
        return StreamingResponse
                .builder()
                .id(streaming.getId())
                .name(streaming.getName())
                .build();
    }
}
