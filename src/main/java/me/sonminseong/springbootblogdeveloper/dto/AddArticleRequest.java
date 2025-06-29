package me.sonminseong.springbootblogdeveloper.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import me.sonminseong.springbootblogdeveloper.domain.Article;

@NoArgsConstructor // 기본 생성자 추가
@AllArgsConstructor // 모든 필드 값을 파라미터로 받는 생성자 추가
@Getter
public class AddArticleRequest {
    
    @NotNull
    @Size(min =  1,max = 10)
    private String title;

    @NotNull
    private String content;

    /**
     * toEntity()는 빌더 패턴을 사용해 DTO를 엔티티로 만들어주는 메서드입니다.
     * 추후에 블로그 글을 추가할 때 저장할 엔티티로 변환하는 용도로 사용
     * @return
     */
    public Article toEntity(String author){ // 생성자를 사용해 객체 생성
        return Article.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
}
