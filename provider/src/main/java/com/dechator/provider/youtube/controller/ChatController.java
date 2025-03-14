package com.dechator.provider.youtube.controller;

import com.dechator.provider.prompt.model.news.NewsSummary;
import com.dechator.provider.prompt.model.politics.PoliticsSummary;
import com.dechator.provider.youtube.service.ChatService;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/chat/v1")
public class ChatController {

  private final ChatService chatService;

  @GetMapping("/news/summary")
  public ResponseEntity<Map<String, NewsSummary>> getNewsChatSummaryList() {
    return ResponseEntity.status(HttpStatus.OK)
        .body(chatService.getNewsChatSummaryList());
  }

  @GetMapping("/news/summary/target/{targetId}")
  public ResponseEntity<Map<String, NewsSummary>> getNewsChatSummaryByTargetId(
      @PathVariable String targetId) {
    return ResponseEntity.status(HttpStatus.OK)
        .body(chatService.getNewsChatSummaryByTargetId(targetId));
  }

  @GetMapping("/politics/summary")
  public ResponseEntity<Map<String, PoliticsSummary>> getPoliticsChatSummaryList() {
    return ResponseEntity.status(HttpStatus.OK)
        .body(chatService.getPoliticsChatSummaryList());
  }

  @GetMapping("/politics/summary/target/{targetId}")
  public ResponseEntity<Map<String, PoliticsSummary>> getPoliticsChatSummaryByTargetId(
      @PathVariable String targetId) {
    return ResponseEntity.status(HttpStatus.OK)
        .body(chatService.getPoliticsChatSummaryByTargetId(targetId));
  }


}
