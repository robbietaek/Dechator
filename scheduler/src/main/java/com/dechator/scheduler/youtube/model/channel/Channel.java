package com.dechator.scheduler.youtube.model.channel;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serial;
import java.io.Serializable;
import lombok.Data;

@Data
public class Channel implements Serializable {

  @Serial
  private static final long serialVersionUID = -7950006406497963663L;

  @JsonProperty("id")
  private ChannelId id;

  @JsonProperty("snippet")
  private Snippet snippet;

}
