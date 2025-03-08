package com.dechator.scheduler.youtube.model.chat;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;

@Data
public class PageInfo implements Serializable {

  @JsonProperty("totalResults")
  private int totalResults;

  @JsonProperty("resultsPerPage")
  private int resultsPerPage;

}
