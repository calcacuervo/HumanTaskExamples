package com.wordpress.demian.task;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.mapping.Array;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.wordpress.salaboy.smarttasks.metamodel.MetaTaskDecoratorBase;
import com.wordpress.salaboy.smarttasks.metamodel.MetaTaskDecoratorService;
import com.wordpress.salaboy.smarttasks.uihelper.api.ConnectionData;
import com.wordpress.salaboy.smarttasks.uihelper.api.SmartTaskUIHelper;
import com.wordpress.salaboy.smarttasks.uihelper.api.TaskListDataSet;
import com.wordpress.salaboy.smarttasks.uihelper.api.TaskListUIHelper;
import com.wordpress.salaboy.smarttasks.uihelper.configuration.UIHelperConfiguration;
import com.wordpress.salaboy.smarttasks.uihelper.configuration.UIHelperConfigurationProvider;
import com.wordpress.salaboy.smarttasks.uihelper.configuration.saxhandler.JBPM5ConfigurationHandler;

/**
 * Controller to handle the list task requests.
 * 
 * @author calcacuervo
 */
@Controller
public class ListTasksController {

	private static final Logger logger = LoggerFactory
			.getLogger(ListTasksController.class);

	//TODO it should not be needed
	@RequestMapping(value = "/new/")
	public String start(Model model) {
		return "start";
	}

	/**
	 * This method gets the task list using the UIHelper classes.
	 * @param entity
	 * @param profile
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list/{entity}/{profile}", method = RequestMethod.GET)
	public String list(@PathVariable("entity") String entity,
			@PathVariable("profile") String profile, Model model) {
		logger.info("Let's get the Task List!");
		MetaTaskDecoratorService.getInstance().registerDecorator("base", new MetaTaskDecoratorBase());
		File root = new File(Thread.currentThread().getContextClassLoader()
				.getResource("uihelper").getFile());
		UIHelperConfigurationProvider configurationProvider = new UIHelperConfigurationProvider(root);
		configurationProvider.addUIHelperConfigurationUriHandler(new JBPM5ConfigurationHandler());
		UIHelperConfiguration config = configurationProvider.createConfiguration();
        SmartTaskUIHelper helper = new SmartTaskUIHelper(config);
        ConnectionData connectionData = new ConnectionData();
        connectionData.setEntityId(entity);
        helper.connect(connectionData);
        TaskListUIHelper taskListHelper = helper.getTaskListHelper("taskList1", profile);
        TaskListDataSet set = taskListHelper.getDataSet(0, taskListHelper.getDataCount());
        String[][] data = set.getData();
        model.addAttribute("data", data);
        String[] headers = taskListHelper.getColumnHeaders();
        model.addAttribute("headers", headers);
		model.addAttribute("user",entity);
		model.addAttribute("profile", profile);
		return "list";
	}

}
