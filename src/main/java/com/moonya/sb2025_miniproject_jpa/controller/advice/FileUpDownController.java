package com.moonya.sb2025_miniproject_jpa.controller.advice;

import com.moonya.sb2025_miniproject_jpa.dto.UploadFileDTO;
import com.moonya.sb2025_miniproject_jpa.dto.UploadResultDTO;
import com.sun.net.httpserver.Headers;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnailator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Consumer;

@RestController
@Log4j2
public class FileUpDownController {

    @Value("${com.moonya.upload.path}") // application.properties 파일의 key에 대한 값을 얻어와 아래의 변수에 할당
    private String uploadPath;

    @Operation(summary = "Upload POST", description = "Post 방식으로 파일을 업로드")
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<List<UploadResultDTO>> upload(UploadFileDTO uploadFileDTO) {
        log.info("upload : {}", uploadFileDTO);
        log.info("uploadPath : {}", uploadPath);

            if (uploadFileDTO.getFiles() != null && uploadFileDTO.getFiles().size() > 0) {
                List<UploadResultDTO> fileList = new ArrayList<>();
                // 파일이 업로드가 되었다면...
                uploadFileDTO.getFiles().forEach(multipartFile -> {
                    String originalFileName = multipartFile.getOriginalFilename();
                    log.info("uploaded file name : {}", originalFileName);

                    String uuid = UUID.randomUUID().toString();

                    Path savePath = Paths.get(uploadPath, uuid + "_" + originalFileName);
                    Boolean isImage = false;

                    try {
                        multipartFile.transferTo(savePath); // 실제 파일 저장
                        if (Files.probeContentType(savePath).startsWith("image")) { // contentType이 "image"라는 말로 시작한다면..
                            isImage = true;
                            File thumbFile = new File(uploadPath, "s_" + uuid + "_" + originalFileName);
                            Thumbnailator.createThumbnail(savePath.toFile(), thumbFile, 50, 50);
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    fileList.add(UploadResultDTO.builder()
                            .uuid(uuid)
                            .originalFileName(originalFileName)
                            .img(isImage)
                            .build());
                });

                return ResponseEntity.ok(fileList);
            }

        return null;
    }

    @Operation(summary = "view 파일", description = "Get방식으로 첨부 파일 조회")
    @GetMapping("view/{fileName}")
    public ResponseEntity<Resource> viewFileGET(@PathVariable String fileName) {
        Resource resource = new FileSystemResource(uploadPath + File.separator + fileName);

        String resourceName = resource.getFilename();
        HttpHeaders headers = new HttpHeaders();
        List<String> headerValue = new ArrayList<>();
        try {
            headerValue.add(Files.probeContentType(resource.getFile().toPath()));
            headers.put("Content-Type", headerValue);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok().headers(headers).body(resource);
    }

    @Operation(summary = "remove 파일", description = "delete방식으로 업로드 된 파일 삭제")
    @DeleteMapping("/remove/{fileName}")
    public Map<String, Boolean> removeFile(@PathVariable String fileName) {
        Map<String, Boolean> resultMap = new HashMap<>();

        boolean isRemove = false;
        Resource resource = new FileSystemResource(uploadPath + File.separator + fileName);

        try {
            isRemove = resource.getFile().delete();

            if (Files.probeContentType(resource.getFile().toPath()).startsWith("image")){
                File thumbFile = new File(uploadPath + File.separator + "s_" + fileName);
                thumbFile.delete();
            };
        } catch (SecurityException s) {
            log.error(s.getMessage());

            resultMap.put("isRemove", isRemove);
        } catch (IOException e) {
            log.error(e.getMessage());

            resultMap.put("isRemove", isRemove);
        }
        resultMap.put("isRemove", isRemove);

        return  resultMap;
    }
}
