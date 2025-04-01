package com.moonya.sb2025_miniproject_jpa.service;

import com.moonya.sb2025_miniproject_jpa.domain.Reply;
import com.moonya.sb2025_miniproject_jpa.dto.PageRequestDTO;
import com.moonya.sb2025_miniproject_jpa.dto.PageResponseDTO;
import com.moonya.sb2025_miniproject_jpa.dto.ReplyDTO;
import com.moonya.sb2025_miniproject_jpa.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class ReplyServiceImpl implements ReplyService {
    private final ReplyRepository replyRepository;
    private final ModelMapper modelMapper;

    @Override
    public Long registReply(ReplyDTO replyDTO) {
        Reply reply = modelMapper.map(replyDTO, Reply.class);
        Long newRno = replyRepository.save(reply).getRno();
        return newRno;
    }

    @Override
    public PageResponseDTO<ReplyDTO> getListOfBoard(Long bno, PageRequestDTO pageRequestDTO) {
        Pageable pageable = PageRequest.of(pageRequestDTO.getPage() - 1, pageRequestDTO.getSize(),
                Sort.by("rno").descending());

        Page<Reply> result = replyRepository.replyListOfBoard(bno, pageable);

        List<ReplyDTO> replyDTOList = result.getContent().stream().map(reply -> modelMapper.map(reply, ReplyDTO.class))
                .collect(Collectors.toList());

        int total = (int) result.getTotalElements();

        PageResponseDTO<ReplyDTO> pageResponseDTO = new PageResponseDTO<>(
                pageRequestDTO,
                replyDTOList,
                total,
                pageRequestDTO.getSearchType(),
                pageRequestDTO.getKeyword(),
                pageRequestDTO.getLink());

        return pageResponseDTO;
    }

    @Override
    public void updateReply(Long rno, String replyText) {
        Optional<Reply> result = replyRepository.findById(rno);
        if (result.isPresent()) {
            Reply oldReply = result.get();

            oldReply.setReplyText(replyText);
            replyRepository.save(oldReply);
        }
    }

    @Override
    public void removeReply(Long rno) throws NoSuchElementException {
        if (!replyRepository.findById(rno).isPresent()) {
            throw new NoSuchElementException("Reply not found");
        }
        replyRepository.deleteById(rno);
    }
}
