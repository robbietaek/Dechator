package com.dechator.provider.prompt.model.politics;

import java.io.Serial;
import java.io.Serializable;
import lombok.Data;

@Data
public class SentimentAnalysis implements Serializable {

  @Serial
  private static final long serialVersionUID = -5064912954423627800L;

  private Double positiveRatio;

  private Double negativeRatio;
  
  private Double neutralRatio;
}