package br.com.larrydev.movieflix.controller;

import br.com.larrydev.movieflix.controller.request.StreamingRequest;
import br.com.larrydev.movieflix.controller.response.StreamingResponse;
import br.com.larrydev.movieflix.mapper.StreamingMapper;
import br.com.larrydev.movieflix.service.StreamingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movieflix/streaming")
public class StreamingController {

    private final StreamingService streamingService;

    public StreamingController(StreamingService streamingService) {
        this.streamingService = streamingService;
    }

    @GetMapping
    public ResponseEntity<List<StreamingResponse>> getAllStreamings() {
        List<StreamingResponse> streamingResponses = streamingService.getAllStreamings()
                .stream()
                .map(StreamingMapper::toStreamingResponse)
                .toList();
        return ResponseEntity.ok().body(streamingResponses);
    }

    @PostMapping
    public ResponseEntity<StreamingResponse> postStreaming(@RequestBody StreamingRequest request) {
        StreamingResponse createdStreaming = StreamingMapper
                .toStreamingResponse(streamingService
                        .createStreaming(StreamingMapper
                                .toStreaming(request)
                        )
                );
        return ResponseEntity.status(HttpStatus.CREATED).body(createdStreaming);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<StreamingResponse> getStreaming(@PathVariable Long id) {
        return streamingService.getStreamingById(id)
                .map(streaming -> ResponseEntity.ok().body(StreamingMapper.toStreamingResponse(streaming)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<?> deleteStreaming(@PathVariable Long id) {
        return streamingService.getStreamingById(id)
                .map(streaming -> {
                    streamingService.deleteStreamingById(id);
                    return ResponseEntity.noContent().build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
