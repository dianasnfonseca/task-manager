# Comprehensive Kotlin Learning Guide

## Basics

### Variables and Types

#### 1. Variable Declaration
```kotlin
// Immutable (can't be reassigned)
val name: String = "Task"
val count = 1 // Type inference

// Mutable (can be reassigned)
var description: String = "Initial"
description = "Updated" // OK

// Constants (compile-time)
const val MAX_TITLE_LENGTH = 100
```

#### 2. Basic Types
```kotlin
// Numbers
val int: Int = 42
val long: Long = 42L
val double: Double = 42.0
val float: Float = 42.0f

// Strings
val str: String = "Hello"
val char: Char = 'A'

// Booleans
val bool: Boolean = true

// Type conversion
val stringNumber = "42"
val number = stringNumber.toInt()
val double = number.toDouble()
```

#### 3. String Operations
```kotlin
// String templates
val name = "Task"
println("Current $name")
println("Task length: ${name.length}")

// Multiline strings
val text = """
    Line 1
    Line 2
    Line 3
""".trimIndent()

// String operations
val uppercase = name.uppercase()
val hasTask = name.contains("Task")
val words = name.split(" ")
```

### Functions

#### 1. Function Declaration
```kotlin
// Basic function
fun add(a: Int, b: Int): Int {
    return a + b
}

// Single-expression function
fun multiply(a: Int, b: Int) = a * b

// Unit-returning function (void)
fun printTask(task: Task) {
    println(task.title)
}

// Default parameters
fun createTask(
    title: String,
    description: String = "",
    priority: Priority = Priority.MEDIUM
) = Task(title, description, priority)
```

#### 2. Named Arguments
```kotlin
// Using named arguments
val task = createTask(
    title = "Learn Kotlin",
    priority = Priority.HIGH,
    description = "Study basics"
)

// Mixing positional and named arguments
createTask("Learn Kotlin", priority = Priority.HIGH)
```

#### 3. Extension Functions
```kotlin
// Adding function to existing class
fun String.toTitleCase(): String {
    return this.split(" ")
        .map { it.capitalize() }
        .joinToString(" ")
}

// Using receiver type with generics
fun <T> List<T>.secondOrNull(): T? {
    return if (size >= 2) this[1] else null
}

// Extension property
val Task.isNew: Boolean
    get() = createdAt.isAfter(LocalDateTime.now().minusHours(24))
```

### Classes and Objects

#### 1. Class Declaration
```kotlin
// Basic class
class Task {
    var title: String = ""
    var description: String = ""
    
    fun isValid() = title.isNotBlank()
}

// Primary constructor
class Task(val title: String) {
    var description: String = ""
    
    // Initialization block
    init {
        require(title.isNotBlank()) { "Title cannot be blank" }
    }
}

// Secondary constructor
class Task(val title: String) {
    var description: String = ""
    
    constructor(title: String, description: String) : this(title) {
        this.description = description
    }
}
```

#### 2. Properties
```kotlin
class Task {
    // Basic property
    var title: String = ""
    
    // Custom getter
    val isValid: Boolean
        get() = title.isNotBlank()
    
    // Custom setter
    var priority: Priority = Priority.MEDIUM
        set(value) {
            require(value != Priority.URGENT || approvedByManager)
            field = value
        }
    
    // Late-initialized property
    lateinit var assignee: String
    
    // Lazy property
    val tokenizer by lazy {
        SomeExpensiveTokenizer()
    }
}
```

#### 3. Data Classes
```kotlin
// Basic data class
data class Task(
    val id: UUID = UUID.randomUUID(),
    var title: String,
    var description: String = "",
    var status: Status = Status.TODO
)

// Using data class features
val task = Task("Learn Kotlin")
val copy = task.copy(title = "Master Kotlin")
val (id, title) = task // Destructuring

// Data class inheritance
data class SubTask(
    val parentId: UUID,
    override val id: UUID,
    override var title: String
) : BaseTask(id, title)
```

#### 4. Sealed Classes
```kotlin
// Sealed class hierarchy
sealed class TaskResult {
    data class Success(val task: Task) : TaskResult()
    data class Error(val message: String) : TaskResult()
    object Loading : TaskResult()
}

// Using sealed class
fun handleResult(result: TaskResult) = when(result) {
    is TaskResult.Success -> displayTask(result.task)
    is TaskResult.Error -> showError(result.message)
    TaskResult.Loading -> showLoadingIndicator()
}
```

### Null Safety

#### 1. Nullable Types
```kotlin
// Nullable declaration
var nullableDescription: String? = null
val nonNullTitle: String = "Task"

// Compile error:
nonNullTitle = null // Error
nullableDescription = null // OK
```

#### 2. Safe Calls
```kotlin
// Safe call operator
val length = nullableDescription?.length // Type: Int?

// Chain of safe calls
val upperCase = nullableDescription
    ?.trim()
    ?.uppercase()
    
// Collection safe calls
val tasks: List<Task>? = null
val firstTask = tasks?.firstOrNull()
```

#### 3. Elvis Operator
```kotlin
// Elvis operator basics
val length = nullableDescription?.length ?: 0

// Elvis with throw
val description = nullableDescription 
    ?: throw IllegalStateException("Description required")

// Chaining Elvis operators
val processedDescription = nullableDescription
    ?.trim()
    ?.takeIf { it.isNotEmpty() }
    ?: "Default description"
```

#### 4. Not-null Assertion
```kotlin
// Not-null assertion (use carefully!)
lateinit var task: Task

fun initTask() {
    task = Task("New Task")
}

fun useTask() {
    println(task.title) // Throws if not initialized
}

// Checking lateinit
if (::task.isInitialized) {
    println(task.title)
}
```

### Collections

#### 1. Lists
```kotlin
// Creating lists
val readOnlyList: List<String> = listOf("a", "b", "c")
val mutableList: MutableList<String> = mutableListOf("a", "b", "c")

// List operations
val first = list.first()
val last = list.last()
val filtered = list.filter { it.length > 1 }
val mapped = list.map { it.uppercase() }
val sorted = list.sorted()
val reversed = list.reversed()
```

#### 2. Sets
```kotlin
// Creating sets
val readOnlySet: Set<String> = setOf("a", "b", "c")
val mutableSet: MutableSet<String> = mutableSetOf("a", "b", "c")

// Set operations
val union = set1 union set2
val intersection = set1 intersect set2
val difference = set1 subtract set2

// Custom objects in sets
data class Tag(val name: String)
val tags = mutableSetOf<Tag>()
```

#### 3. Maps
```kotlin
// Creating maps
val readOnlyMap: Map<String, Int> = mapOf(
    "one" to 1,
    "two" to 2
)

val mutableMap: MutableMap<String, Int> = mutableMapOf()

// Map operations
val value = map["key"]
val defaultValue = map.getOrDefault("key", 0)
val withNewValue = map + ("three" to 3)
val filtered = map.filter { it.value > 1 }
```

#### 4. Collection Operations
```kotlin
// Filtering
val highPriority = tasks.filter { it.priority == Priority.HIGH }

// Mapping
val titles = tasks.map { it.title }

// FlatMap
val allSubtasks = tasks.flatMap { it.subtasks }

// Grouping
val byStatus = tasks.groupBy { it.status }

// Sorting
val sorted = tasks.sortedBy { it.dueDate }
val customSorted = tasks.sortedWith(compareBy(
    { it.priority },
    { it.dueDate }
))

// Aggregation
val count = tasks.count { it.isCompleted }
val anyUrgent = tasks.any { it.priority == Priority.URGENT }
val allCompleted = tasks.all { it.isCompleted }
```

### Control Flow

#### 1. If Expression
```kotlin
// If as expression
val status = if (task.isCompleted) "Done" else "Pending"

// Multiple branches
val priority = if (task.isUrgent) {
    Priority.URGENT
} else if (task.dueDate?.isBefore(tomorrow) == true) {
    Priority.HIGH
} else {
    Priority.MEDIUM
}
```

#### 2. When Expression
```kotlin
// Simple when
val color = when (task.status) {
    Status.TODO -> "blue"
    Status.IN_PROGRESS -> "yellow"
    Status.COMPLETED -> "green"
    else -> "gray"
}

// When with multiple values
val importance = when (task.priority) {
    Priority.LOW, Priority.MEDIUM -> "Normal"
    Priority.HIGH, Priority.URGENT -> "Important"
}

// When with conditions
val action = when {
    task.isOverdue() -> "Mark urgent"
    task.dueDate == null -> "Set due date"
    task.isCompleted -> "Archive"
    else -> "No action"
}
```

#### 3. Loops
```kotlin
// For loop
for (task in tasks) {
    println(task.title)
}

// For with index
for ((index, task) in tasks.withIndex()) {
    println("$index: ${task.title}")
}

// While loop
while (taskQueue.isNotEmpty()) {
    processTask(taskQueue.poll())
}

// Do-while loop
do {
    val input = readLine()
    processInput(input)
} while (input != "exit")
```

### Scope Functions

#### 1. let
```kotlin
// Operating on nullable values
nullableTask?.let { task ->
    println(task.title)
    processTask(task)
}

// Chain of operations
findTask(id)
    ?.let { task -> validate(task) }
    ?.let { task -> process(task) }
```

#### 2. with
```kotlin
// Grouping operations on an object
with(task) {
    title = "New Title"
    description = "Description"
    status = Status.IN_PROGRESS
    priority = Priority.HIGH
}
```

#### 3. run
```kotlin
// Combining let and with
nullableTask?.run {
    if (isValid()) {
        process()
    }
}

// Initialization logic
val task = Task().run {
    title = "Task"
    description = "Description"
    this
}
```

#### 4. apply
```kotlin
// Object configuration
val task = Task().apply {
    title = "Task"
    description = "Description"
    dueDate = LocalDateTime.now().plusDays(1)
}

// Builder-style usage
val task = createTask().apply {
    addSubtask("Subtask 1")
    addSubtask("Subtask 2")
    assignTo("John")
}
```

#### 5. also
```kotlin
// Side effects
val task = Task("Learn Kotlin")
    .also { println("Created task: ${it.title}") }
    .also { saveTask(it) }

// Debugging
processTask(task.also { 
    println("Processing task: $it") 
})
```

### Error Handling

#### 1. Try-Catch
```kotlin
// Basic try-catch
try {
    processTask(task)
} catch (e: IllegalArgumentException) {
    println("Invalid task: ${e.message}")
} catch (e: Exception) {
    println("Error: ${e.message}")
} finally {
    cleanup()
}

// Try as expression
val result = try {
    processTask(task)
    "Success"
} catch (e: Exception) {
    "Error: ${e.message}"
}
```

#### 2. Custom Exceptions
```kotlin
// Custom exception
class TaskException(
    message: String,
    val task: Task? = null
) : Exception(message)

// Using custom exception
throw TaskException("Invalid task status", task)

// Extension function for validation
fun Task.validateOrThrow() {
    if (!isValid()) {
        throw TaskException("Invalid task", this)
    }
}
```

### Best Practices

#### 1. Code Organization
```kotlin
// Package structure
com.example.taskmanager/
  ├── model/          // Data classes
  ├── repository/     // Data access
  ├── service/        // Business logic
  ├── util/           // Utilities
  └── ui/            // User interface

// File naming
TaskRepository.kt
TaskService.kt
DateTimeUtils.kt
```

#### 2. Naming Conventions
```kotlin
// Classes: PascalCase
class TaskManager
class TaskRepository

// Functions/Properties: camelCase
fun processTask()
val taskCount: Int

// Constants: SCREAMING_SNAKE_CASE
const val MAX_TASKS = 100

// Package names: lowercase
package com.example.taskmanager.model
```

#### 3. Documentation
```kotlin
/**
 * Represents a task in the system.
 *
 * @property id Unique identifier
 * @property title Task title
 * @property description Optional description
 */
data class Task(
    val id: UUID,
    var title: String,
    var description: String? = null
)

/**
 * Processes the given task.
 *
 * @param task Task to process
 * @throws TaskException if task is invalid
 * @return Processed task
 */
fun processTask(task: Task): Task
```