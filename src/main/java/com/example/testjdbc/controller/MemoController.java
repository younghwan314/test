package com.example.testjdbc.controller;

import com.example.testjdbc.dto.MemoRequestDto;
import com.example.testjdbc.dto.MemoResponseDto;
import com.example.testjdbc.service.MemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemoController {

    private final MemoService memoService;

    // Create API
    @PostMapping("/memos")
    public ResponseEntity<MemoResponseDto> save(@RequestBody MemoRequestDto dto) {
        return ResponseEntity.ok(memoService.save(dto));
    }

    // Read All API
    @GetMapping("/memos")
    public ResponseEntity<List<MemoResponseDto>> findAll() {
        return ResponseEntity.ok(memoService.findAll());
    }

    // Read One API
    @GetMapping("/memos/{memoId}")
    public ResponseEntity<MemoResponseDto> findById(@PathVariable Long memoId) {
        return ResponseEntity.ok(memoService.findById(memoId));
    }

    // Update API
    @PutMapping("/memos/{memoId}")
    public ResponseEntity<MemoResponseDto> updateContent(@PathVariable Long memoId, @RequestBody MemoRequestDto dto) {
        return ResponseEntity.ok(memoService.updateContent(memoId, dto));
    }

    // Delete API
    @DeleteMapping("/memos/{memoId}")
    public void deleteById(@PathVariable Long memoId) {
        memoService.deleteById(memoId);
    }
}
