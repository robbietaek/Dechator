package com.dechator.scheduler.youtube.model.live_stream;

import java.io.Serial;
import java.io.Serializable;
import lombok.Data;

@Data
public class StreamingDetails implements Serializable {

  @Serial
  private static final long serialVersionUID = -8207295747685686853L;

  private String actualStartTime;
  private String scheduledStartTime;
  private String concurrentViewers;
  
}
