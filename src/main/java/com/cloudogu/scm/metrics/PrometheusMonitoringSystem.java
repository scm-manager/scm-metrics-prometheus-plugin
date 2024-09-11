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

import com.google.common.annotations.VisibleForTesting;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.prometheus.PrometheusConfig;
import io.micrometer.prometheus.PrometheusMeterRegistry;
import sonia.scm.metrics.MonitoringSystem;
import sonia.scm.metrics.ScrapeTarget;
import sonia.scm.plugin.Extension;

import javax.inject.Singleton;
import java.util.Optional;

@Extension
@Singleton
public class PrometheusMonitoringSystem implements MonitoringSystem {

  @VisibleForTesting
  static final String NAME = "prometheus";

  private final PrometheusMeterRegistry registry = new PrometheusMeterRegistry(PrometheusConfig.DEFAULT);

  @Override
  public String getName() {
    return NAME;
  }

  @Override
  public MeterRegistry getRegistry() {
    return registry;
  }

  @Override
  public Optional<ScrapeTarget> getScrapeTarget() {
    return Optional.of(new PrometheusScrapeTarget(registry));
  }
}
