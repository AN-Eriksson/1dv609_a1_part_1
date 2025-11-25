| Version       | Correct | BugDoesNotHash | NoTrim | NullInput | SpecialCharBug | MyCustomBug |
|---------------|:-------:|:--------------:|:------:|:---------:|:---------------:|:------------:|
| Test name 1   |   ✅    |       ❌        |   ✅    |    ✅      |        ❌        |      ✅       |
| Test name 2   |   ❌    |       ❌        |   ❌    |    ✅      |        ✅        |      ❌       |
| Test name 3   |   ✅    |       ✅        |   ❌    |    ❌      |        ✅        |      ❌       |
| Test name 4   |   ❌    |       ❌        |   ✅    |    ❌      |        ❌        |      ✅       |
| Test name 5   |   ✅    |       ❌        |   ❌    |    ❌      |        ❌        |      ❌       |
| **Coverage**  | **100%** |   **100%**     | **100%** | **100%**  |    **100%**     |   **100%**   |