package com.moonya.sb2025_miniproject_jpa.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UploadResultDTO {
    private String uuid;
    private String originalFileName;
    private Boolean img;

    // getter로 만들면 잭슨바인더가 반환될 때 json으로 함께 반환시켜준다
    public String getLink() {
        String link = "";
      if (img) {
          link = "s_" + uuid + "_" + originalFileName;
      } else {
          link = uuid + "_" + originalFileName;
      }
        return link;
    };
}
