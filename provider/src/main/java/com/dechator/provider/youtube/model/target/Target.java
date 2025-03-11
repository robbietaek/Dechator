package com.dechator.provider.youtube.model.target;

import java.io.Serial;
import java.io.Serializable;
import lombok.Data;

@Data
public class Target implements Serializable {

  @Serial
  private static final long serialVersionUID = 6579716491275836902L;

  private String targetId;

  private String channelId;

  private String liveVideoId;

  private String liveChatId;

  private Boolean isTarget;

}
