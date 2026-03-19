# ValidatorApi

ValidatorApi is a REST API for validating different types of data.

Currently implemented validators:
- IBAN
- Luhn

The project is designed to be extended with additional validators (for example: tax IDs, phone numbers, email addresses, URLs, domains, national IDs, card numbers, and more).

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

Default Luhn endpoint:
- POST /v1/validate/luhn

IBAN Example request:
```bash
curl -X POST http://localhost:8080/v1/validate/iban \
  -H "Content-Type: application/json" \
  -d '{"iban":"PL61 1090 1014 0000 0712 1981 2874"}'
```

IBAN Example response:
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

Luhn example request:
```bash
curl -X POST http://localhost:8080/v1/validate/luhn \
  -H "Content-Type: application/json" \
  -d '{"luhn":"4539 1488 0343 6467"}'
```

Luhn example response:
```json
{
  "valid": true,
  "normalized": "4539148803436467",
  "reason": null,
  "details": {
    "length": 16
  }
}
```

## Run Tests
```bash
./mvnw test
```

## Roadmap
- Replace raw IBAN error strings with a dedicated enum.
- Replace raw Luhn error strings with a dedicated enum.
- Add IBAN country list and country-specific length validation.
- Add more validators in the core package.
- Add dedicated API layer tests (controller tests).
