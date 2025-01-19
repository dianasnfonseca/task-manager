# Task Manager - Detailed Requirements and Specifications

## Phase 1: Core System

### Exercise 1: Task Data Model (3 hours)

#### 1.1 Task Data Class
**Required Properties:**
- `id: UUID` - Unique identifier
- `title: String` - Task title (1-100 characters)
- `description: String?` - Optional description
- `status: TaskStatus` - Current status
- `priority: TaskPriority` - Task priority level
- `createdAt: LocalDateTime` - Creation timestamp
- `dueDate: LocalDateTime?` - Optional due date
- `completedAt: LocalDateTime?` - Completion timestamp

**Validation Rules:**
- Title cannot be empty or blank
- Title must be between 1-100 characters
- Description (if provided) must be under 500 characters
- Due date (if provided) must be in the future
- Created at must not be in the future

#### 1.2 Task Status Enum
```kotlin
enum class TaskStatus {
    TODO,
    IN_PROGRESS,
    ON_HOLD,
    COMPLETED,
    CANCELLED
}
```

#### 1.3 Task Priority Enum
```kotlin
enum class TaskPriority(val value: Int) {
    LOW(1),
    MEDIUM(2),
    HIGH(3),
    URGENT(4)
}
```

### Exercise 2: Task Repository (4 hours)

#### 2.1 Repository Interface
**Required Operations:**
```kotlin
interface TaskRepository {
    fun save(task: Task): Task
    fun findById(id: UUID): Task?
    fun findAll(): List<Task>
    fun update(task: Task): Task
    fun delete(id: UUID)
    fun findByStatus(status: TaskStatus): List<Task>
    fun findByPriority(priority: TaskPriority): List<Task>
    fun findOverdueTasks(): List<Task>
    fun findTasksDueToday(): List<Task>
}
```

#### 2.2 In-Memory Implementation
- Use MutableMap<UUID, Task> for storage
- Implement thread-safety considerations
- Add error handling for all operations
- Include validation checks

### Exercise 3: Task Manager Service (5 hours)

#### 3.1 Task Management Operations
**Required Functions:**
```kotlin
class TaskManager(private val repository: TaskRepository) {
    fun createTask(createDTO: CreateTaskDTO): Task
    fun updateTask(id: UUID, updateDTO: UpdateTaskDTO): Task
    fun deleteTask(id: UUID)
    fun completeTask(id: UUID)
    fun moveToInProgress(id: UUID)
    fun putOnHold(id: UUID)
    fun cancelTask(id: UUID)
    fun changePriority(id: UUID, priority: TaskPriority)
    fun updateDueDate(id: UUID, newDueDate: LocalDateTime)
}
```

#### 3.2 Task DTOs
```kotlin
data class CreateTaskDTO(
    val title: String,
    val description: String?,
    val priority: TaskPriority,
    val dueDate: LocalDateTime?
)

data class UpdateTaskDTO(
    val title: String?,
    val description: String?,
    val priority: TaskPriority?,
    val dueDate: LocalDateTime?
)
```

### Exercise 4: Console UI (6 hours)

#### 4.1 Main Menu Options
1. Create New Task
2. View All Tasks
3. View Task Details
4. Update Task
5. Delete Task
6. Change Task Status
7. Change Task Priority
8. View Tasks by Status
9. View Tasks by Priority
10. View Overdue Tasks
11. Exit

#### 4.2 Task Display Format
```
ID: {uuid}
Title: {title}
Status: {status}
Priority: {priority}
Created: {createdAt}
Due: {dueDate}
Description: {description}
```

#### 4.3 Required User Inputs
**Create Task:**
- Title (required)
- Description (optional)
- Priority (required, default: MEDIUM)
- Due Date (optional)

**Update Task:**
- Task ID
- Field to update
- New value

**Change Status:**
- Task ID
- New status

## Phase 2: Enhanced Features

### Exercise 5: Categories (4 hours)

#### 5.1 Category Model
```kotlin
data class Category(
    val id: UUID,
    val name: String,
    val description: String?,
    val color: String // Hex color code
)
```

#### 5.2 Task Updates
Add to Task class:
- `categoryId: UUID?`

#### 5.3 Category Operations
```kotlin
interface CategoryRepository {
    fun save(category: Category): Category
    fun findById(id: UUID): Category?
    fun findAll(): List<Category>
    fun update(category: Category): Category
    fun delete(id: UUID)
}
```

### Exercise 6: Tags (4 hours)

#### 6.1 Tag Model
```kotlin
data class Tag(
    val id: UUID,
    val name: String,
    val color: String
)
```

#### 6.2 Task Updates
Add to Task class:
- `tags: MutableSet<Tag>`

#### 6.3 Required Operations
- Add tag to task
- Remove tag from task
- Find tasks by tag
- List all tags
- Create new tag
- Delete tag

### Exercise 7: Task Search (5 hours)

#### 7.1 Search Criteria
```kotlin
data class TaskSearchCriteria(
    val titleContains: String? = null,
    val status: TaskStatus? = null,
    val priority: TaskPriority? = null,
    val category: UUID? = null,
    val tags: Set<UUID> = emptySet(),
    val dueBefore: LocalDateTime? = null,
    val dueAfter: LocalDateTime? = null,
    val isOverdue: Boolean? = null
)
```

#### 7.2 Search Operations
- Basic search by title
- Advanced search with criteria
- Sort results by different fields
- Filter by multiple conditions

## Phase 3: Data Persistence

### Exercise 8: File Storage (6 hours)

#### 8.1 Required File Operations
- Save tasks to JSON file
- Load tasks from JSON file
- Auto-save on changes
- Backup creation
- Error handling for file operations

#### 8.2 File Structure
```json
{
  "tasks": [
    {
      "id": "uuid",
      "title": "string",
      "description": "string?",
      "status": "enum",
      "priority": "enum",
      "createdAt": "datetime",
      "dueDate": "datetime?",
      "completedAt": "datetime?",
      "categoryId": "uuid?",
      "tags": ["uuid"]
    }
  ],
  "categories": [],
  "tags": []
}
```

### Exercise 9: Import/Export (5 hours)

#### 9.1 Export Formats
- CSV export
- JSON export
- Basic report generation

#### 9.2 Import Support
- CSV import
- JSON import
- Data validation during import

## Phase 4: Advanced Features

### Exercise 10: Subtasks (6 hours)

#### 10.1 Subtask Properties
Add to Task class:
- `parentId: UUID?`
- `hasSubtasks: Boolean`
- `isCompleted: Boolean`

#### 10.2 Required Operations
- Add subtask to task
- Complete subtask
- Show task hierarchy
- Calculate completion percentage

### Exercise 11: Statistics (4 hours)

#### 11.1 Required Metrics
- Tasks by status
- Tasks by priority
- Overdue tasks
- Completion rate
- Average completion time
- Tasks by category
- Most used tags

#### 11.2 Display Formats
- Summary tables
- Basic charts (if possible)
- Trend analysis

## Technical Requirements

### Error Handling
1. Input validation errors
2. File operation errors
3. Data integrity errors
4. Invalid operation errors

### Data Validation
1. Title format and length
2. Date validity
3. Status transitions
4. Priority assignments

### Performance Considerations
1. Efficient search operations
2. Quick sort and filter
3. Optimized file operations
4. Memory management

## User Interface Requirements

### Input Requirements
1. Clear prompts
2. Input validation
3. Error messages
4. Confirmation for important actions

### Display Requirements
1. Clear formatting
2. Consistent layout
3. Status indicators
4. Priority indicators

## Testing Requirements

### Unit Tests
1. Task operations
2. Data validation
3. Business logic
4. File operations

### Integration Tests
1. End-to-end workflows
2. File persistence
3. Search operations
4. Data integrity