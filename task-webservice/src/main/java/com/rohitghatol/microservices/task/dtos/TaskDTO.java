/**
 * 
 */
package com.rohitghatol.microservices.task.dtos;

import com.rohitghatol.microservices.task.model.CommentCollectionResource;

/**
 * Represents Todo Task.
 *
 * @author rohitghatol
 */
public class TaskDTO {

	/** The task id. */
	private String taskId;

	/** The last name. */
	private String description;

	/** The completed. */
	private boolean completed;

	/** The user name with whom the task is associated. */
	private String userName;
	
	/** The comments. */
	private CommentCollectionResource comments;
	
	/**
	 * Instantiates a new task dto.
	 */
	public TaskDTO() {
		super();

	}

	/**
	 * Instantiates a new task dto.
	 *
	 * @param taskId the task id
	 * @param description the description
	 */
	public TaskDTO(String taskId, String description, String userName) {
		super();
		this.taskId = taskId;
		this.description = description;
		this.completed = false;
		this.userName = userName;
	}

	/**
	 * Gets the task id.
	 *
	 * @return the task id
	 */
	public String getTaskId() {
		return taskId;
	}

	/**
	 * Sets the task id.
	 *
	 * @param taskId the new task id
	 */
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description.
	 *
	 * @param description the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Checks if is completed.
	 *
	 * @return true, if is completed
	 */
	public boolean isCompleted() {
		return completed;
	}

	/**
	 * Sets the completed.
	 *
	 * @param completed the new completed
	 */
	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the comments
	 */
	public CommentCollectionResource getComments() {
		return comments;
	}

	/**
	 * @param comments the comments to set
	 */
	public void setComments(CommentCollectionResource comments) {
		this.comments = comments;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((comments == null) ? 0 : comments.hashCode());
		result = prime * result + (completed ? 1231 : 1237);
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((taskId == null) ? 0 : taskId.hashCode());
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TaskDTO other = (TaskDTO) obj;
		if (comments == null) {
			if (other.comments != null)
				return false;
		} else if (!comments.equals(other.comments))
			return false;
		if (completed != other.completed)
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (taskId == null) {
			if (other.taskId != null)
				return false;
		} else if (!taskId.equals(other.taskId))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TaskDTO [taskId=" + taskId + ", description=" + description + ", completed=" + completed + ", userName="
				+ userName + ", comments=" + comments + "]";
	}

}
