package com.dechator.provider.ai.gemini.service;

import com.dechator.provider.prompt.dao.PromptDao;
import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class GeminiService {

  private final PromptDao promptDao;
  private static String geminiApiKey;

  @Value("${google.gemimiApiKey}")
  public void setGeminiApiKey(String geminiApiKey) {
    this.geminiApiKey = geminiApiKey;
  }

  public String getGeminiNewsResponse(String targetId, List<String> requestList) {
    String result = null;
    Client client = Client.builder().apiKey(geminiApiKey).build();

    try {
      GenerateContentResponse response =
          client.models.generateContent("gemini-2.0-flash-001", String.format(
                  promptDao.selectPromptTextByPromptType("CHAT_NEWS"), targetId,
                  String.join(", ", requestList)),
              null);
      result = modifyToCleanText(response.text());
    } catch (Exception e) {
      log.error("error : {}", e.getMessage());
    }

    return result;
  }

  public String getGeminiPoliticsResponse(String targetId, List<String> requestList) {
    String result = null;
    Client client = Client.builder().apiKey(geminiApiKey).build();

    try {
      GenerateContentResponse response =
          client.models.generateContent("gemini-2.0-flash-001", String.format(
                  promptDao.selectPromptTextByPromptType("CHAT_POLITICS"), targetId,
                  String.join(", ", requestList)),
              null);
      result = modifyToCleanText(response.text());
    } catch (Exception e) {
      log.error("error : {}", e.getMessage());
    }

    return result;
  }

  private String modifyToCleanText(String text) {
    return text.replaceAll("\\p{C}", "")
        .replaceAll("\\*", "")
        .trim()
        .replaceAll("   ", "");
  }

}
