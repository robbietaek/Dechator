package com.dechator.scheduler.youtube.model.channel;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serial;
import java.io.Serializable;
import lombok.Data;

@Data
public class Snippet implements Serializable {

  @Serial
  private static final long serialVersionUID = 2232532042708149831L;

  @JsonProperty("title")
  private String title;

  @JsonProperty("customUrl")
  private String customUrl;

  @JsonProperty("description")
  private String description;
  
}
