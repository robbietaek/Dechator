package com.dechator.scheduler.youtube.entity.snippet;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.OffsetDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "tb_chat")
public class Snippet {

  @Id
  private String id;

  private String targetId;

  private String type;

  private String authorChannelId;

  @Column(name = "published_at", columnDefinition = "TIMESTAMP WITH TIME ZONE")
  private OffsetDateTime publishedAt;

  private Boolean hasDisplayContent;

  @Column(columnDefinition = "TEXT")
  private String displayMessage;

}