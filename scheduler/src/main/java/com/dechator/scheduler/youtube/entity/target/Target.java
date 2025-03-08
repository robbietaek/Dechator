package com.dechator.scheduler.youtube.entity.target;

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
@Table(name = "tb_target")
public class Target {

  @Id
  private String targetId;

  private String channelId;

  private String liveVideoId;

  private String liveChatId;

  public void updateTarget(String targetId, String channelId, String liveVideoId,
      String liveChatId) {
    this.targetId = targetId;
    this.channelId = channelId;
    this.liveVideoId = liveVideoId;
    this.liveChatId = liveChatId;
  }

}