package io.github.dianasnfonseca.models

import java.time.LocalDateTime
import javax.print.attribute.standard.DateTimeAtCreation
import java.util.UUID

/**
 * Required Properties:
 *
 * @property id UUID - Unique identifier
 * @property title String - Task title (1-100 characters)
 * @property description String? - Optional description
 * @property status TaskStatus - Current status
 * @property priority TaskPriority - Task priority level
 * @property createdAt LocalDateTime - Creation timestamp
 * @property dueDate LocalDateTime? - Optional due date
 * @property completedAt LocalDateTime? - Completion timestamp
 *
 * Validation Rules:
 *
 * - Title cannot be empty or blank
 * - Title must be between 1-100 characters
 * - Description (if provided) must be under 500 characters
 * - Due date (if provided) must be in the future
 * - Created at must not be in the future
 */

const val MIN_TITLE_LENGTH = 1
const val MAX_TITLE_LENGTH = 100
const val DESCRIPTION_UNDER_LENGTH = 500

data class Task(
    val id: UUID,
    var title: String,
    var description: String?,
    var status: String, // TODO: Change
    var priority: String, //TODO: Change for enum
    var createdAt: DateTimeAtCreation,
    var dueDate: LocalDateTime?,
    var completedAt: LocalDateTime?
    ){
    init {
        // Title cannot be empty or blank
        require(title.isNotBlank()){"Title cannot be blank or empty." }

        // Title must be between 1-100 characters
        require(title.length>=MIN_TITLE_LENGTH and title.length<=MAX_TITLE_LENGTH ){"Title must be between $MIN_TITLE_LENGTH-$MAX_TITLE_LENGTH characters." }

        // Description (if provided) must be under 500 characters
        require(description?.length<DESCRIPTION_UNDER_LENGTH ){"Description must be under $DESCRIPTION_UNDER_LENGTH characters." }
    }
}

