package com.dechator.scheduler.youtube.model.video;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import lombok.Data;

@Data
public class VideoResponse implements Serializable {

  @Serial
  private static final long serialVersionUID = -2956536557430158229L;

  @JsonProperty("kind")
  private String kind;

  @JsonProperty("etag")
  private String etag;

  @JsonProperty("items")
  private List<Video> items;

  @JsonProperty("pageInfo")
  private PageInfo pageInfo;

}
