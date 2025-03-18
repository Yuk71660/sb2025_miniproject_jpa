package com.moonya.sb2025_miniproject_jpa.dto;

import lombok.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PageRequestDTO {
    @Builder.Default // 빌더패턴으로 객체 만들 때 디폴트값 주려면 필요함
    private int page = 1, size = 10;

    private String searchType, keyword;
    private String link;

//    public String[] getSearchTypes() {
//
////        searchType.split("");
//        if (searchType == null || searchType.isEmpty()){
//            return null;
//        } else {
//            char[] charArray = searchType.toCharArray();
//            String[] searchTypes = new String[charArray.length];
//
//            for (int i = 0; i < charArray.length; i++) {
//                searchTypes[i] = String.valueOf(charArray[i]);
//            }
//
//            return searchTypes;
//        }
//
//    };

    public String[] getSearchTypes() {
        if (searchType == null || searchType.isEmpty()) {
            return null;
        } else {
            return searchType.split("");
        }
    }

    public Pageable getPageable(String...props) {
        return PageRequest.of(this.page -1, this.size, Sort.by(props).descending());
    }

    public String getLink(){
        if (link == null){
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("page=" + this.page);
            stringBuilder.append("&size=" + this.size);
            if (searchType != null && searchType.length() > 0) {
            stringBuilder.append("&serchType=" + this.searchType);
            }
            if (keyword != null && keyword.length() > 0) {

                try {
                    stringBuilder.append("&keyword=" + URLEncoder.encode(keyword,"UTF-8"));
                } catch (UnsupportedEncodingException e) {
                    throw new RuntimeException(e);
                }
            }
            this.link = stringBuilder.toString();
        }
        return  this.link;
    }
}
