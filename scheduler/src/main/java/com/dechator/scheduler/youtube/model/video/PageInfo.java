package com.dechator.scheduler.youtube.model.video;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serial;
import java.io.Serializable;
import lombok.Data;

@Data
public class PageInfo implements Serializable {

  @Serial
  private static final long serialVersionUID = -4692244425151671940L;

  @JsonProperty("totalResults")
  private int totalResults;

  @JsonProperty("resultsPerPage")
  private int resultsPerPage;

}
