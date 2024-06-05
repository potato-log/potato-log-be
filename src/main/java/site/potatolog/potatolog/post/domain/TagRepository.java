package site.potatolog.potatolog.post.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag, Long> {
  Optional<Tag> findByContent(String content);
}
