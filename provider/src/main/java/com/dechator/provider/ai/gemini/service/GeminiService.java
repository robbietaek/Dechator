package com.dechator.provider.ai.gemini.service;

import com.dechator.provider.prompt.dao.PromptDao;
import com.dechator.provider.prompt.model.news.NewsSummary;
import com.dechator.provider.prompt.model.politics.PoliticsSummary;
import com.dechator.provider.prompt.model.prompt.Prompt;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.text.TextContentRenderer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class GeminiService {

  private final PromptDao promptDao;
  private static String geminiApiKey;

  private ObjectMapper objectMapper = new ObjectMapper();

  @Value("${google.gemimiApiKey}")
  public void setGeminiApiKey(String geminiApiKey) {
    this.geminiApiKey = geminiApiKey;
  }

  public NewsSummary getGeminiNewsResponse(String targetId, List<String> requestList) {
    NewsSummary result = null;
    Client client = Client.builder().apiKey(geminiApiKey).build();

    try {
      Prompt prompt = promptDao.selectPromptByPromptType("CHAT_NEWS");
      GenerateContentResponse response =
          client.models.generateContent("gemini-2.0-flash-001", String.format(
                  prompt.getPromptText(), targetId, prompt.getPromptJson(),
                  String.join(", ", requestList)),
              null);
      Parser parser = Parser.builder().build();
      Node document = parser.parse(response.text());
      TextContentRenderer renderer = TextContentRenderer.builder().build();
      String responseJson = renderer.render(document).trim();
      result = objectMapper.readValue(responseJson, NewsSummary.class);
    } catch (Exception e) {
      log.error("error : {}", e.getMessage());
    }

    return result;
  }

  public PoliticsSummary getGeminiPoliticsResponse(String targetId, List<String> requestList) {
    PoliticsSummary politicsSummary = null;
    Client client = Client.builder().apiKey(geminiApiKey).build();

    try {
      Prompt prompt = promptDao.selectPromptByPromptType("CHAT_POLITICS");
      GenerateContentResponse response =
          client.models.generateContent("gemini-2.0-flash-001", String.format(
                  prompt.getPromptText(), targetId,
                  prompt.getPromptJson(),
                  String.join(", ", requestList)),
              null);

      Parser parser = Parser.builder().build();
      Node document = parser.parse(response.text());
      TextContentRenderer renderer = TextContentRenderer.builder().build();
      String responseJson = renderer.render(document).trim();
      politicsSummary = objectMapper.readValue(responseJson, PoliticsSummary.class);
    } catch (Exception e) {
      log.error("error : {}", e.getMessage());
    }

    return politicsSummary;
  }

}
