package com.dechator.provider.youtube.controller;

import com.dechator.provider.youtube.service.ChatService;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/chat")
public class ChatController {

  private final ChatService chatService;

  @GetMapping
  public ResponseEntity<Map<String, String>> getChatSummary() {
    return ResponseEntity.status(HttpStatus.OK)
        .body(chatService.getAllChatSummary());
  }

}
