package com.example.testjdbc.service;

import com.example.testjdbc.dto.MemoRequestDto;
import com.example.testjdbc.dto.MemoResponseDto;
import com.example.testjdbc.entity.Memo;
import com.example.testjdbc.repository.MemoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemoService {

    private final MemoRepository memoRepository;

    // save 메서드
    @Transactional
    public MemoResponseDto save(MemoRequestDto dto) {
        Memo memo = new Memo(dto.getContent()); // 저장되기 전의 Memo
        Memo savedMemo = memoRepository.save(memo); // 저장된 Memo
        return new MemoResponseDto(savedMemo.getId(), savedMemo.getContent());
    }

    // findAll 메서드
    @Transactional(readOnly = true)
    public List<MemoResponseDto> findAll() {
        List<Memo> memos = memoRepository.findAll();

        // 리턴 타입을 맞추기 위한 dto 리스트 그릇
        List<MemoResponseDto> dtoList = new ArrayList<>();
        for (Memo memo : memos) {
            dtoList.add(new MemoResponseDto(memo.getId(), memo.getContent()));
        }

        return dtoList;
    }

    @Transactional(readOnly = true)
    public MemoResponseDto findById(Long id) {
        Memo memo = memoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 id가 존재하지 않습니다.")
        );

        return new MemoResponseDto(memo.getId(), memo.getContent());
    }

    @Transactional
    public MemoResponseDto updateContent(Long id, MemoRequestDto dto) {
        memoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 id가 존재하지 않습니다.")
        );

        Memo updatedMemo = memoRepository.updateContent(id, dto.getContent());

        return new MemoResponseDto(updatedMemo.getId(), updatedMemo.getContent());
    }

    @Transactional
    public void deleteById(Long id) {
        memoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 id가 존재하지 않습니다.")
        );

        memoRepository.deleteById(id);
    }

}
