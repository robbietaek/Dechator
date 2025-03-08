package com.dechator.scheduler.youtube.model.chat;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serial;
import java.io.Serializable;
import lombok.Data;

@Data
public class LiveChatAuthorDetails implements Serializable {

  @Serial
  private static final long serialVersionUID = -5962139958909285026L;

  @JsonProperty("channelId")
  private String channelId;

  @JsonProperty("channelUrl")
  private String channelUrl;

  @JsonProperty("displayName")
  private String displayName;

  @JsonProperty("profileImageUrl")
  private String profileImageUrl;

  @JsonProperty("isVerified")
  private Boolean isVerified;

  @JsonProperty("isChatOwner")
  private Boolean isChatOwner;

  @JsonProperty("isChatSponsor")
  private Boolean isChatSponsor;

  @JsonProperty("isChatModerator")
  private Boolean isChatModerator;

}
