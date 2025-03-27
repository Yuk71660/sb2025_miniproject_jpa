package com.moonya.sb2025_miniproject_jpa.service;

import com.moonya.sb2025_miniproject_jpa.domain.Reply;
import com.moonya.sb2025_miniproject_jpa.dto.PageRequestDTO;
import com.moonya.sb2025_miniproject_jpa.dto.PageResponseDTO;
import com.moonya.sb2025_miniproject_jpa.dto.ReplyDTO;
import com.moonya.sb2025_miniproject_jpa.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Log4j2
public class ReplyServiceImpl implements ReplyService{
    private final ReplyRepository replyRepository;
    private final ModelMapper modelMapper;

    @Override
    public Long registReply(ReplyDTO replyDTO) {
        Reply reply = modelMapper.map(replyDTO, Reply.class);
        Long newRno = replyRepository.save(reply).getRno();
        return newRno;
    }

    @Override
    public PageResponseDTO<ReplyDTO> getReplies(Long bno, PageRequestDTO pageRequestDTO) {

        Pageable pageable = pageRequestDTO.getPageable("rno");

        Page<Reply> result = replyRepository.replyListOfBoard(bno, pageable);

        List<ReplyDTO> dtoList = result.getContent().stream().map(reply -> {
            return modelMapper.map(reply, ReplyDTO.class);
        }).toList();

        PageResponseDTO<ReplyDTO> pageResponseDTO = new PageResponseDTO<ReplyDTO>(pageRequestDTO, dtoList, (int)result.getTotalElements(),
                pageRequestDTO.getSearchType(), pageRequestDTO.getKeyword(), pageRequestDTO.getLink());

        if (pageResponseDTO.getDtoList() == null) {
            throw new NoSuchElementException("텅");
        }

        return pageResponseDTO;
    }
}
