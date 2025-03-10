package com.dechator.scheduler.youtube.model.channel;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import lombok.Data;

@Data
public class ChannelResponse implements Serializable {

  @Serial
  private static final long serialVersionUID = -5375003471799306136L;

  @JsonProperty("kind")
  private String kind;

  @JsonProperty("etag")
  private String etag;

  @JsonProperty("pageInfo")
  private PageInfo pageInfo;

  @JsonProperty("items")
  private List<ChannelItem> items;

}
