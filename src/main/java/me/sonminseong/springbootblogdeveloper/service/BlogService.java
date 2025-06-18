package me.sonminseong.springbootblogdeveloper.service;

import lombok.RequiredArgsConstructor;
import me.sonminseong.springbootblogdeveloper.domain.Article;
import me.sonminseong.springbootblogdeveloper.dto.AddArticleRequest;
import me.sonminseong.springbootblogdeveloper.dto.UpdateArticleRequest;
import me.sonminseong.springbootblogdeveloper.repository.BlogRepository;
import org.hibernate.sql.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor // final이 붙거다 @NotNull이 붙은 필드fh 생성자 aksemfdjwnsek.
@Service // 빈으로 등록
public class BlogService {

    private final BlogRepository blogRepository;

    // 블로그 글 추가 메서드
    public Article save(AddArticleRequest request, String userName){
        return blogRepository.save(request.toEntity(userName));
    }

    // 저장되어 있는 블로그 글 조회
    public List<Article> findAll(){
        return blogRepository.findAll();
    }

    // 글 하나 조회
    public Article findById(long id){
        return blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found: "+id));
        /**
         * findById() 메서드를 사용해 id를 받아 엔티티를 조회하고 없으면
         * IllegalArgumentException 예외를 발생합니다.
         */
    }

    // 글 삭제
    public void delete(long id){
        blogRepository.deleteById(id);
    }

    // 글 수정
    @Transactional
    public Article update(long id, UpdateArticleRequest request){
        Article article = blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found: "+id));

        article.update(request.getTitle(), request.getContent());

        return article;
    }
}
