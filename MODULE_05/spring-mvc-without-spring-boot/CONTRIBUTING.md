# CONTRIBUTING

- Follow [Conventional Commits](https://www.conventionalcommits.org/en/v1.0.0/).
    - References:
      - https://gist.github.com/joshbuchea/6f47e86d2510bce28f8e7f42ae84c716
      - https://ec.europa.eu/component-library/v1.15.0/eu/docs/conventions/git/

## Tests

- Use Hamcrest to write assertions.

## Maven commands

- mvn clean install -DskipTests
- mvn dependency:tree -Ddetail=true

### Release a new version

```text
mvn release:prepare
```
