## 🛠️ Setup Instructions

### ✅ Requirements

- Java 21 installed and added to system PATH

---

### 🔧 Step 1: External Dependencies

Downloaded these 3 Jackson JARs and placed them in the `lib/` folder:

- [jackson-core-2.17.0.jar](https://repo1.maven.org/maven2/com/fasterxml/jackson/core/jackson-core/2.17.0/jackson-core-2.17.0.jar)
- [jackson-databind-2.17.0.jar](https://repo1.maven.org/maven2/com/fasterxml/jackson/core/jackson-databind/2.17.0/jackson-databind-2.17.0.jar)
- [jackson-annotations-2.17.0.jar](https://repo1.maven.org/maven2/com/fasterxml/jackson/core/jackson-annotations/2.17.0/jackson-annotations-2.17.0.jar)

---

### ⚙️ Step 2: Configure IntelliJ

1. Open the project folder in IntelliJ (`Modular Address Book/`)
2. Add `lib/` JARs to classpath:
    - Go to `File > Project Structure > Modules > Dependencies`
    - Click `+` → `JARs or directories` → Select all 3 JARs from `lib/`
    - Click `Apply` and then `OK`

---
