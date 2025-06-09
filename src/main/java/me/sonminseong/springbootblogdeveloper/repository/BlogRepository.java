package me.sonminseong.springbootblogdeveloper.repository;

import me.sonminseong.springbootblogdeveloper.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Article,Long> {
}
