# HRMS Platform — ESS Regression Checklist & Test Cases

> Full ESS (Employee Self-Service / MyInfo) test suite, covering Login and Personal/Contact
> Details. Cleaned up and structured from the original test case sheet — one duplicate test case
> ID in the source data (`TC_MYINFO_LOGIN_03` used twice) has been corrected below
> (`TC_MYINFO_LOGIN_04`). All data is dummy/sample.

## 1. Login

| ID | Title | Steps | Expected Result | Test Data | Priority | Category | Type |
|---|---|---|---|---|---|---|---|
| TC_MYINFO_LOGIN_01 | Login with valid ESS user and valid password | 1. Open the application URL 2. Enter valid ESS username 3. Enter valid ESS password 4. Click Login | User is redirected to the "Personal Details" page | Valid ESS user, created by an HR Admin account | P1 | Sanity | Functional |
| TC_MYINFO_LOGIN_02 | Login with valid ESS user and invalid password | 1. Open the application URL 2. Enter valid ESS username 3. Enter invalid ESS password 4. Click Login | Error message displayed: "Credentials not valid" | Valid ESS user + invalid password | P2 | Progression | Functional |
| TC_MYINFO_LOGIN_03 | Login with invalid ESS user and valid password | 1. Open the application URL 2. Enter invalid ESS username 3. Enter valid ESS password 4. Click Login | Error message displayed: "Credentials not valid" | Invalid ESS user + valid password | P2 | Progression | Functional |
| TC_MYINFO_LOGIN_04 | Login with invalid ESS user and invalid password | 1. Open the application URL 2. Enter invalid ESS username 3. Enter invalid ESS password 4. Click Login | Error message displayed: "Credentials not valid" | Invalid ESS user + invalid password | P2 | Progression | Functional |

## 2. Personal Details — GUI Element State (Enabled / Disabled)

**TC_MYINFO_PERSDETAILS_01** — Check the GUI elements present in the Contact Details form.
Priority: P3 · Category: Progression · Type: GUI · Test Data: N/A

| Field | Expected State |
|---|---|
| Full Name | Enabled |
| Middle Name | Enabled |
| Last Name | Enabled |
| Employee ID | **Disabled** |
| Other ID | Enabled |
| Driver's License Number | **Disabled** |
| License Expiry Date | Enabled |
| Gender | Enabled |
| Nationality | Enabled |
| Marital Status | Enabled |
| Date of Birth | **Disabled** |
| Nick Name | Enabled |
| Smoker | Enabled |
| Military Service | Enabled |

## 3. Personal Details — GUI Element Behavior

**TC_MYINFO_PERSDETAILS_02** — Check the behavior of GUI elements present in the Contact Details
form. Priority: P2 · Category: Progression · Type: GUI · Test Data: N/A

| Control Type | Field(s) | Expected Behavior |
|---|---|---|
| Text Box | Full Name, Middle Name, Last Name, Other ID, Nick Name, Military Service | Should allow entering values/text |
| Combo Box | Marital Status, Nationality | Should display the list of items; should allow selecting exactly one item at a time |
| Radio Button | Gender | Should allow selecting exactly one option at a time |
| Calendar / Date Picker | License Expiry Date | Should allow selecting a date; the selected date should display in the associated text box |

## 4. Personal Details — Save / Persistence

| ID | Title | Steps | Expected Result | Test Data | Priority | Category | Type |
|---|---|---|---|---|---|---|---|
| TC_MYINFO_PERSDETAILS_03 | Check the contact details are updating/saved | 1. Click Edit on the Contact Details page 2. Enter appropriate values in the contact details form 3. Click Save | Details are saved; a confirmation message ("details are saved") is displayed | Test data required (dummy values per field) | P2 | Progression | Database |

## 5. Profile Picture Upload

| ID | Title | Steps | Expected Result | Test Data | Priority | Category | Type |
|---|---|---|---|---|---|---|---|
| TC_MYINFO_PERSDETAILS_04 | Check the user can upload a picture in an accepted format (jpg, png, gif) | 1. Login 2. Go to Contact Details form 3. Click the Add button in the attachment section 4. Browse/provide the path of a dummy image file 5. Click Save | A popup prompts to browse for an image file; the image is successfully attached to the contact details form | Dummy image files (jpg/png/gif) | P2 | Progression | Functional |
| TC_MYINFO_PERSDETAILS_05 | Check the user can upload a picture under 1 MB | 1. Login 2. Go to Contact Details form 3. Click Add in the attachment section 4. Browse a dummy image file under 1 MB 5. Click Save | Upload is allowed | Dummy image file, size < 1 MB | — | — | Functional |
| TC_MYINFO_PERSDETAILS_06 | Check the user cannot upload a picture over 1 MB | 1. Login 2. Go to Contact Details form 3. Click Add in the attachment section 4. Browse a dummy image file over 1 MB 5. Click Save | Upload is **not** allowed — a clear file-size error is shown | Dummy image file, size > 1 MB | P2 | Progression | Functional |

## 6. Full Regression Checklist

- [ ] Login — valid credentials
- [ ] Login — invalid username only
- [ ] Login — invalid password only
- [ ] Login — both invalid
- [ ] Personal Details — field enabled/disabled state (all 14 fields)
- [ ] Personal Details — text box behavior
- [ ] Personal Details — combo box behavior (single-select)
- [ ] Personal Details — radio button behavior (single-select)
- [ ] Personal Details — date picker behavior
- [ ] Personal Details — save/persistence with confirmation message
- [ ] Profile Picture Upload — accepted formats (jpg/png/gif)
- [ ] Profile Picture Upload — under size limit (allowed)
- [ ] Profile Picture Upload — over size limit (rejected)

## 7. Priority Automation Candidates

1. Login — valid credentials (P1 Sanity)
2. Login — all 3 negative combinations
3. Personal Details — field enabled/disabled state verification
4. Personal Details — save/update confirmation

See [`automation/`](../automation) for the Selenium/TestNG implementation.
