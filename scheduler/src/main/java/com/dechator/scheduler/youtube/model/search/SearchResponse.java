package com.dechator.scheduler.youtube.model.search;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import lombok.Data;

@Data
public class SearchResponse implements Serializable {

  @Serial
  private static final long serialVersionUID = 3888283652450825388L;

  @JsonProperty("kind")
  private String kind;

  @JsonProperty("etag")
  private String etag;

  @JsonProperty("regionCode")
  private String regionCode;

  @JsonProperty("pageInfo")
  private PageInfo pageInfo;

  @JsonProperty("items")
  private List<SearchResult> items;

}
