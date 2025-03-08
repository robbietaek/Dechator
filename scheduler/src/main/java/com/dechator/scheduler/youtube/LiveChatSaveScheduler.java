package com.dechator.scheduler.youtube;

import com.dechator.scheduler.youtube.entity.author.Author;
import com.dechator.scheduler.youtube.entity.author.AuthorRepository;
import com.dechator.scheduler.youtube.entity.snippet.Snippet;
import com.dechator.scheduler.youtube.entity.snippet.SnippetRepository;
import com.dechator.scheduler.youtube.entity.target.Target;
import com.dechator.scheduler.youtube.entity.target.TargetRepository;
import com.dechator.scheduler.youtube.model.chat.LiveChatMessage;
import com.dechator.scheduler.youtube.model.chat.LiveChatResponse;
import com.dechator.scheduler.youtube.service.YoutubeGetService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class LiveChatSaveScheduler {

  private final YoutubeGetService youtubeGetService;
  private final AuthorRepository authorRepository;
  private final TargetRepository targetRepository;
  private final SnippetRepository snippetRepository;
  private ObjectMapper objectMapper = new ObjectMapper();

  @Scheduled(fixedDelay = 20000)
  public void saveLiveChatMessages() {
    List<Target> targetList = targetRepository.findAll();

    for (Target target : targetList) {
      if (target.getChannelId() == null || target.getLiveVideoId() == null
          || target.getLiveChatId() == null) {
        String targetId = target.getTargetId();

        String channelId = youtubeGetService.getChannelIdByHandle(targetId);
        if (channelId == null) {
          continue;
        }

        String liveVideoId = youtubeGetService.getLiveVideoIdByChannelId(channelId);
        if (liveVideoId == null) {
          continue;
        }

        String liveChatId = youtubeGetService.getLiveChatIdByLiveVideoId(liveVideoId);
        if (liveChatId == null) {
          continue;
        }

        target.updateTarget(targetId, channelId, liveVideoId, liveChatId);
        targetRepository.save(target);
      }

      LiveChatResponse liveChatResponse = youtubeGetService.getYoutubeChannelLiveChatListByLiveChatId(
          target.getLiveChatId());

      List<Author> authorList = liveChatResponse.getItems().stream()
          .map(item -> objectMapper.convertValue(item.getAuthorDetails(), Author.class))
          .collect(Collectors.toList());
      authorRepository.saveAll(authorList);

      List<Snippet> snippetsList = new ArrayList<>();
      for (LiveChatMessage liveChatMessage : liveChatResponse.getItems()) {
        OffsetDateTime publishedAt = OffsetDateTime.parse(
            liveChatMessage.getSnippet().getPublishedAt());

        snippetsList.add(new Snippet(liveChatMessage.getId(), target.getTargetId(),
            liveChatMessage.getSnippet().getType(),
            liveChatMessage.getSnippet().getAuthorChannelId(),
            publishedAt,
            liveChatMessage.getSnippet().getHasDisplayContent(),
            liveChatMessage.getSnippet().getDisplayMessage()));
      }

      snippetRepository.saveAll(snippetsList);
    }
    log.info("FINISH");
  }

}
