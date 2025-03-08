package com.dechator.scheduler.youtube.model.chat;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serial;
import java.io.Serializable;
import lombok.Data;

@Data
public class LiveChatMessage implements Serializable {

  @Serial
  private static final long serialVersionUID = 2830275236473645045L;

  @JsonProperty("kind")
  private String kind;

  @JsonProperty("etag")
  private String etag;

  @JsonProperty("id")
  private String id;

  @JsonProperty("snippet")
  private LiveChatSnippet snippet;

  @JsonProperty("authorDetails")
  private LiveChatAuthorDetails authorDetails;

}
