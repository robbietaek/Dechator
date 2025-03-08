package com.dechator.scheduler.youtube.model.search;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serial;
import java.io.Serializable;
import lombok.Data;

@Data
public class VideoId implements Serializable {

  @Serial
  private static final long serialVersionUID = -9159373886061788423L;

  @JsonProperty("kind")
  private String kind;

  @JsonProperty("videoId")
  private String videoId;

}
