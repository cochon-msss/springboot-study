package me.sonminseong.springbootblogdeveloper.dto;

import lombok.Getter;
import me.sonminseong.springbootblogdeveloper.domain.Article;

// 응답을 위한 dto
@Getter
public class ArticleResponse {

    private final String title;
    private final String content;

    public ArticleResponse(Article article){
        this.title = article.getTitle();
        this.content = article.getContent();
    }
}
