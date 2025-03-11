package com.dechator.scheduler.youtube;

import com.dechator.scheduler.youtube.dao.target.TargetDao;
import com.dechator.scheduler.youtube.model.author.Author;
import com.dechator.scheduler.youtube.model.chat.LiveChatMessage;
import com.dechator.scheduler.youtube.model.chat.LiveChatResponse;
import com.dechator.scheduler.youtube.model.snippet.Snippet;
import com.dechator.scheduler.youtube.model.target.Target;
import com.dechator.scheduler.youtube.service.BulkInsertService;
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
import org.springframework.util.ObjectUtils;

@Slf4j
@Service
@RequiredArgsConstructor
public class LiveChatSaveScheduler {

  private final YoutubeGetService youtubeGetService;
  private final BulkInsertService bulkInsertService;
  private final TargetDao targetDao;
  private ObjectMapper objectMapper = new ObjectMapper();

  @Scheduled(fixedDelay = 30000)
  public void saveLiveChatMessages() {
    List<Target> targetList = targetDao.selectTargetList();

    for (Target target : targetList) {
      if (target.getChannelId() == null || target.getLiveVideoId() == null
          || target.getLiveChatId() == null) {
        String targetId = target.getTargetId();

        String channelId = youtubeGetService.getChannelIdByHandle(targetId);
        if (channelId == null) {
          continue;
        }
        target.setChannelId(channelId);

        String liveVideoId = youtubeGetService.getLiveVideoIdByChannelId(channelId);
        if (liveVideoId == null) {
          targetDao.upsertTarget(Target.builder().targetId(targetId).channelId(channelId).build());
          continue;
        }
        target.setLiveVideoId(liveVideoId);

        String liveChatId = youtubeGetService.getLiveChatIdByLiveVideoId(liveVideoId);
        if (liveChatId == null) {
          continue;
        }
        target.setLiveChatId(liveChatId);

        targetDao.upsertTarget(
            Target.builder().targetId(targetId).channelId(channelId).liveVideoId(liveVideoId)
                .liveChatId(liveChatId)
                .build());
      }

      LiveChatResponse liveChatResponse = youtubeGetService.getYoutubeChannelLiveChatListByLiveChatId(
          target.getLiveChatId(), target.getTargetId());

      if (ObjectUtils.isEmpty(liveChatResponse)) {
        continue;
      }

      List<Author> authorList = liveChatResponse.getItems().stream()
          .map(item -> objectMapper.convertValue(item.getAuthorDetails(), Author.class))
          .collect(Collectors.toList());
      bulkInsertService.bulkInsertAuthorList(authorList);

      List<Snippet> snippetsList = new ArrayList<>();
      for (LiveChatMessage liveChatMessage : liveChatResponse.getItems()) {
        OffsetDateTime publishedAt = OffsetDateTime.parse(
            liveChatMessage.getSnippet().getPublishedAt());

        snippetsList.add(
            Snippet.builder().id(liveChatMessage.getId()).targetId(target.getTargetId())
                .type(liveChatMessage.getSnippet().getType())
                .authorChannelId(liveChatMessage.getSnippet().getAuthorChannelId())
                .publishedAt(publishedAt)
                .hasDisplayContent(liveChatMessage.getSnippet().getHasDisplayContent())
                .displayMessage(
                    liveChatMessage.getSnippet().getTextMessageDetails().getMessageText()).build());
      }

      bulkInsertService.bulkInsertSnippetList(snippetsList);
    }
  }

  @Scheduled(fixedRate = 108000000)
  public void updateTargetByMostConcurrentViewers() {
    List<Target> targetList = targetDao.selectTargetList();

    for (Target target : targetList) {
      String targetId = target.getTargetId();

      String channelId = youtubeGetService.getChannelIdByHandle(targetId);
      if (channelId == null) {
        continue;
      }
      target.setChannelId(channelId);

      String liveVideoId = youtubeGetService.getLiveVideoIdByChannelId(channelId);
      if (liveVideoId == null) {
        targetDao.upsertTarget(Target.builder().targetId(targetId).channelId(channelId).build());
        continue;
      }
      target.setLiveVideoId(liveVideoId);

      String liveChatId = youtubeGetService.getLiveChatIdByLiveVideoId(liveVideoId);
      if (liveChatId == null) {
        continue;
      }
      target.setLiveChatId(liveChatId);

      targetDao.upsertTarget(
          Target.builder().targetId(targetId).channelId(channelId).liveVideoId(liveVideoId)
              .liveChatId(liveChatId)
              .build());
    }
  }

}
