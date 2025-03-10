package com.dechator.scheduler.youtube.service;

import com.dechator.scheduler.youtube.model.author.Author;
import com.dechator.scheduler.youtube.model.snippet.Snippet;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class BulkInsertService {

  private final JdbcTemplate jdbcTemplate;
  private static final int BATCH_SIZE = 500;

  public void bulkInsertAuthorList(List<Author> authorList) {
    String sql = """
            INSERT INTO tb_author (channel_id, channel_url, display_name, is_chat_moderator, is_chat_owner, is_chat_sponsor, is_verified, profile_image_url)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?)
            ON CONFLICT (channel_id) DO UPDATE 
            SET channel_url = EXCLUDED.channel_url,
                display_name = EXCLUDED.display_name,
                is_chat_moderator = EXCLUDED.is_chat_moderator,
                is_chat_owner = EXCLUDED.is_chat_owner,
                is_chat_sponsor = EXCLUDED.is_chat_sponsor,
                is_verified = EXCLUDED.is_verified,
                profile_image_url = EXCLUDED.profile_image_url
        """;

    int totalSize = authorList.size();
    for (int i = 0; i < totalSize; i += BATCH_SIZE) {
      int end = Math.min(i + BATCH_SIZE, totalSize);
      List<Object[]> batchArgs = authorList.subList(i, end).stream()
          .map(author -> new Object[]{
              author.getChannelId(),
              author.getChannelUrl(),
              author.getDisplayName(),
              author.getIsChatModerator(),
              author.getIsChatOwner(),
              author.getIsChatSponsor(),
              author.getIsVerified(),
              author.getProfileImageUrl()
          })
          .toList();

      jdbcTemplate.batchUpdate(sql, batchArgs);
    }

  }

  public void bulkInsertSnippetList(List<Snippet> snippetList) {
    String sql = """
            INSERT INTO tb_chat (id, author_channel_id, display_message, has_display_content, published_at, target_id, "type")
            VALUES (?, ?, ?, ?, ?, ?, ?)
            ON CONFLICT (id) DO UPDATE 
            SET author_channel_id = EXCLUDED.author_channel_id,
                display_message = EXCLUDED.display_message,
                has_display_content = EXCLUDED.has_display_content,
                published_at = EXCLUDED.published_at,
                target_id = EXCLUDED.target_id,
                "type" = EXCLUDED."type"
        """;

    int totalSize = snippetList.size();
    for (int i = 0; i < totalSize; i += BATCH_SIZE) {
      int end = Math.min(i + BATCH_SIZE, totalSize);
      List<Object[]> batchArgs = snippetList.subList(i, end).stream()
          .map(chat -> new Object[]{
              chat.getId(),
              chat.getAuthorChannelId(),
              chat.getDisplayMessage(),
              chat.getHasDisplayContent(),
              chat.getPublishedAt(),
              chat.getTargetId(),
              chat.getType()
          })
          .toList();

      jdbcTemplate.batchUpdate(sql, batchArgs);
    }
  }

}
