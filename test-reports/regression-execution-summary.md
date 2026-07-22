# HRMS Platform (ESS) — Regression Execution Summary (Sample)

> Representative regression execution report for portfolio purposes.

## Execution Overview

| Metric | Value |
|---|---|
| Test Cycle | Sample Release Regression |
| Total Test Cases Executed | 10 |
| Passed | 8 |
| Failed | 2 |
| Pass Rate | 80% |

## Results by Area

| Area | Test Cases | Passed | Failed | Notes |
|---|---|---|---|---|
| Login | 4 | 4 | 0 | — |
| Personal Details — GUI State | 1 (14 fields) | 1 | 0 | — |
| Personal Details — GUI Behavior | 1 (4 control types) | 1 | 0 | — |
| Personal Details — Save/Persistence | 1 | 1 | 0 | — |
| Profile Picture Upload | 3 | 1 | 2 | Field-access and file-size defects found (see bug-reports) |

## Defect Summary

| Severity | Count |
|---|---|
| Critical | 1 |
| Major | 1 |

## Conclusion

The regression cycle's two findings — a field-access-control bypass on Date of Birth and a
file-size validation gap on profile picture upload — are exactly the defect classes this
module's QA strategy is built to catch through field-by-field GUI validation rather than
happy-path-only testing. Both were prioritized for fix-and-retest ahead of UAT sign-off.
