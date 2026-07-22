# HRMS Platform — Automation Framework

Automation for the ESS Login and Personal Details flows, built with **Selenium WebDriver + Java +
TestNG**.

## Why Selenium + Java + TestNG

- This module's regression suite is primarily manual (see the README's "My Role" and Key
  Achievements) — Selenium/TestNG automation here targets the highest-value, most repetitive
  cases (login negative-case matrix, field-state verification) to reduce manual regression load,
  rather than attempting full automation coverage of every GUI behavior test
- TestNG's `@DataProvider` is a natural fit for the login negative-case matrix (valid/invalid
  username × valid/invalid password)

## Suggested Project Structure

```
automation/
├── README.md
├── pom.xml
├── src/test/java/
│   ├── pages/
│   │   ├── LoginPage.java
│   │   └── PersonalDetailsPage.java
│   ├── fixtures/
│   │   └── DummyEssCredentials.java
│   └── tests/
│       └── SampleEssLoginTest.java
└── testng.xml
```

> This repo currently includes one representative sample (`SampleEssLoginTest.java`) rather than
> the full framework, to keep the portfolio focused.

## Test Data Policy

All automation uses **dummy data only**: dummy ESS usernames/passwords that map to no real
employee account.

## Priority Automated Scenarios

1. Login — valid credentials (P1 Sanity)
2. Login — all 3 negative combinations (invalid username / invalid password / both invalid)
3. Personal Details — field enabled/disabled state verification
4. Personal Details — save/update confirmation
