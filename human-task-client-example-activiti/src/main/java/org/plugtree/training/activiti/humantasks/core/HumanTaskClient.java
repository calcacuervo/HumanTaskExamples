package org.plugtree.training.activiti.humantasks.core;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.task.Task;
import org.h2.tools.Server;

public class HumanTaskClient {

	ProcessEngine processEngine;

	public HumanTaskClient() {
		try {
			Server server = Server.createTcpServer(
				     new String[] { "-tcpAllowOthers" }).start();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		processEngine = ProcessEngines.getDefaultProcessEngine();
		processEngine.getRepositoryService().createDeployment()
				.addClasspathResource("rules/humanTask.xml.bpmn20.xml")
				.deploy();
	}

	public void start() throws RuntimeException {
	}

	public void startProcess() throws RuntimeException {
		processEngine.getRuntimeService().startProcessInstanceByKey(
				"adhoc_test");
	}

	public List<Task> getAssignedTasks(String user) {
		return processEngine.getTaskService().createTaskQuery()
				.taskAssignee(user).list();
	}

	public void completeTask(String user, Task task, Map data) {
		System.out.println("Completing task " + task.getId());
		this.processEngine.getTaskService().complete(task.getId(), data);
	}

}