package com.dechator.scheduler.youtube.model.channel;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serial;
import java.io.Serializable;
import lombok.Data;

@Data
public class ChannelItem implements Serializable {

  @Serial
  private static final long serialVersionUID = 7587870041766545581L;

  @JsonProperty("kind")
  private String kind;

  @JsonProperty("etag")
  private String etag;

  @JsonProperty("id")
  private String id;

}
