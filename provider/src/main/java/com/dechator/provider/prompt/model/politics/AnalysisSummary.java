package com.dechator.provider.prompt.model.politics;

import java.io.Serial;
import java.io.Serializable;
import lombok.Data;

@Data
public class AnalysisSummary implements Serializable {

  @Serial
  private static final long serialVersionUID = 2770090677735134435L;

  private SentimentAnalysis sentimentAnalysis;

  private IdeologicalBias ideologicalBias;
  
  private String emotionalShift;
}