package com.example.testjdbc.repository;

import com.example.testjdbc.entity.Memo;

import java.util.List;
import java.util.Optional;

public interface MemoRepository {

    Memo save(Memo memo); // Create
    Optional<Memo> findById(Long id); // Read 단건
    List<Memo> findAll(); // Read 다건
    Memo updateContent(Long id, String content); // Update
    void deleteById(Long id); // Delete
}
