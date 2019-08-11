Human Task Examples\

|Test|Test|Test|
|-|-|-|
|test test|test<br>|test\test|

In this repository you will find examples related to human tasks, especially using smarttasks project:
https://github.com/Salaboy/smart-tasks

Currently there is:
- A test server used as a task repository.
- A test client with an jbpm 5 example process that can be followed finishing human tasks and checking each user task.
- An example web ui application that uses smarttasks to show the user interface.

The idea of this repository is having a good idea of how to start from a process with human tasks, generate/complete these tasks and be able to show it in an Interface.

This is work on progress and ideas are welcome.

Currently, to run a whole example, follow these steps:
-In human-task-server-example, compile it, and then mvn exec:java. It will start the server.
-In human-task-client-example, compile it and then mvn exec:java. It will open a UI with the users from a sample process located in human-task-client-example/src/main/resources/rules/humanTask.bpmn. It allow to complete the process and view a report of finished processes.
-In human-task-client-example-activiti, compile it and then mvn exec:java. It will open a UI with the users from a sample process located in human-task-client-example/src/main/resources/rules/humanTask.xml. It allow to complete the process and view a report of finished processes.
-In human-task-web-ui-example, compile it and run mvn jetty:run. Then go to http://localhost:8080/human-task-web-ui-example/new/ and you can check give a user and profile (predefined in the project) and see the task list.

I will try to explain the ideas behind my projects in my personal blog:

http://dcalca.wordpress.com/

Enjoy!

Demian
