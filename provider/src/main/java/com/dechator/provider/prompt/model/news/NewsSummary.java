package com.dechator.provider.prompt.model.news;

import java.io.Serial;
import java.io.Serializable;
import lombok.Data;

@Data
public class NewsSummary implements Serializable {

  @Serial
  private static final long serialVersionUID = -5739386439258395499L;


  private SentimentAnalysis sentimentAnalysis;

  private KeywordsAndIssues keywordsAndIssues;

  private DebatesAndDiscussions debatesAndDiscussions;

  private ChatFrequencyAndParticipation chatFrequencyAndParticipation;

  private ViewerRequestsAndQuestions viewerRequestsAndQuestions;

  private AnomaliesAndOutliers anomaliesAndOutliers;

}
