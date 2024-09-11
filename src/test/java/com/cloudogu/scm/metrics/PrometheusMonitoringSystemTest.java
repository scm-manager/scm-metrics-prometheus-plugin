/*
 * Copyright (c) 2020 - present Cloudogu GmbH
 *
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU Affero General Public License as published by the Free
 * Software Foundation, version 3.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see https://www.gnu.org/licenses/.
 */

package com.cloudogu.scm.metrics;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PrometheusMonitoringSystemTest {

  @Test
  void shouldReturnName() {
    PrometheusMonitoringSystem system = new PrometheusMonitoringSystem();
    assertThat(system.getName()).isEqualTo(PrometheusMonitoringSystem.NAME);
  }

  @Test
  void shouldReturnWorkingRegistry() {
    PrometheusMonitoringSystem system = new PrometheusMonitoringSystem();
    MeterRegistry registry = system.getRegistry();
    Counter c = registry.counter("c");
    c.increment();
    c.increment();
    assertThat(c.count()).isEqualTo(2.0);
  }

  @Test
  void shouldReturnAnScrapeTarget() {
    PrometheusMonitoringSystem system = new PrometheusMonitoringSystem();
    assertThat(system.getScrapeTarget()).isPresent();
  }

}
