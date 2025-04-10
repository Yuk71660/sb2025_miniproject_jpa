package com.moonya.sb2025_miniproject_jpa.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.BatchSize;

import java.util.HashSet;
import java.util.Set;

// 엔티티 클래스에 setter쓰면 죄다 db에 업데이트 날리니까 @쓰지말고 필요한거만 직접 만들자
@Entity // 아래의 클래스가 db의 테이블로 매핑
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Board extends BaseEntity {
    @Id // 아래 필드를 pk로 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY ) // unique한 값을 생성하는 방법(현재는 mysql의 AI)
    private Long bno;

    @Column(length = 13, nullable = false)
    private String writer;
    @Column(length = 500, nullable = false)
    private String title;
    @Column(length = 2000, nullable = false)
    private String content;
    @Builder.Default
    private Long readCount = 0L;

    // 상위 엔티티에서 하위 엔티티의 행동을 관리한다.
    @OneToMany(mappedBy = "board", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    @BatchSize(size = 20)
    private Set<BoardUpFile> fileSet = new HashSet<>();

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setReadCount() {
        this.readCount = this.readCount + 1l;
    }

    public void addUpFile(String uuid, String originalFileName, Boolean img) {
        BoardUpFile boardUpFile = BoardUpFile.builder()
                .uuid(uuid)
                .originalFileName(originalFileName)
                .board(this)
                .img(img)
                .ord(this.fileSet.size())
                .build();
        this.fileSet.add(boardUpFile);
    }

    public void clearAllFiles() {

        this.fileSet.forEach(boardUpFile -> boardUpFile.setBoard(null));
        this.fileSet.clear();
    }

    // 파일의 이름을 받아 파일을 db에서 삭제하는 메서드
    public void removeFile(String uuid) {
//        Iterator<BoardUpFile> iterator = this.fileSet.iterator();
//        while (iterator.hasNext()) {
//            BoardUpFile value = iterator.next();
//            if (value.getUuid().equals(uuid)){
//                value.setBoard(null);
//                iterator.remove();
//            }
//        }
        this.fileSet.removeIf(boardUpFile -> boardUpFile.getUuid().equals(uuid));
    }
}
