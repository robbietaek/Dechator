package com.dechator.provider.prompt.model.politics;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import lombok.Data;

@Data
public class Anomalies implements Serializable {

  @Serial
  private static final long serialVersionUID = 3507550731721624708L;

  private List<String> highlyEmotionalChats;

  private List<String> suspectedBotActivity;

  private List<String> fakeNews;

}