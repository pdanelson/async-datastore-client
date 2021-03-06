/*
 * Copyright (c) 2011-2015 Spotify AB
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

package com.spotify.asyncdatastoreclient;

/**
 * Represents query order.
 *
 * An order is composed of a property name and a direction. Multiple orders
 * may be applied to a single {@code Query}.
 */
public class Order {

  public enum Direction {
    ASCENDING,
    DESCENDING
  }

  private final String name;
  private final Direction dir;

  Order(final String name, final Direction dir) {
    this.name = name;
    this.dir = dir;
  }

  com.google.datastore.v1.PropertyOrder getPb() {
    final com.google.datastore.v1.PropertyOrder.Builder order = com.google.datastore.v1.PropertyOrder.newBuilder()
        .setProperty(com.google.datastore.v1.PropertyReference.newBuilder().setName(name));
    if (dir == Direction.ASCENDING) {
      order.setDirection(com.google.datastore.v1.PropertyOrder.Direction.ASCENDING);
    } else {
      order.setDirection(com.google.datastore.v1.PropertyOrder.Direction.DESCENDING);
    }
    return order.build();
  }
}
