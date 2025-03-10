package com.dechator.scheduler.youtube.model.live_stream;

import java.io.Serial;
import java.io.Serializable;
import lombok.Data;

@Data
public class PageInfo implements Serializable {

  @Serial
  private static final long serialVersionUID = -2886167241062306530L;

  private Integer totalResults;
  private Integer resultsPerPage;
  
}
