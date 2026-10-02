# Sample Requirement Traceability Matrix — HRMS Platform (ESS)

> Worked example using dummy data. An RTM is referenced throughout this portfolio as a core QA
> artifact — this is what that artifact actually looks like, not just a claim that it exists.
> See [`docs/README.md`](./docs/README.md) for the full documentation map.

## What an RTM Is Actually For

A regression checklist (see [`regression-checklist.md`](./regression-checklist.md)) answers
"what do we test." An RTM answers a different, equally important question: **"does every
business requirement have test coverage, and is that coverage actually sufficient?"** The two
documents look similar but serve different purposes — a checklist is organized by test area; an
RTM is organized by *requirement*, which is what makes it the tool that actually catches a
requirement with **no** test coverage at all, not just a weakly-tested one.

## The Matrix

| Req ID | Requirement (from a sample sprint story) | Linked Test Case(s) | Automation Status | Coverage Status |
|---|---|---|---|---|
| REQ-401 | A valid ESS user can log in and reach the Personal Details page | TC_MYINFO_LOGIN_01 | Automated | ✅ Covered |
| REQ-402 | All three invalid-credential combinations show an identical, non-revealing error message | TC_MYINFO_LOGIN_02–04 | Automated | ✅ Covered |
| REQ-403 | Every HR-controlled field renders as disabled in the UI | TC_MYINFO_PERSDETAILS_01 | Manual | ✅ Covered |
| REQ-404 | Every employee-editable field behaves correctly for its control type (text/combo/radio/date) | TC_MYINFO_PERSDETAILS_02 | Manual | ✅ Covered |
| REQ-405 | A saved change to an employee-editable field persists and shows a confirmation message | TC_MYINFO_PERSDETAILS_03 | Manual | ✅ Covered |
| REQ-406 | Profile picture upload accepts jpg/png/gif and rejects other formats | TC_MYINFO_PERSDETAILS_04 | Manual | ✅ Covered |
| REQ-407 | Profile picture upload enforces the 1MB size limit | TC_MYINFO_PERSDETAILS_06 | Manual | ⚠️ Partial — this is the exact requirement `BUG-HRM-7038` violated; the test existed but only verified the UI-level rejection message, not whether the server itself independently validates size |
| REQ-408 | HR-controlled fields cannot be modified via a direct API call, even if the UI never sends that request | — | — | ❌ **Gap — this is the exact requirement `BUG-HRM-7021` violated; no test case ever called the save API directly bypassing the UI** |
| REQ-409 | Disabled and enabled fields are visually and programmatically distinguishable (not color alone) | TC_MYINFO_UI_04 | Manual | ✅ Covered |
| REQ-410 | Login and save/update remain correct and responsive when many employees act within the same narrow window | — | — | ❌ **Gap — identified when performance testing was added to this suite; see `docs/tech-and-skills.md` section 5** |

## What the Gaps Actually Caught

This is the part a checklist alone wouldn't surface, because a checklist only tells you about the
tests that already exist:

- **REQ-408** is the single most important gap in this matrix. `TC_MYINFO_PERSDETAILS_01` tests
  that Date of Birth *renders* disabled — it was never a test of whether the **save endpoint**
  rejects a Date of Birth change if one is sent anyway. Those are different claims. Per
  [`architecture-and-flow.md`](./docs/architecture-and-flow.md) section 3, this is precisely the
  OWASP API3:2023 BOPLA gap `BUG-HRM-7021` fell into — a UI-only control with no independent
  server-side equivalent. Raised as a new story (illustrative ID `HRM-3104`): add a direct
  API-level test that attempts to PATCH an HR-controlled field as an employee and asserts
  rejection, independent of any UI interaction at all.
- **REQ-410** is a gap this RTM only caught because performance testing was added to this
  suite's scope at all — every existing test case in this repo runs as a single user, one action
  at a time. None of them say anything about whether login or the field-write-authorization check
  (which now runs on every save, per REQ-408's fix) still behaves correctly when many employees
  hit the system concurrently, which per
  [`docs/tech-and-skills.md`](./docs/tech-and-skills.md) section 5 is this module's actual
  realistic peak-load scenario.

**The general pattern:** an RTM's value isn't the rows that say "Covered" — those just confirm
existing test design. Its value is specifically the rows that say "Gap" or "Partial," because
those are the requirements a test-case-first workflow (write tests, forget to check them against
the original requirement list) would never have surfaced on its own.
