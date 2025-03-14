package com.dechator.provider.prompt.model.politics;

import java.io.Serial;
import java.io.Serializable;
import lombok.Data;

@Data
public class PoliticsSummary implements Serializable {

  @Serial
  private static final long serialVersionUID = -8026217927667480522L;

  private AnalysisSummary analysisSummary;

  private KeywordsAndIssues keywordsAndIssues;

  private ArgumentsAndReactions argumentsAndReactions;

  private ChatActivity chatActivity;

  private ViewerRequests viewerRequests;

  private Anomalies anomalies;

}