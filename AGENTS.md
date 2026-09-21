# AGENTS.md

Spring Boot 4 (parent 4.1.1) / Spring Data JPA demo project on **Java 25** (enforced by the
maven-enforcer plugin). Single Maven module, package `ch.dboeckli.guru.jpa.multidb`. It demonstrates
**multiple independent databases** in one application — `cardholderdb`, `carddb` (credit card) and
`pandb` — each with its own datasource configuration, entity package and repository package, against
H2 (MySQL-compat mode) and MySQL, with schema management via **Flyway**. App port `8080`.

## Build & test commands

- Full build: `./mvnw clean verify` — format checks, unit (`*Test`, surefire) + IT (`*IT`, failsafe)
  tests, Helm lint/template.
- Unit tests only: `./mvnw test` (H2-based tests). Single test:
  `./mvnw test -Dtest=CreditCardRepositoryTest#methodName`.
- `./mvnw clean install` additionally builds the Docker image and packages the Helm chart into
  `target/helm/repo/`. Skip the Docker build with `-Dskip.docker.build=true`.
- `-Dskip.start.stop.springboot=true` skips the in-build app boot (spring-boot:start/stop).
- Run locally: `./mvnw spring-boot:run -Dspring-boot.run.profiles=h2` (or `mysql`).

After changing code, always verify: run the relevant Maven goal above and report its output
(evidence, not just "done").

## Profiles

- `h2`: in-memory H2 in MySQL-compat mode (no Docker).
- `mysql`: MySQL via Docker Compose — `compose-mysql.yaml`; schema via Flyway
  (`db/migration/{cardholder,creditcard,pan}`).
- IntelliJ run configs in `.run/`: `Spring6Application h2`, `Spring6Application mysql`,
  `deploy-k8s`, `test-k8s`, `uninstall-k8s`, `clear docker`.

## Sandbox build quirk (background)

This sandbox mounts the repo via filesystem passthrough, which blocks symlinks — Spotless's
`npm install` (prettier) would fail with `EPERM` unless npm skips bin links. The sandbox kit sets
`npm_config_bin_links=false` globally (`spec.yaml` → `environment.variables`), so no manual export
is needed here. On a normal host (Windows/CI) this does not apply either.

## Formatting is enforced (fails the `validate` phase)

- Java: Spring Java Format → fix with `./mvnw spring-javaformat:apply`.
- Everything else (pom.xml, `**/*.md`, json, `src/main/resources/application*.yaml`, `**/*.sh`):
  Spotless → fix with `./mvnw spotless:apply`.
- Spotless flexmark also formats markdown, so this file and any `.md` edits must stay flexmark-clean;
  run `./mvnw spotless:apply` after editing markdown.

## Test conventions

- Naming matters: `*Test` = unit (surefire), `*IT` = integration (failsafe).
- H2 tests: repository/service tests against in-memory H2 (MySQL-compat mode), including
  `*SpliceTest` variants.
- MySQL ITs: `@ActiveProfiles("test_mysql")`; they need Docker (MySQL from the test profile).
- A custom `TestClassOrderer` sorts test classes; `LocaleExtension` forces `Locale.US`.

## Architecture

- Three independent DBs, one per bounded context: `domain/cardholder`, `domain/creditcard` and
  `domain/pan` entities; `repository/{cardholder,creditcard,pan}` Spring Data JPA repositories, each
  wired in its own `config/*DatabaseConfiguration` class; `service` layer (`CreditCardService`)
  coordinates across the databases; `util/EncryptionUtil` for PAN encryption.
- Schema migrations: `src/main/resources/db/migration/{cardholder,creditcard,pan}` (Flyway only,
  wired by `config/FlywayConfiguration`).

## Deploy / CI

- Deployment is Helm-only: chart in `helm-charts/` (parent `sdjpa-multi-db-chart`, MySQL subchart
  `sdjpa-multi-db-mysql-chart`), packaged to `target/helm/repo/`, release name = artifactId,
  namespace `sdjpa-multi-db`.
- CI (`.github/workflows/`): `maven-build.yml` builds + deploys snapshots and triggers
  `deploy-and-test-cluster.yml`; `release.yml` runs `mvn release:prepare release:perform` on
  main/master only (version must be `-SNAPSHOT`); SonarCloud analysis runs in the `analyze` job.
- Dependency updates are managed via `.github/renovate.json`; validate changes with
  `renovate-config-validator`.
