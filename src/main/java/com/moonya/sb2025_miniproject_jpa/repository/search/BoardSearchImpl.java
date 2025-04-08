package com.moonya.sb2025_miniproject_jpa.repository.search;

import com.moonya.sb2025_miniproject_jpa.domain.Board;
import com.moonya.sb2025_miniproject_jpa.domain.BoardUpFile;
import com.moonya.sb2025_miniproject_jpa.domain.QBoard;
import com.moonya.sb2025_miniproject_jpa.domain.QReply;
import com.moonya.sb2025_miniproject_jpa.dto.BoardListAllDTO;
import com.moonya.sb2025_miniproject_jpa.dto.BoardReplyCountDTO;
import com.moonya.sb2025_miniproject_jpa.dto.BoardUpFileDTO;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class BoardSearchImpl extends QuerydslRepositorySupport implements BoardSearch {

    public BoardSearchImpl() {
        super(Board.class);
    }

    @Override
    public Page<Board> searchBoardTitle3(Pageable pageable) {
        String keyword = "3"; // 검색할 키워드 (예제)

        QBoard board = QBoard.board; // Q도메인 객체
        BooleanBuilder booleanBuilder = new BooleanBuilder();

        booleanBuilder.or(board.writer.contains(keyword));
        booleanBuilder.or(board.title.contains(keyword));
        booleanBuilder.or(board.content.contains(keyword));

        JPQLQuery<Board> query = from(board);

        query.where(booleanBuilder);

        this.getQuerydsl().applyPagination(pageable, query);

        List<Board> boardList = query.fetch();

        long count = query.fetchCount();

        return new PageImpl<>(boardList, pageable, count);
    }

    @Override
    public Page<Board> searchAll(String[] searchTypes, String keyword, Pageable pageable) {

        QBoard board = QBoard.board; // Q도메인 객체
        QReply reply = QReply.reply;

        JPQLQuery<Board> query = from(board);
        BooleanBuilder booleanBuilder = new BooleanBuilder();


        if ((searchTypes != null && searchTypes.length > 0) && keyword != null) {
            for (String type : searchTypes) {
                switch (type) {
                    case "t":
                        booleanBuilder.or(board.title.contains(keyword));
                        break;
                    case "w":
                        booleanBuilder.or(board.writer.contains(keyword));
                        break;
                    case "c":
                        booleanBuilder.or(board.content.contains(keyword));
                        break;
                }
            }
            query.where(booleanBuilder);
        }

        this.getQuerydsl().applyPagination(pageable, query);

        List<Board> boardList = query.fetch();

        long count = query.fetchCount();

        return new PageImpl<>(boardList, pageable, count);

    }

    @Override
    public Page<BoardReplyCountDTO> searchWithReplyCount(String[] searchTypes, String keyword, Pageable pageable) {

        QBoard board = QBoard.board; // Q도메인 객체
        QReply reply = QReply.reply; // left outer join

        BooleanBuilder booleanBuilder = new BooleanBuilder();

        JPQLQuery<Board> query = from(board);
        query.leftJoin(reply).on(reply.board.eq(board));


        if ((searchTypes != null && searchTypes.length > 0) && keyword != null) {
            for (String type : searchTypes) {
                switch (type) {
                    case "t":
                        booleanBuilder.or(board.title.contains(keyword));
                        break;
                    case "w":
                        booleanBuilder.or(board.writer.contains(keyword));
                        break;
                    case "c":
                        booleanBuilder.or(board.content.contains(keyword));
                        break;
                }
            }
            query.where(booleanBuilder);
        }

        JPQLQuery<BoardReplyCountDTO> dtoQuery = query.select(Projections.bean(BoardReplyCountDTO.class,
                board.bno,
                board.title,
                board.writer,
                board.readCount,
                board.regDate,
                reply.count().as("replyCount"))).groupBy(board.bno);;


        this.getQuerydsl().applyPagination(pageable, dtoQuery);

        List<BoardReplyCountDTO> dtoList = dtoQuery.fetch();

        long count = dtoQuery.fetchCount();

        return new PageImpl<>(dtoList, pageable, count);

    }

    @Override
    public Page<BoardListAllDTO> searchWithAll(String[] searchTypes, String keyword, Pageable pageable) {

        QBoard board = QBoard.board; // Q도메인 객체
        QReply reply = QReply.reply; // left outer join

        BooleanBuilder booleanBuilder = new BooleanBuilder();


        JPQLQuery<Board> query = from(board);
        query.leftJoin(reply).on(reply.board.eq(board));

        if ((searchTypes != null && searchTypes.length > 0) && keyword != null) {
            for (String type : searchTypes) {
                switch (type) {
                    case "t":
                        booleanBuilder.or(board.title.contains(keyword));
                        break;
                    case "w":
                        booleanBuilder.or(board.writer.contains(keyword));
                        break;
                    case "c":
                        booleanBuilder.or(board.content.contains(keyword));
                        break;
                }
            }
            query.where(booleanBuilder);
        }

        this.getQuerydsl().applyPagination(pageable, query);

        query.groupBy(board);
        JPQLQuery<Tuple> tupleJPQLQuery = query.select(board, reply.countDistinct());
        List<Tuple> tupleList = tupleJPQLQuery.fetch();

        // 게시글 + 댓글 수 + 첨부파일들
        List<BoardListAllDTO> boardListAllDTOList = tupleList.stream().map( tuple -> {
            Board singleBoard = tuple.get(board);
            Long cnt = tuple.get(1, Long.class);

            List<BoardUpFileDTO> boardUpFileDTOList = singleBoard.getFileSet().stream().map(boardUpFile -> {
                BoardUpFileDTO boardUpFileDTO = BoardUpFileDTO.builder()
                        .uuid(boardUpFile.getUuid())
                        .originalFileName(boardUpFile.getOriginalFileName())
                        .ord(boardUpFile.getOrd())
                        .build();
                return boardUpFileDTO;
            }).toList();

            BoardListAllDTO boardListAllDTO = BoardListAllDTO.builder()
                    .bno(singleBoard.getBno())
                    .title(singleBoard.getTitle())
                    .writer(singleBoard.getWriter())
                    .replyCount(cnt)
                    .regDate(singleBoard.getRegDate())
                    .readCount(singleBoard.getReadCount())
                    .boardUpFileDTOList(boardUpFileDTOList)
                    .build();

            return boardListAllDTO;
        }).toList();

        return new PageImpl<>(boardListAllDTOList, pageable, query.fetchCount());
    }

}
