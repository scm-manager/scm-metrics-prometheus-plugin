---
title: Documentation
subtitle: Prometheus Metrics Plugin Documentation
---
This documentation describes the usage of Prometheus Metrics Plugin. It is available in different languages and versions, which can be selected in the menu on the right.

In SCM Manager, many components collect metrics that provide useful information and can give insight into the usage of the system. This plugin provides these metrics for the monitoring system [Prometheus](https://prometheus.io/).

# Usage

To configure Prometheus for use with the plugin, the endpoint must be made known to Prometheus. This can be done either via one of Prometheus's service discovery mechanisms or via the static configuration in `prometheus.yml`. In the following we show the configuration using the static configuration. For more information on Prometheus configuration, have a look at the [Prometheus Documentation](https://prometheus.io/docs/prometheus/latest/configuration/configuration/).

## Static configuration

```yaml
scrape_configs:
- job_name: 'scm-manager'
  static_configs:
  - targets: [ 'scm.hitchhiker.com:8080' ]
  scheme: http
  metrics_path: '/scm/api/v2/metrics/prometheus' ]
  basic_auth:
    username: 'prometheus'
    password: 'secretPwd4Prometheus'
```

* `job_name`: Unique name for the scrape job
* `static_configs.targets`: An array with an entry for the hostname of the SCM-Manager instance including the port
* `scheme`: `http` or `https`.
* `metrics_path`: Path to the endpoint of the plugin, can be taken from the index (`/api/v2`). The link is located under `_links.metrics` with the name `prometheus`.
* `basic_auth`: Username and password of a user with the permission to read the metrics. It is recommended to create a technical user that has only this permission.
