package me.sonminseong.springbootblogdeveloper.config.error.execption;

import me.sonminseong.springbootblogdeveloper.config.error.ErrorCode;

public class ArticleNotFoundException extends NotFoundException{
    public ArticleNotFoundException(){
        super(ErrorCode.ARTICLE_NOT_FOUND);
    }
}
