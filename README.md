# ValidatorApi

ValidatorApi is a REST API for validating different types of data.

Currently implemented validator:
- IBAN

The project is designed to be extended with additional validators (for example: tax IDs, national IDs, card numbers, and more).

## Tech Stack
- Java 21
- Spring Boot 4
- Maven

## Run Locally
```bash
./mvnw spring-boot:run
```

Default IBAN endpoint:
- POST /v1/validate/iban

Example request:
```bash
curl -X POST http://localhost:8080/v1/validate/iban \
  -H "Content-Type: application/json" \
  -d '{"iban":"PL61 1090 1014 0000 0712 1981 2874"}'
```

Example response:
```json
{
  "valid": true,
  "normalized": "PL61109010140000071219812874",
  "reason": null,
  "details": {
    "countryCode": "PL",
    "checkDigits": "61"
  }
}
```

## Run Tests
```bash
./mvnw test
```

## Roadmap
- Replace raw IBAN error strings with a dedicated enum.
- Add IBAN country list and country-specific length validation.
- Add more validators in the core package.
- Add dedicated API layer tests (controller tests).
