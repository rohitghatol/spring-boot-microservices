package com.rohitghatol.microservices.task.apis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rohitghatol.microservices.task.dtos.TaskDTO;

/**
 * REST endpoint for the task functionality
 * 
 * @author anilallewar
 *
 */
@RestController
@RequestMapping("/")
public class TaskController {

	@Autowired
	private CommentsService commentsService;

	private List<TaskDTO> tasks = Arrays.asList(new TaskDTO("task11", "description11", "1"),
			new TaskDTO("task12", "description12", "1"), new TaskDTO("task13", "description13", "1"),
			new TaskDTO("task21", "description21", "2"), new TaskDTO("task22", "description22", "2"));

	/**
	 * Get all tasks
	 * 
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, headers = "Accept=application/json")
	public List<TaskDTO> getTasks() {
		return tasks;
	}

	/**
	 * Get tasks for specific taskid
	 * 
	 * @param taskId
	 * @return
	 */
	@RequestMapping(value = "{taskId}", method = RequestMethod.GET, headers = "Accept=application/json")
	public TaskDTO getTaskByTaskId(@PathVariable("taskId") String taskId) {
		TaskDTO taskToReturn = null;
		for (TaskDTO currentTask : tasks) {
			if (currentTask.getTaskId().equalsIgnoreCase(taskId)) {
				taskToReturn = currentTask;
				break;
			}
		}

		if (taskToReturn != null) {
			taskToReturn.setComments(this.commentsService.getCommentsForTask(taskId));
		}
		return taskToReturn;
	}

	/**
	 * Get tasks for specific user that is passed in
	 * 
	 * @param taskId
	 * @return
	 */
	@RequestMapping(value = "/usertask/{userName}", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<TaskDTO> getTasksByUserName(@PathVariable("userName") String userName) {
		List<TaskDTO> taskListToReturn = new ArrayList<TaskDTO>();
		for (TaskDTO currentTask : tasks) {
			if (currentTask.getUserName().equalsIgnoreCase(userName)) {
				taskListToReturn.add(currentTask);
			}
		}

		return taskListToReturn;
	}
}
