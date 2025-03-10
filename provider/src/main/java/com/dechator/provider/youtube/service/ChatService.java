package com.dechator.provider.youtube.service;

import com.dechator.provider.youtube.dao.snippet.SnippetDao;
import com.dechator.provider.youtube.dao.target.TargetDao;
import com.dechator.provider.youtube.model.snippet.Snippet;
import com.dechator.provider.youtube.model.target.Target;
import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatService {

  private static String geminiApiKey;

  @Value("${google.gemimiApiKey}")
  public void setGeminiApiKey(String geminiApiKey) {
    this.geminiApiKey = geminiApiKey;
  }

  private final SnippetDao snippetDao;
  private final TargetDao targetDao;

  public Map<String, String> getAllChatSummary() {
    Map<String, String> map = new HashMap<>();
    Client client = Client.builder().apiKey(geminiApiKey).build();
    List<Target> targetList = targetDao.selectTargetList();

    targetList.parallelStream().forEach(target -> {
      List<Snippet> snippetList = snippetDao.selectSnippetListByTargetId(target.getTargetId());
      try {
        GenerateContentResponse response =
            client.models.generateContent("gemini-2.0-flash-001", String.format(
                    "이 글은 유튜브 핸들 %s 에서 수집한 채팅들이야. 이 글들을 요약해줘. \"%s\"",
                    target.getTargetId(), String.join(", ",
                        snippetList.stream().map(snippet -> snippet.getDisplayMessage()).collect(
                            Collectors.toList()))),
                null);
        map.put(target.getTargetId(), response.text());
      } catch (Exception e) {
        log.error("error : {}", e.getMessage());
      }
    });

    return map;
  }

}
