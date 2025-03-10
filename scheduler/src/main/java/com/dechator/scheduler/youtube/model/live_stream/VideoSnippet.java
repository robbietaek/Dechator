package com.dechator.scheduler.youtube.model.live_stream;

import java.io.Serial;
import java.io.Serializable;
import java.util.Map;
import lombok.Data;

@Data
public class VideoSnippet implements Serializable {

  @Serial
  private static final long serialVersionUID = -840723249102828022L;

  private String publishedAt;
  private String channelId;
  private String title;
  private String description;
  private Map<String, Thumbnail> thumbnails;
  private String channelTitle;
  private String categoryId;
  private String liveBroadcastContent;

}
