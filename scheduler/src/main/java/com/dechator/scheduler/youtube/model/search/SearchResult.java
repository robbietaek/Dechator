package com.dechator.scheduler.youtube.model.search;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serial;
import java.io.Serializable;
import lombok.Data;

@Data
public class SearchResult implements Serializable {

  @Serial
  private static final long serialVersionUID = -6625846807407774147L;

  @JsonProperty("kind")
  private String kind;

  @JsonProperty("etag")
  private String etag;

  @JsonProperty("id")
  private VideoId id;

}
