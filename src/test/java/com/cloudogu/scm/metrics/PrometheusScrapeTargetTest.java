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

import io.micrometer.prometheus.PrometheusConfig;
import io.micrometer.prometheus.PrometheusMeterRegistry;
import io.prometheus.client.exporter.common.TextFormat;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

class PrometheusScrapeTargetTest {

  @Test
  void shouldReturnContentType() {
    PrometheusScrapeTarget target = new PrometheusScrapeTarget(new PrometheusMeterRegistry(PrometheusConfig.DEFAULT));
    assertThat(target.getContentType()).isEqualTo(TextFormat.CONTENT_TYPE_004);
  }

  @Test
  void shouldWritePrometheusMetrics() throws IOException {
    PrometheusMeterRegistry registry = new PrometheusMeterRegistry(PrometheusConfig.DEFAULT);
    registry.counter("sample.counter").increment();

    PrometheusScrapeTarget target = new PrometheusScrapeTarget(registry);
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    target.write(baos);
    assertThat(baos.toString("UTF-8")).contains("sample_counter_total 1.0");
  }

}
