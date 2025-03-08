package com.dechator.scheduler.youtube.model.chat;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serial;
import java.io.Serializable;
import lombok.Data;

@Data
public class TextMessageDetails implements Serializable {

  @Serial
  private static final long serialVersionUID = -172841037549962165L;

  @JsonProperty("messageText")
  private String messageText;

}
