package com.moonya.sb2025_miniproject_jpa.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "board")
public class BoardUpFile implements Comparable<BoardUpFile> {
    @Id
    private String uuid;

    private String originalFileName;

    private int ord;

    @ManyToOne
    private Board board;

    public void setBoard(Board board) {
        this.board = board;
    }

    @Override
    public int compareTo(BoardUpFile o) {
        return this.ord - o.ord;
    }
}
