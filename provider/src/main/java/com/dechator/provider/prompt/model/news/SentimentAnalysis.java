package com.dechator.provider.prompt.model.news;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import lombok.Data;

@Data
public class SentimentAnalysis implements Serializable {

  @Serial
  private static final long serialVersionUID = -3772616301857077321L;

  private String positiveRatio;

  private String negativeRatio;

  private String neutralRatio;

  private String sentimentChanges;

  private List<String> representativeMessages;

}