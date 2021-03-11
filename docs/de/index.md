---
title: Dokumentation
subtitle: Prometheus Metrics Plugin Dokumentation
---

Diese Dokumentation beschreibt die Nutzung des Prometheus Metrics Plugin. Sie steht in verschiedenen Sprachen zur Verfügung, die auf der rechten Seite gewechselt werden können.

In SCM-Manager sammeln viele Komponenten Metriken, die nützliche Informationen bereitstellen und Aufschluss über die Nutzung des Systems geben können. Dieses Plugin stellt diese Metriken für das Monitoring System [Prometheus](https://prometheus.io/) zur Verfügung.

# Nutzung

Um Prometheus für die Nutzung mit dem Plugin zu konfigurieren, muss der Endpunkt Prometheus bekannt gemacht werden. Das kann entweder über einen der Service Discovery Mechanismen von Prometheus geschehen oder über die statische Konfiguration in der `prometheus.yml`. Im folgenden zeigen wir die Konfiguration anhand der statischen Konfiguration. Für mehr Informationen zur Prometheus Konfiguration:

* [Prometheus Dokumentation](https://prometheus.io/docs/prometheus/latest/configuration/configuration/)

## Statische Konfiguration

```yaml
scrape_configs:
- job_name: 'scm-manager'
  static_configs:
  - targets: [ 'scm.hitchhiker.com:8080' ]
  scheme: http
  metrics_path: '/scm/api/v2/metrics/prometheus'
  basic_auth:
    username: 'prometheus'
    password: 'secretPwd4Prometheus'
```

* `job_name`: Eindeutiger Name für den Prometheus Scrape Job
* `static_configs.targets`: Ein Array mit einem Eintrag für den Hostnamen der SCM-Manager Instanz inklusive des Ports
* `scheme`: `http` oder `https`
* `metrics_path`: Pfad zum Endpoint des Plugins, kann aus dem Index (`/api/v2`) entnommen werden. Der Link befindet sich unter `_links.metrics` mit dem Namen `prometheus`
* `basic_auth`: Benutzername und Passwort eines Benutzers mit dem Recht die Metriken zu lesen. Es empfiehlt sich einen technischen Benutzer anzulegen der nur diese Berechtigung hat.
