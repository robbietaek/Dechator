package com.dechator.scheduler.youtube.model.chat;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import lombok.Data;

@Data
public class LiveChatResponse implements Serializable {

  @Serial
  private static final long serialVersionUID = -7547505103545886881L;

  @JsonProperty("kind")
  private String kind;

  @JsonProperty("etag")
  private String etag;

  @JsonProperty("pollingIntervalMillis")
  private int pollingIntervalMillis;

  @JsonProperty("pageInfo")
  private PageInfo pageInfo;

  @JsonProperty("nextPageToken")
  private String nextPageToken;

  @JsonProperty("items")
  private List<LiveChatMessage> items;

}
