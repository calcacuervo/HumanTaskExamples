<head>
	<title>Task</title>
	
	
		<script type="text/javascript">
		var mymap = new Array();
		
		function init() {
		<#list operations.getOperationsList() as operation>
			document.getElementById('button_${operation}').disabled = true;
		    i=0;
		    var aList = new Array();
				<#list operations.getNextTasks(operation) as next>
					 aList[i] = 'button_${next}';
					 i = i + 1;
				</#list>
				mymap['button_${operation}'] = aList
			
		</#list>
		<#list operations.getRootOperations() as operation>
			document.getElementById('button_${operation}').disabled = false;
		</#list>
		}
		
	function buttonClicked(element)
	{
		<#list operations.getOperationsList() as operation>
			document.getElementById('button_${operation}').disabled = true;
		</#list>
		list = mymap[element];
		x = 0;
		for (l in list) {
		   document.getElementById(list[x]).disabled=false;
		   x = x + 1;
	     }
		return false;
	}
	</script>
	
</head>
<body onLoad="init()">
<h1> 
	<#assign idv="id"/>
	Task details for task ${id}.
</h1>
<style type="text/css">
table.sample {
	border-width: medium;
	border-spacing: 2px;
	border-style: outset;
	border-color: black;
	border-collapse: collapse;
	background-color: white;
}
table.sample th {
	border-width: 1px;
	padding: 4px;
	border-style: solid;
	border-color: red;
	background-color: white;
	-moz-border-radius: 0px 0px 0px 0px;
}
table.sample td {
	border-width: 1px;
	padding: 4px;
	border-style: solid;
	border-color: red;
	background-color: white;
	-moz-border-radius: 0px 0px 0px 0px;
}
</style>
Details:
<table class="sample">
	<tr>
		<#list taskInfo?keys as keys>
			<td><b>${keys}</b></td>
		</#list>
	</tr>
	<tr>	
		<#list taskInfo?keys as keys>
			<td>${taskInfo[keys]}</td>
		</#list>
	</tr>
</table>
<br/>
Task Form:
 <FORM action="#" method="post">
    <P>
    <#list taskInputs?keys as inputKey>
     	<LABEL for="${inputKey}">${inputKey}: </LABEL>
        <INPUT type="text" id="${inputKey}" value="${taskInputs[inputKey]}"/><br/>
    </#list>
    <#list operations.getOperationsList() as operation>
    	<INPUT type="button" id="button_${operation}" value=${operation} onClick="buttonClicked('button_${operation}')">
    </#list>
    </P>
 </FORM>
</table>
<a href="/human-task-web-ui-example/new/">New List</a>
</body>
</html>
