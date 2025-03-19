package com.moonya.sb2025_miniproject_jpa.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Getter
@Setter
@ToString
public class PageResponseDTO {
    private int page; // 현재 페이지 번호
    private int size; // 1페이지당 출력할 갯수
    private int total; // 전체 row 갯수
    private int totalPage; // 전체 페이지 갯수

    private int start; // 시작 페이지 번호
    private int end; // 끝 페이지 번호

    private boolean prev; // 이전 블럭 존재 여부
    private boolean next; // 다음 블럭 존재 여부

    private List<BoardDTO> dtoList; // 페이징된 데이터

    private List<Integer> pageNationList = new ArrayList<>();

    @Builder(builderMethodName = "withAll")
    public PageResponseDTO(PageRequestDTO pageRequestDTO, List<BoardDTO> dtoList, int total) {
        if (total <= 0) {
            this.dtoList = List.of();
            this.pageNationList = List.of();
            return;
        }
        this.total = total;
        this.page = pageRequestDTO.getPage();
        this.size = pageRequestDTO.getSize();
        this.dtoList = dtoList;

        // 블록의 마지막 페이지
        this.end = (int) (Math.ceil(this.page / 10.0)) * 10;
        this.start = this.end - 9;

        // 전체 마지막 페이지
        this.totalPage = (int) Math.ceil((double) this.total / this.size);
        if (totalPage < this.end) {
            this.end = totalPage;
        }

        IntStream.rangeClosed(start, end).forEach(i -> this.pageNationList.add(i));

        this.prev = this.start > 1;
        this.next = this.end < totalPage;
    }
}
