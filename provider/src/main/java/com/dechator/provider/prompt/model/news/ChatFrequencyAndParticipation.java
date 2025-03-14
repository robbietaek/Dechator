package com.dechator.provider.prompt.model.news;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import lombok.Data;

@Data
public class ChatFrequencyAndParticipation implements Serializable {

  @Serial
  private static final long serialVersionUID = -2829372255729594968L;

  private String totalChatCount;

  private String averageChatRate;

  private String repetitiveUsers;

  private List<String> popularMessages;

}