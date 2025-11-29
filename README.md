# GES Model Sample - Java Modernization Journey

A comprehensive example of modernizing a legacy Java 8 / JPA 1.0 / JUnit 4 application to modern Java 21 / JPA 3.1 / JUnit 5.

## 🎯 Purpose

This repository demonstrates a complete, step-by-step modernization of a Java enterprise application, showcasing best practices and modern features at each stage.

## 📚 What's Inside

A simplified student management system (GES - Gestão de Estudantes) with entities for:
- Members (students)
- Courses
- Class Groups
- Instructors
- Locations
- Enrollments
- Attendance

## 🚀 Modernization Steps

Each step is tagged for easy navigation:

| Tag | Description | Key Changes |
|-----|-------------|-------------|
| `v0.0-legacy-baseline` | **Starting Point** | Java 8, JPA 1.0, JUnit 4, javax.* |
| `v1.0-step1-jakarta-migration` | **Jakarta Migration** | javax.* → jakarta.* namespace |
| `v1.0-step2-jpa-modernization` | **JPA 3.1 Upgrade** | Records, repeatable annotations, Java 21 |
| `v1.0-step3-junit5-migration` | **JUnit 5 Migration** | Modern testing with AssertJ |
| `v1.0-step4-java21-features` | **Java 21 Features** | Sealed types, switch expressions, text blocks |
| `v1.0-complete` | **Final State** | Complete with documentation |

## 🔍 Navigate the Journey

### View a specific step:
```bash
git checkout v1.0-step1-jakarta-migration
```

### Compare steps:
```bash
git diff v1.0-step1-jakarta-migration v1.0-step2-jpa-modernization
```

### View all tags:
```bash
git tag -l -n1
```

## 🛠️ Technology Stack

### Before → After

| Component | Before | After |
|-----------|--------|-------|
| Java | 8 | 21 |
| JPA | 1.0 (javax) | 3.1 (jakarta) |
| Testing | JUnit 4 | JUnit 5 + AssertJ |
| Date/Time | java.util.Date | java.time.* |
| ORM | None | Hibernate 6.4.4 |
| Build | Maven 2.3.2 | Maven 3.13.0 |

## 📖 Key Features Demonstrated

### Modern Java 21
- ✅ Records as JPA embeddables
- ✅ Sealed interfaces
- ✅ Switch expressions with pattern matching
- ✅ Pattern matching with instanceof
- ✅ Text blocks
- ✅ LocalDateTime/LocalDate

### Modern JPA 3.1
- ✅ Repeatable annotations
- ✅ Programmatic bootstrap
- ✅ Minimal XML configuration
- ✅ Records as @Embeddable

### Modern Testing
- ✅ JUnit 5 (Jupiter)
- ✅ AssertJ fluent assertions
- ✅ Parameterized tests
- ✅ Nested test classes
- ✅ Display names

## 🏃 Quick Start

### Prerequisites
- Java 21
- Maven 3.8+

### Build & Test
```bash
# Compile
mvn clean compile

# Run tests
mvn test

# Package
mvn package
```

## 📊 Test Coverage

- **Total Tests:** 29
- **Test Classes:** 3
  - MemberTest (6 tests)
  - CourseTest (14 tests)
  - ModernJavaFeaturesTest (9 tests)

All tests passing ✅

## 📝 Documentation

- [MODERNIZATION_SUMMARY.md](MODERNIZATION_SUMMARY.md) - Detailed step-by-step breakdown
- Each commit has descriptive messages
- Each tag has detailed annotations

## 🎓 Learning Path

1. **Start at the baseline** (`v0.0-legacy-baseline`) to see the legacy code
2. **Follow each step** in order to understand the progression
3. **Read commit messages** for context on each change
4. **Run tests at each step** to see everything works
5. **Compare diffs** between steps to see exactly what changed

## 🤝 Use Cases

This repository is perfect for:
- Learning modern Java features
- Understanding JPA migration paths
- Modernizing legacy applications
- Teaching enterprise Java best practices
- Reference for Jakarta EE migration

## 📄 License

This is a sample/educational project demonstrating modernization techniques.

## 🔗 Repository

GitHub: https://github.com/nedelva/ges-model-sample

---

**Happy Learning! 🚀**
