package example.controller;

import example.dto.CatDto;
import example.service.CatService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cats")
@RequiredArgsConstructor
public class CatController {
    private final CatService catService;

    @GetMapping // Эндпоинт GET /api/cats
    public ResponseEntity<Page<CatDto>> getAllCats(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(catService.findAll(pageable));
    }

    @GetMapping("/{id}") // Эндпоинт GET /api/cats/1
    public ResponseEntity<CatDto> getCatById(@PathVariable Long id) {
        return ResponseEntity.ok(catService.findById(id));
    }

    @GetMapping("/by-color")
    public ResponseEntity<Page<CatDto>> getCatsByColor(
            @RequestParam String color,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(catService.findByColor(color, pageable));
    }

    @PostMapping // Эндпоинт POST /api/cats
    public ResponseEntity<CatDto> createCat(@RequestBody CatDto catDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(catService.save(catDto));
    }
}