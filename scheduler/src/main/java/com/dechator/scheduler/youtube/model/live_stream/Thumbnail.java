package com.dechator.scheduler.youtube.model.live_stream;

import java.io.Serial;
import java.io.Serializable;
import lombok.Data;

@Data
public class Thumbnail implements Serializable {

  @Serial
  private static final long serialVersionUID = -2630758879552161803L;

  private String url;
  private Integer width;
  private Integer height;

}
