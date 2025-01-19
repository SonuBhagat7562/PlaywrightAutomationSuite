# Automated Testing Framework
This repository contains an automated test suite for testing the functionality of [Your Project Name]. The framework supports both **headed** (browser-based) and **headless** execution modes, generating detailed reports.
---
## Prerequisites
Ensure the following are installed and configured on your system:
- **Java JDK** - Version 8 or higher
- **Maven** - Version 3.6.0 or higher
- **Google Chrome** - Version 100 or higher
- **ChromeDriver** - Compatible with your Chrome version
- **Operating System** - Windows, macOS, or Linux
---
## Setup Instructions
1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/your-repository.git
   
**##Navigate to the project directory****
cd your-repository

**##Install the dependencies:**
mvn clean install

**Configure the config.properties file:**
baseURL: Application URL
browser: chrome or firefox
headless: true or false

**Run tests in browser-based mode:**
mvn test -Dheadless=false

**Run tests without browser UI:**
mvn test -Dheadless=true
