package com.dechator.scheduler.youtube.service;

import com.dechator.scheduler.youtube.model.channel.ChannelResponse;
import com.dechator.scheduler.youtube.model.chat.LiveChatResponse;
import com.dechator.scheduler.youtube.model.search.SearchResponse;
import com.dechator.scheduler.youtube.model.video.VideoResponse;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@Service
@RequiredArgsConstructor
public class YoutubeGetService {

  private final RestTemplate restTemplate;

  private static String apiKey;

  @Value("${google.apiKey}")
  public void setApiKey(String apiKey) {
    this.apiKey = apiKey;
  }

  public String getChannelIdByHandle(String handle) {
    ChannelResponse channelResponse = new ChannelResponse();
    try {
      URI uri = UriComponentsBuilder
          .fromUriString("https://www.googleapis.com")
          .path("/youtube/v3/search")
          .queryParam("part", "snippet")
          .queryParam("type", "channel")
          .queryParam("q", handle)
          .queryParam("key", apiKey)
          .encode()
          .build()
          .toUri();
      ResponseEntity<ChannelResponse> responseEntity = restTemplate.getForEntity(uri,
          ChannelResponse.class);
      channelResponse = responseEntity.getBody();
    } catch (Exception e) {
      e.printStackTrace();
    }

    if (channelResponse == null || channelResponse.getItems().isEmpty()) {
      return null;
    }

    return channelResponse.getItems().get(0).getId().getChannelId();
  }

  public String getLiveVideoIdByChannelId(String channelId) {
    SearchResponse searchResponse = new SearchResponse();
    try {
      URI uri = UriComponentsBuilder
          .fromUriString("https://www.googleapis.com")
          .path("/youtube/v3/search")
          .queryParam("part", "id")
          .queryParam("channelId", channelId)
          .queryParam("type", "video")
          .queryParam("eventType", "live")
          .queryParam("key", apiKey)
          .encode()
          .build()
          .toUri();
      ResponseEntity<SearchResponse> responseEntity = restTemplate.getForEntity(uri,
          SearchResponse.class);
      searchResponse = responseEntity.getBody();
    } catch (Exception e) {
      e.printStackTrace();
    }

    if (searchResponse.getPageInfo().getTotalResults() == 0) {
      return null;
    }

    return searchResponse.getItems().get(0).getId().getVideoId();
  }

  public String getLiveChatIdByLiveVideoId(String videoId) {
    VideoResponse videoResponse = new VideoResponse();
    try {
      URI uri = UriComponentsBuilder
          .fromUriString("https://www.googleapis.com")
          .path("/youtube/v3/videos")
          .queryParam("id", videoId)
          .queryParam("part", "liveStreamingDetails")
          .queryParam("key", apiKey)
          .encode()
          .build()
          .toUri();
      ResponseEntity<VideoResponse> responseEntity = restTemplate.getForEntity(uri,
          VideoResponse.class);
      videoResponse = responseEntity.getBody();
    } catch (Exception e) {
      e.printStackTrace();
    }

    if (videoResponse.getPageInfo().getTotalResults() == 0) {
      return null;
    }

    return videoResponse.getItems().get(0).getLiveStreamingDetails().getActiveLiveChatId();
  }

  public LiveChatResponse getYoutubeChannelLiveChatListByLiveChatId(String liveChatId) {
    LiveChatResponse liveChatResponse = new LiveChatResponse();
    try {
      URI uri = UriComponentsBuilder
          .fromUriString("https://www.googleapis.com")
          .path("/youtube/v3/liveChat/messages")
          .queryParam("liveChatId", liveChatId)
          .queryParam("part", "snippet,authorDetails")
          .queryParam("key", apiKey)
          .encode()
          .build()
          .toUri();
      ResponseEntity<LiveChatResponse> responseEntity = restTemplate.getForEntity(uri,
          LiveChatResponse.class);
      liveChatResponse = responseEntity.getBody();
    } catch (Exception e) {
      e.printStackTrace();
    }

    if (liveChatResponse.getPageInfo().getTotalResults() == 0) {
      return null;
    }

    return liveChatResponse;
  }

  public LiveChatResponse getYoutubeChannelLiveChatByUserName(String userName) {
    String channelId = getChannelIdByHandle(userName);
    if (channelId == null) {
      return null;
    }

    String liveVideoId = getLiveVideoIdByChannelId(channelId);
    if (liveVideoId == null) {
      return null;
    }

    String liveChatId = getLiveChatIdByLiveVideoId(liveVideoId);
    if (liveChatId == null) {
      return null;
    }

    return getYoutubeChannelLiveChatListByLiveChatId(liveChatId);
  }

}
