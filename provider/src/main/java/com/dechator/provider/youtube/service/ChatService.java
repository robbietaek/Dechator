package com.dechator.provider.youtube.service;

import com.dechator.provider.ai.gemini.service.GeminiService;
import com.dechator.provider.prompt.model.news.NewsSummary;
import com.dechator.provider.prompt.model.politics.PoliticsSummary;
import com.dechator.provider.youtube.dao.snippet.SnippetDao;
import com.dechator.provider.youtube.dao.target.TargetDao;
import com.dechator.provider.youtube.model.snippet.Snippet;
import com.dechator.provider.youtube.model.target.Target;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatService {

  private final GeminiService geminiService;
  private final SnippetDao snippetDao;
  private final TargetDao targetDao;

  public Map<String, NewsSummary> getNewsChatSummaryList() {
    Map<String, NewsSummary> map = new HashMap<>();
    List<Target> targetList = targetDao.selectTargetList();

    targetList.parallelStream().forEach(target -> {
      List<Snippet> snippetList = snippetDao.selectSnippetListByTargetId(target.getTargetId());
      map.put(target.getTargetId(), geminiService.getGeminiNewsResponse(target.getTargetId(),
          snippetList.stream()
              .map(snippet -> snippet.getPublishedAt() + " " + snippet.getDisplayMessage() + "\n")
              .collect(
                  Collectors.toList())));
    });

    return map;
  }

  public Map<String, NewsSummary> getNewsChatSummaryByTargetId(String targetId) {
    Map<String, NewsSummary> map = new HashMap<>();
    Target target = targetDao.selectTargetByTargetId(targetId);

    List<Snippet> snippetList = snippetDao.selectSnippetListByTargetId(target.getTargetId());
    map.put(target.getTargetId(), geminiService.getGeminiNewsResponse(target.getTargetId(),
        snippetList.stream()
            .map(snippet -> snippet.getPublishedAt() + " " + snippet.getDisplayMessage() + "\n")
            .collect(
                Collectors.toList())));

    return map;
  }

  public Map<String, PoliticsSummary> getPoliticsChatSummaryList() {
    Map<String, PoliticsSummary> map = new HashMap<>();
    List<Target> targetList = targetDao.selectTargetList();

    targetList.parallelStream().forEach(target -> {
      List<Snippet> snippetList = snippetDao.selectSnippetListByTargetId(target.getTargetId());
      map.put(target.getTargetId(), geminiService.getGeminiPoliticsResponse(target.getTargetId(),
          snippetList.stream()
              .map(snippet -> snippet.getPublishedAt() + " " + snippet.getDisplayMessage() + "\n")
              .collect(
                  Collectors.toList())));
    });

    return map;
  }

  public Map<String, PoliticsSummary> getPoliticsChatSummaryByTargetId(String targetId) {
    Map<String, PoliticsSummary> map = new HashMap<>();
    Target target = targetDao.selectTargetByTargetId(targetId);

    List<Snippet> snippetList = snippetDao.selectSnippetListByTargetId(target.getTargetId());
    map.put(target.getTargetId(), geminiService.getGeminiPoliticsResponse(target.getTargetId(),
        snippetList.stream()
            .map(snippet -> snippet.getPublishedAt() + " " + snippet.getDisplayMessage() + "\n")
            .collect(
                Collectors.toList())));

    return map;
  }

}
