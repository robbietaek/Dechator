package com.dechator.scheduler.youtube.access;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public final class ApiKey {

  private static final List<String> API_KEYS = Arrays.asList(

  );

  private static final AtomicInteger keyIndex = new AtomicInteger(0);

  public static String getRandomApiKey() {
    int index = keyIndex.getAndUpdate(i -> (i + 1) % API_KEYS.size());
    return API_KEYS.get(index);
  }

}
