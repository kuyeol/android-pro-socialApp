package com.ung.feedback

enum class PostsTypeFilter {
  /**
   * Do not filter tasks.
   */
  ALL_POST,

  /**
   * Filters only the active (not completed yet) tasks.
   */
  ACTIVE_TASKS,

  /**
   * Filters only the completed tasks.
   */
  COMPLETED_TASKS
}
