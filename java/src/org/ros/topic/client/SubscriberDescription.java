/*
 * Copyright (C) 2011 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package org.ros.topic.client;

import com.google.common.collect.ImmutableMap;

import org.ros.node.server.SlaveDescription;
import org.ros.topic.TopicDescription;
import org.ros.transport.ConnectionHeaderFields;

import java.net.URL;
import java.util.Map;

/**
 * @author damonkohler@google.com (Damon Kohler)
 */
public class SubscriberDescription {

  private final SlaveDescription slaveDescription;
  private final TopicDescription topicDescription;

  public static SubscriberDescription createFromHeader(Map<String, String> header) {
    // TODO(damonkohler): Update SlaveDescription to handle the case where the
    // URL is not set.
    SlaveDescription slaveDescription = new SlaveDescription(header.get(ConnectionHeaderFields.CALLER_ID),
        null);
    return new SubscriberDescription(slaveDescription, TopicDescription.createFromHeader(header));
  }

  public SubscriberDescription(SlaveDescription slaveDescription, TopicDescription topicDescription) {
    this.slaveDescription = slaveDescription;
    this.topicDescription = topicDescription;
  }

  public SlaveDescription getSlaveDescription() {
    return slaveDescription;
  }

  public String getNodeName() {
    return slaveDescription.getName();
  }

  public URL getSlaveUrl() {
    return slaveDescription.getUrl();
  }

  public String getTopicName() {
    return topicDescription.getName();
  }

  public Map<String, String> toHeader() {
    return new ImmutableMap.Builder<String, String>()
        .putAll(slaveDescription.toHeader())
        .putAll(topicDescription.toHeader())
        .build();
  }

}