# spring-html-demo
## build & launch App server
```bash
./mvnw clean package && docker compose up 
```

And then you can access to `http://localhost/todo` by your browser.
Your request is handled by Nginx and forwarded to the Spring Boot server.

## Architecture Overview
```mermaid
  flowchart LR
    subgraph client
    Browser
    Terminal
    end
    subgraph otel backend
    Jaeger
    Loki
    Prometheus
    otel-tui
    end
    Browser --> NGINX
    NGINX --> AppServer
    AppServer --> MySQL
    AppServer --telemetry data on Http by **opentelemetry-spring-boot-starter**---> OtelCollector
    OtelCollector --traces on gRPC--> Jaeger
    OtelCollector --logs on Http--> Loki
    OtelCollector --metrics on Http--> Prometheus
    OtelCollector --telemetry data on gRPC-->otel-tui
    Graphana --> Loki
    Graphana --> Prometheus
    Browser --"http://localhost:16686"--> Jaeger
    Browser --> Prometheus
    Browser --> Graphana
    Terminal --> otel-tui
```

## Otel Backends
### Trace (Jaeger)
open `http://localhost:16686` in your browser.

### log  (Loki + Grafana)
open `http://localhost:3000` in your browser.
And login with `admin`/`admin`.

Configure Loki from `http://localhost:3000/connections/datasources/new`.
And then you can query logs from `http://localhost:3000/explore`.

### Metrics (Prometheus)
open `http://localhost:9090` in your browser.
And query target metrics.

### Trace, Log, Metrics (otel-tui)
https://github.com/ymtdzzz/otel-tui

```bash
$ docker compose attach oteltui
```
