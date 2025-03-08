package com.dechator.scheduler.youtube.entity.author;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tb_author")
public class Author {

  @Id
  private String channelId;

  @Column(columnDefinition = "TEXT")
  private String channelUrl;

  private String displayName;

  @Column(columnDefinition = "TEXT")
  private String profileImageUrl;

  private Boolean isVerified;

  private Boolean isChatOwner;

  private Boolean isChatSponsor;

  private Boolean isChatModerator;
}