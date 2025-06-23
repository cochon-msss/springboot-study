package me.sonminseong.springbootblogdeveloper.controller;

import lombok.RequiredArgsConstructor;
import me.sonminseong.springbootblogdeveloper.domain.Article;
import me.sonminseong.springbootblogdeveloper.dto.ArticleListViewResponse;
import me.sonminseong.springbootblogdeveloper.dto.ArticleViewResponse;
import me.sonminseong.springbootblogdeveloper.service.BlogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BlogViewController {

    private final BlogService blogService;

    // 목록 조회
    @GetMapping("/articles")
    public String getArticles(Model model){
        // 전체 데이터를 조회한 걸 List<entity>로 repository에서 return 받아서
        // 거이서 필요한 데이터만 ArticleListViewReponse로 받아서 view로 넘긴다.
        // 그렇기 때문에 ArticleListViewResponse생성자에는 entiry를 받아서 생성하는 생성자가 있다.
        List<ArticleListViewResponse> articles = blogService.findAll().stream()
                .map(ArticleListViewResponse::new)
                .toList();
        model.addAttribute("articles",articles); // 블로그 글 리스트 저장
        return "articleList";
    }

    // 블로그 상세 내용 조회
    @GetMapping("/articles/{id}")
    public String getArticle(@PathVariable Long id, Model model){
        // 한개의 row롤 entity로 return받고
        // view에 전달하기 위해 articleViewResponse생성자에 entity로 생성후 return
        Article article = blogService.findById(id);
        model.addAttribute("article", new ArticleViewResponse(article));

        return "article";
    }

    // 수정, 생성
    @GetMapping("/new-article")
     // id 키를 가진 쿼리 파라미어틔 값을 id 변수에 매핑(id는 없을 수도 있음)
    public String newArticle(@RequestParam(required = false) Long id, Model model){
        if(id == null){ // id가 없으면 생성
            model.addAttribute("article", new ArticleViewResponse());
        }else{ // id가 있으면 수정
            Article article = blogService.findById(id);
            model.addAttribute("article",new ArticleViewResponse(article));
        }
        return "newArticle";
    }

}
