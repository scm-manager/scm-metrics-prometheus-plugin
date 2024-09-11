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

import io.micrometer.prometheus.PrometheusMeterRegistry;
import io.prometheus.client.exporter.common.TextFormat;
import sonia.scm.metrics.ScrapeTarget;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

public class PrometheusScrapeTarget implements ScrapeTarget {

  private final PrometheusMeterRegistry registry;

  PrometheusScrapeTarget(PrometheusMeterRegistry registry) {
    this.registry = registry;
  }

  @Override
  public String getContentType() {
    return TextFormat.CONTENT_TYPE_004;
  }

  @Override
  public void write(OutputStream outputStream) throws IOException {
    try (OutputStreamWriter writer = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8)) {
      registry.scrape(writer);
    }
  }

}
