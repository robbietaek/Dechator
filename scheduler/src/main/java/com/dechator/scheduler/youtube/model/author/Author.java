package com.dechator.scheduler.youtube.model.author;

import java.io.Serial;
import java.io.Serializable;
import lombok.Data;

@Data
public class Author implements Serializable {

  @Serial
  private static final long serialVersionUID = 8726740072857967948L;

  private String channelId;

  private String channelUrl;

  private String displayName;

  private String profileImageUrl;

  private Boolean isVerified;

  private Boolean isChatOwner;

  private Boolean isChatSponsor;

  private Boolean isChatModerator;

}