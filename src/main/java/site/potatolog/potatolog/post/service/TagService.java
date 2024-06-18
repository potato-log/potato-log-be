package site.potatolog.potatolog.post.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.potatolog.potatolog.post.domain.Tag;
import site.potatolog.potatolog.post.domain.TagRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TagService {

  private final TagRepository tagRepository;

  @Transactional
  public List<Tag> createTags(List<String> tagContents) {
    return tagContents.stream()
            .map(tagContent -> tagRepository.findByContent(tagContent)
                    .orElseGet(() -> tagRepository.save(Tag.builder().content(tagContent).build())))
            .collect(Collectors.toList());
  }
}
