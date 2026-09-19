# 🧪 Automated Test Suite — BigBang.ba E-Commerce Platform
### Software Verification, Validation and Testing | 2024/2025

**21 automated test cases** covering functional, security, performance, and responsive design testing on a live e-commerce platform — [BigBang.ba](https://www.bigbang.ba/), one of Bosnia's leading online retailers.

---

## 📊 Test Suite Overview

| Category | Tests | Description |
|----------|-------|-------------|
| **Authentication** | 3 | Account creation, login, logout flows |
| **Shopping** | 4 | Cart operations, add to cart, price checks, checkout |
| **Navigation & UI** | 3 | Navigation, product sorting, search functionality |
| **Forms** | 2 | Contact form, newsletter subscription |
| **Security** | 3 | Session fixation, session persistence, security compliance |
| **Performance** | 1 | Performance benchmarking |
| **Responsive Design** | 1 | Multi-viewport responsive layout testing |
| **E2E User Journey** | 2 | Guest checkout, complete user journey |
| **Other** | 2 | Address management, delivery info, promotions, social media |
| **Total** | **21** | |

---

## 🔒 Security Testing Highlights

Security tests were **proactively included beyond course requirements**, covering:

- **Session Fixation** — verifies that session IDs are regenerated after login (prevents session hijacking)
- **Session Persistence** — validates session state is correctly maintained across page navigations
- **Security Compliance** — checks for common web security vulnerabilities and compliance issues

---

## 🛠️ Tech Stack

| Tool | Purpose |
|------|---------|
| **Java** | Core test language |
| **Selenium WebDriver** | Browser automation |
| **JUnit** | Test framework & assertions |
| **IntelliJ IDEA** | Development environment |
| **ChromeDriver** | Browser driver |

---

## 📁 Repository Structure

```
src/
└── test/java/
    ├── AccCreationTest.java              # User account creation
    ├── AddToCartAndPriceCheckTest.java   # Cart + price validation
    ├── AddressTest.java                  # Address management
    ├── CartTest.java                     # Cart operations
    ├── ContactFormTests.java             # Contact form submission
    ├── DeliveryInfoTest.java             # Delivery information
    ├── GuestCheckoutTest.java            # Guest checkout flow
    ├── LoginTest.java                    # User authentication
    ├── LogoutTest.java                   # Logout functionality
    ├── NavigationTest.java               # Site navigation
    ├── NewsletterTests.java              # Newsletter subscription
    ├── PerformanceTests.java             # Performance benchmarking
    ├── ProductSortingTests.java          # Product sorting
    ├── PromotionsTest.java               # Promotional features
    ├── ResponsiveDesignTest.java         # Multi-viewport testing
    ├── SearchTests.java                  # Search functionality
    ├── SecurityCompliance.java           # Security compliance checks
    ├── SessionFixationTest.java          # Session fixation prevention
    ├── SessionPersistenceAcrossPagesTest.java  # Session persistence
    ├── SocialMediaTests.java             # Social media links
    └── UserJourney1Test.java             # End-to-end user journey
```

---

## 🚀 How to Run

### Prerequisites
- Java 11+
- Maven or Gradle
- Chrome browser + ChromeDriver (matching version)
- IntelliJ IDEA (recommended)

### Setup

```bash
git clone https://github.com/EmanHrustemovic/Software-Verification-Validation-and-Testing-2024-2025-
cd Software-Verification-Validation-and-Testing-2024-2025-
```

Open in IntelliJ IDEA and run individual test classes or the full suite.

> **Note:** Tests target the live [BigBang.ba](https://www.bigbang.ba/) website. Test behaviour may vary based on current site state.

---

## 📚 Course Context

This project was developed as part of the **Software Verification, Validation and Testing** course at International Burch University (IBU), 2024/2025.

The test suite demonstrates practical application of:
- **Black-box testing** techniques on a production website
- **Test case design** covering happy paths and edge cases
- **Security-aware testing** mindset (session management, compliance)
- **Automated regression testing** principles

---

## 👤 Author

**Eman Hrustemović** — Junior QA / IT Engineer

- 🔗 [GitHub](https://github.com/EmanHrustemovic)
- 🔗 [LinkedIn](https://linkedin.com/in/eman-hrustemovic)
- 📧 emanhrustemovic6@gmail.com
