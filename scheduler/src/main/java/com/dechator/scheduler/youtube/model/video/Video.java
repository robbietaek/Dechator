package com.dechator.scheduler.youtube.model.video;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serial;
import java.io.Serializable;
import lombok.Data;

@Data
public class Video implements Serializable {

  @Serial
  private static final long serialVersionUID = 6525247054775901199L;

  @JsonProperty("kind")
  private String kind;

  @JsonProperty("etag")
  private String etag;

  @JsonProperty("id")
  private String id;

  @JsonProperty("liveStreamingDetails")
  private LiveStreamingDetails liveStreamingDetails;

}
