package com.dechator.provider.prompt.model.politics;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import lombok.Data;

@Data
public class ChatActivity implements Serializable {

  @Serial
  private static final long serialVersionUID = 2237345089677452902L;

  private Integer totalChats;

  private String averageChatsPerSecond;

  private List<String> chatSurgeMoments;

  private List<RepetitiveUserComment> repetitiveUserComments;

}