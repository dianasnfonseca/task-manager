# Task Manager - Kotlin Learning Project

## Project Overview
A command-line task management application built while learning Kotlin. This project is designed to progressively introduce and implement Kotlin concepts through practical application.

## Learning Objectives
This project is structured to learn:
- Kotlin syntax and basic concepts
- Object-oriented programming in Kotlin
- Kotlin's null safety features
- Collections and their operations
- File I/O operations
- Error handling
- Testing in Kotlin

## Project Phases

### Phase 1: Basic Console Application (20-25 hours)
- [ ] Project setup and structure
- [ ] Basic task creation and viewing
- [ ] Simple data management
- [ ] Console interface

**Learning Focus:**
- Variables and data types
- Data classes
- Basic functions
- Null safety
- Collections

### Phase 2: Enhanced Features (25-30 hours)
- [ ] Task due dates
- [ ] Priority levels
- [ ] Task categories
- [ ] Search functionality
- [ ] Task sorting

**Learning Focus:**
- Enums
- Extension functions
- Collection operations
- Date/Time handling

### Phase 3: Data Persistence (20-25 hours)
- [ ] Save tasks to file
- [ ] Load tasks from file
- [ ] Data validation
- [ ] Error handling

**Learning Focus:**
- File I/O
- Exception handling
- Object serialization
- Kotlin interfaces

### Phase 4: Advanced Features (30-35 hours)
- [ ] Subtasks support
- [ ] Task dependencies
- [ ] Tags/Labels
- [ ] Statistics and reporting
- [ ] Data export (CSV/JSON)

**Learning Focus:**
- Higher-order functions
- Lambdas
- Scope functions
- Coroutines basics

## Technical Details

### Project Structure
```
task-manager/
├── src/
│   ├── main/
│   │   └── kotlin/
│   │       ├── models/     # Data classes
│   │       ├── managers/   # Business logic
│   │       ├── utils/      # Helper functions
│   │       └── ui/         # User interface
│   └── test/
│       └── kotlin/        # Test files
```

### Tech Stack
- Language: Kotlin
- Build Tool: Gradle
- Testing: JUnit
- Other Tools:
    - Git for version control
    - IntelliJ IDEA as IDE

## Getting Started

### Prerequisites
- JDK 21 or later
- Kotlin 1.9.x
- Gradle 8.x

### Setup
1. Clone the repository
```bash
git clone https://github.com/dianasnfonseca/task-manager.git
```

2. Navigate to project directory
```bash
cd task-manager
```

3. Build the project
```bash
./gradlew build
```

4. Run the application
```bash
./gradlew run
```

## Development Progress

I will be updating this section as I progress through different phases of the project, documenting:
- New Kotlin concepts learned
- Challenges faced and solutions found
- Code improvements and refactoring decisions
- Testing strategies

## Future Enhancements
- GUI implementation
- Database integration
- Web API development
- Mobile app version

## Learning Resources
- [Kotlin Official Documentation](https://kotlinlang.org/docs/home.html)
- [Kotlin Koans](https://play.kotlinlang.org/koans/overview)
- [Kotlin by Example](https://play.kotlinlang.org/byExample/overview)
