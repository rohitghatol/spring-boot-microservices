package com.rohitghatol.microservices.task.apis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.rohitghatol.microservices.task.dtos.TaskDTO;
import com.rohitghatol.microservices.task.model.CommentCollectionResource;
import com.rohitghatol.microservices.task.model.CommentResource;

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
	private OAuth2RestOperations restTemplate;

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
			taskToReturn.setComments(this.getCommentsForTask(taskId));
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

	/**
	 * Returns the comments for the task; note that this applies a circuit
	 * breaker that would return the default value if the comments-webservice
	 * was down.
	 * 
	 * @param taskId
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "getFallbackCommentsForTask", commandProperties = {
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "2") })
	public CommentCollectionResource getCommentsForTask(String taskId) {
		// Get the comments for this task
		return restTemplate.getForObject(String.format("http://comments-webservice/comments/%s", taskId),
				CommentCollectionResource.class);

	}

	/**
	 * Gets the default comments for task. Need to add the suppress warning
	 * since the method is not directly used by the class.
	 *
	 * @return the default comments for task
	 */
	@SuppressWarnings("unused")
	private CommentCollectionResource getFallbackCommentsForTask(String taskId) {
		// Get the default comments
		CommentCollectionResource comments = new CommentCollectionResource();
		comments.addComment(new CommentResource(taskId, "default comment", Calendar.getInstance().getTime()));

		return comments;
	}
}
