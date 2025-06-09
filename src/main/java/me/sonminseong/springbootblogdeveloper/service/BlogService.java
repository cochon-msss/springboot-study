package me.sonminseong.springbootblogdeveloper.service;

import lombok.RequiredArgsConstructor;
import me.sonminseong.springbootblogdeveloper.domain.Article;
import me.sonminseong.springbootblogdeveloper.dto.AddArticleRequest;
import me.sonminseong.springbootblogdeveloper.repository.BlogRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor // final이 붙거다 @NotNull이 붙은 필드fh 생성자 aksemfdjwnsek.
@Service // 빈으로 등록
public class BlogService {

    private final BlogRepository blogRepository;

    // 블로그 글 추가 메서드
    public Article save(AddArticleRequest request){
        return blogRepository.save(request.toEntity());
    }
}
