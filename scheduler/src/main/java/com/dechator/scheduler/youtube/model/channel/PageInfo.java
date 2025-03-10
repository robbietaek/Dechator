package com.dechator.scheduler.youtube.model.channel;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serial;
import java.io.Serializable;
import lombok.Data;

@Data
public class PageInfo implements Serializable {

  @Serial
  private static final long serialVersionUID = 1350731872030036743L;

  @JsonProperty("totalResults")
  private int totalResults;

  @JsonProperty("resultsPerPage")
  private int resultsPerPage;

}
