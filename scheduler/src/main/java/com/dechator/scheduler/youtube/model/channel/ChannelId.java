package com.dechator.scheduler.youtube.model.channel;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serial;
import java.io.Serializable;
import lombok.Data;

@Data
public class ChannelId implements Serializable {

  @Serial
  private static final long serialVersionUID = 312103803137916165L;

  @JsonProperty("kind")
  private String kind;

  @JsonProperty("channelId")
  private String channelId;

}
