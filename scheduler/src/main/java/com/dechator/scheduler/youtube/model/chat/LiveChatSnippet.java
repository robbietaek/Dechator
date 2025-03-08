package com.dechator.scheduler.youtube.model.chat;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serial;
import java.io.Serializable;
import lombok.Data;

@Data
public class LiveChatSnippet implements Serializable {

  @Serial
  private static final long serialVersionUID = -879951491036934008L;

  @JsonProperty("type")
  private String type;

  @JsonProperty("liveChatId")
  private String liveChatId;

  @JsonProperty("authorChannelId")
  private String authorChannelId;

  @JsonProperty("publishedAt")
  private String publishedAt;

  @JsonProperty("hasDisplayContent")
  private Boolean hasDisplayContent;

  @JsonProperty("displayMessage")
  private String displayMessage;

  @JsonProperty("textMessageDetails")
  private TextMessageDetails textMessageDetails;
}
