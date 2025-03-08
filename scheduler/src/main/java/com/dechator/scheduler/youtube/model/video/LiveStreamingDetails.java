package com.dechator.scheduler.youtube.model.video;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serial;
import java.io.Serializable;
import lombok.Data;

@Data
public class LiveStreamingDetails implements Serializable {

  @Serial
  private static final long serialVersionUID = 8174228173767958249L;

  @JsonProperty("actualStartTime")
  private String actualStartTime;

  @JsonProperty("scheduledStartTime")
  private String scheduledStartTime;

  @JsonProperty("concurrentViewers")
  private String concurrentViewers;

  @JsonProperty("activeLiveChatId")
  private String activeLiveChatId;

}
