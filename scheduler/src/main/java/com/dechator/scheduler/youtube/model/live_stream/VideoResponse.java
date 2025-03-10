package com.dechator.scheduler.youtube.model.live_stream;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import lombok.Data;

@Data
public class VideoResponse implements Serializable {

  @Serial
  private static final long serialVersionUID = -7203360713138058676L;

  private String kind;
  private String etag;
  private List<VideoItem> items;
  private PageInfo pageInfo;
}
