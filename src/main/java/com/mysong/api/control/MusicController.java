package com.mysong.api.control;

import com.mysong.api.model.Song;
import com.mysong.api.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(value = "/music")
public class MusicController extends BasicController {

    private final SongService songService;

    @Autowired
    public MusicController(SongService songService) {
        this.songService = songService;
    }

    @GetMapping("/songs")
    public ResponseEntity<Map<String, Object>> findAll() {
        List<Song> response;
        final Map<String, Object> result = new HashMap<>();
        try {
            response = songService.findAll();
            result.put("success", true);
            result.put("error", null);
            result.put("body", response);
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } catch (Exception e) {
            result.put("success", false);
            result.put("error", e.getMessage());
            result.put("body", null);
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(result);
        }
    }

    @GetMapping("/songs/{id}")
    public ResponseEntity<Map<String, Object>> findById(@PathVariable Long id) {
        Optional<Song> response;
        final Map<String, Object> result = new HashMap<>();
        try {
            response = songService.findById(id);
            result.put("success", true);
            result.put("error", null);
            result.put("body", response);
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } catch (Exception e) {
            result.put("success", false);
            result.put("error", e.getMessage());
            result.put("body", null);
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(result);
        }
    }

}
