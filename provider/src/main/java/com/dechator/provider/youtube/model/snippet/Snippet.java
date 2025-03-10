package com.dechator.provider.youtube.model.snippet;

import java.io.Serial;
import java.io.Serializable;
import java.time.OffsetDateTime;
import lombok.Data;

@Data
public class Snippet implements Serializable {

  @Serial
  private static final long serialVersionUID = 3216968339203956437L;

  private String id;

  private String targetId;

  private String type;

  private String authorChannelId;

  private OffsetDateTime publishedAt;

  private Boolean hasDisplayContent;

  private String displayMessage;

}