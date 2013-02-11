/*
 * Copyright (c) 2012 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package com.google.api.client.googleapis.subscriptions;

import com.google.api.client.googleapis.testing.subscriptions.MockNotificationCallback;
import com.google.common.collect.ImmutableSet;

import junit.framework.TestCase;

/**
 * Tests for the {@link MemorySubscriptionStore} class.
 *
 * @author Matthias Linder (mlinder)
 */
public class MemorySubscriptionStoreTest extends TestCase {

  public void testStoreAndGet() {
    MockNotificationCallback handler = new MockNotificationCallback();
    StoredSubscription s = new StoredSubscription(handler);
    MemorySubscriptionStore store = new MemorySubscriptionStore();
    store.storeSubscription(s);
    assertEquals(s, store.getSubscription(s.getId()));
    assertEquals(null, store.getSubscription("does_not_exist"));
  }

  public void testList() {
    MockNotificationCallback handler = new MockNotificationCallback();
    StoredSubscription s1 = new StoredSubscription(handler);
    StoredSubscription s2 = new StoredSubscription(handler);
    MemorySubscriptionStore store = new MemorySubscriptionStore();
    store.storeSubscription(s1);
    store.storeSubscription(s2);
    assertEquals(2, store.listSubscriptions().size());
    assertEquals(ImmutableSet.of(s1, s2), ImmutableSet.copyOf(store.listSubscriptions()));
  }

  public void testRemove() {
    MockNotificationCallback handler = new MockNotificationCallback();
    StoredSubscription s = new StoredSubscription(handler);
    MemorySubscriptionStore store = new MemorySubscriptionStore();
    store.storeSubscription(s);
    store.removeSubscription(s);
    assertEquals(null, store.getSubscription(s.getId()));
  }
}
