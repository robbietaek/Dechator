package com.dechator.scheduler.youtube.model.live_stream;

import java.io.Serial;
import java.io.Serializable;
import lombok.Data;

@Data
public class VideoItem implements Serializable {

  @Serial
  private static final long serialVersionUID = -2370629912185425645L;

  private String kind;
  private String etag;
  private String id;
  private VideoSnippet snippet;
  private StreamingDetails liveStreamingDetails;

}
