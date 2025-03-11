package com.dechator.scheduler.youtube.service;

import com.dechator.scheduler.youtube.access.ApiKey;
import com.dechator.scheduler.youtube.dao.target.TargetDao;
import com.dechator.scheduler.youtube.model.channel.ChannelResponse;
import com.dechator.scheduler.youtube.model.chat.LiveChatResponse;
import com.dechator.scheduler.youtube.model.live_stream.VideoItem;
import com.dechator.scheduler.youtube.model.live_stream.VideoResponse;
import com.dechator.scheduler.youtube.model.search.SearchResponse;
import com.dechator.scheduler.youtube.model.target.Target;
import java.net.URI;
import java.util.Comparator;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@Service
@RequiredArgsConstructor
public class YoutubeGetService {

  private final RestTemplate restTemplate;
  private final TargetDao targetDao;

  public String getChannelIdByHandle(String handle) {
    ChannelResponse channelResponse = new ChannelResponse();
    try {
      URI uri = UriComponentsBuilder
          .fromUriString("https://www.googleapis.com")
          .path("/youtube/v3/channels")
          .queryParam("part", "id")
          .queryParam("forHandle", handle)
          .queryParam("key", ApiKey.getRandomApiKey())
          .encode()
          .build()
          .toUri();
      ResponseEntity<ChannelResponse> responseEntity = restTemplate.getForEntity(uri,
          ChannelResponse.class);
      channelResponse = responseEntity.getBody();
    } catch (Exception e) {
      log.error(e.getMessage());
      return null;
    }

    if (channelResponse == null || channelResponse.getPageInfo().getTotalResults() == 0) {
      return null;
    }

    return channelResponse.getItems().get(0).getId();
  }

  public String getLiveVideoIdByChannelId(String channelId) {
    SearchResponse searchResponse = new SearchResponse();
    try {
      URI searchUri = UriComponentsBuilder
          .fromUriString("https://www.googleapis.com")
          .path("/youtube/v3/search")
          .queryParam("part", "id")
          .queryParam("channelId", channelId)
          .queryParam("type", "video")
          .queryParam("eventType", "live")
          .queryParam("key", ApiKey.getRandomApiKey())
          .encode()
          .build()
          .toUri();
      ResponseEntity<SearchResponse> responseEntity = restTemplate.getForEntity(searchUri,
          SearchResponse.class);
      searchResponse = responseEntity.getBody();
    } catch (Exception e) {
      log.error(e.getMessage());
      return null;
    }

    if (searchResponse.getPageInfo().getTotalResults() == 0) {
      return null;
    }

    VideoResponse videoResponse = new VideoResponse();
    try {
      URI videosUri = UriComponentsBuilder
          .fromUriString("https://www.googleapis.com")
          .path("/youtube/v3/videos")
          .queryParam("part", "snippet,liveStreamingDetails")
          .queryParam("id",
              String.join(",",
                  searchResponse.getItems().stream().map(item -> item.getId().getVideoId()).collect(
                      Collectors.toList())))
          .queryParam("key", ApiKey.getRandomApiKey())
          .encode()
          .build()
          .toUri();
      ResponseEntity<VideoResponse> responseEntity = restTemplate.getForEntity(videosUri,
          VideoResponse.class);
      videoResponse = responseEntity.getBody();
    } catch (Exception e) {
      log.error(e.getMessage());
      return null;
    }

    return videoResponse.getItems().stream()
        .sorted(Comparator.comparingLong(this::getViewerCount).reversed())
        .collect(Collectors.toList()).get(0).getId();
  }

  private long getViewerCount(VideoItem item) {
    if (item.getLiveStreamingDetails() != null
        && item.getLiveStreamingDetails().getConcurrentViewers() != null) {
      return Long.parseLong(item.getLiveStreamingDetails().getConcurrentViewers());
    }
    return 0;
  }

  public String getLiveChatIdByLiveVideoId(String videoId) {
    com.dechator.scheduler.youtube.model.video.VideoResponse videoResponse = new com.dechator.scheduler.youtube.model.video.VideoResponse();
    try {
      URI uri = UriComponentsBuilder
          .fromUriString("https://www.googleapis.com")
          .path("/youtube/v3/videos")
          .queryParam("id", videoId)
          .queryParam("part", "liveStreamingDetails")
          .queryParam("key", ApiKey.getRandomApiKey())
          .encode()
          .build()
          .toUri();
      ResponseEntity<com.dechator.scheduler.youtube.model.video.VideoResponse> responseEntity = restTemplate.getForEntity(
          uri,
          com.dechator.scheduler.youtube.model.video.VideoResponse.class);
      videoResponse = responseEntity.getBody();
    } catch (Exception e) {
      log.error(e.getMessage());
      return null;
    }

    if (videoResponse.getPageInfo().getTotalResults() == 0) {
      return null;
    }

    return videoResponse.getItems().get(0).getLiveStreamingDetails().getActiveLiveChatId();
  }

  public LiveChatResponse getYoutubeChannelLiveChatListByLiveChatId(String liveChatId,
      String targetId) {
    LiveChatResponse liveChatResponse = new LiveChatResponse();
    try {
      URI uri = UriComponentsBuilder
          .fromUriString("https://www.googleapis.com")
          .path("/youtube/v3/liveChat/messages")
          .queryParam("liveChatId", liveChatId)
          .queryParam("part", "snippet,authorDetails")
          .queryParam("key", ApiKey.getRandomApiKey())
          .encode()
          .build()
          .toUri();
      ResponseEntity<LiveChatResponse> responseEntity = restTemplate.getForEntity(uri,
          LiveChatResponse.class);
      liveChatResponse = responseEntity.getBody();
    } catch (Exception e) {
      log.error(e.getMessage());
      targetDao.upsertTarget(Target.builder().targetId(targetId).build());
      return null;
    }

    if (liveChatResponse == null || liveChatResponse.getPageInfo().getTotalResults() == 0) {
      return null;
    }

    return liveChatResponse;
  }

}
