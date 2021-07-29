<h1>CRUD project</h1>

<h2>Contents</h2>

<h2>1. Resoures</h2>

During this project several different technoligoes and resources where used:
<ul>
	<h4>For development</h4>
	<li>Java</li>
	<li>Javascript</li>
	<li>Css</li>
	<li>Bootstrap</li>
	<li>Git</li>
	<li>Html</li>
	<li>Spring</li>
	<h4>For Testing</h4>
	<li>Selenium</li>
	<li>Junit</li>
	<li>Mockito</li>
	<li>MockMVC</li>
</ul>

To keep track of task a jira board was used which can be found here
[https://sjknapp.atlassian.net/jira/software/projects/TP/boards/3](https://sjknapp.atlassian.net/jira/software/projects/TP/boards/3)
So stories could be placed in order of importance and estimates of story points could be placed against them so that it was easy to 
see the state of the project.

Another tool that was used was postman which was used to proform manual tests on the endpoints to ensure that they where returning 
what was expected. The postman exported collection can be found in 
[https://github.com/SJKnapp/ProjectOneSupplementary](https://github.com/SJKnapp/ProjectOneSupplementary).

<h2>2. Frontend</h2>

The front end is a to do list with the project repository can be found at 
[https://github.com/SJKnapp/FrontendProjectOne](https://github.com/SJKnapp/FrontendProjectOne)
 where the repository should be cloned into the static folder found in src/main/resources/
<br />
The front has been programed in html, javascript and css. As well as making use of bootstrap 
in order to place the colums in the table.
The front end colour scheme is dependent on a tasks status with the aviable status being:
<ul>
	<li>No due date not marked done</li>
	<li>time remaing not marked done</li>
	<li>overdue not marked done</li>
	<li>complete</li>
</ul>

![image of frontend](https://github.com/SJKnapp/ProjectOneSupplementary/blob/main/Screenshot%202021-07-29%20at%2019-52-17%20Document.png)

where the first row colour scheme is overdue not marked done
<br/>the second row has been marked as done
<br/>the third row is time remaing not marked done
<br/>the forth row is ready for data entry where clicking 
the save button will create an entry with the data in that 
row with the colour scheme of No due date not marked done

The save button for existing rows will update them to the new data.
<br />The delete button will remove them from the database
<br />The (not) done will togle the task status between mark as done or not

pressing any button will refress the tasks in the list


<h2>2. Backend</h2>

<ul><h3>Database</h3>

<p>The data base has the table to_do_list and is hosted from a mysql table</p>
<p>It contains the columns:</p>
<ul>
	<li>int id</li>
	<li>VarChar(255) description</li>
	<li>date due_date</li>
	<li>bit(1) is_done</li>
	<li>varchar(255) name</li>
	<li>int priority</li>
	<li>int time_estimate_minutes</li>
</ul>

where id is the primary key

<h3>Java server</h3>

The java server which was hosted on the port 80 contained the endpoints where status is a 
boolean and id was the data's id in the database:

<ul>
	<li>/getAll - get - gets all the data from the database</li>
	<li>/create - post - creates a new data entry at the next avalible point</li>
	<li>/task/{id}/get - get -get the data at a set id</li>
	<li>/task/{id}/update - put - updates to the new json</li>
	<li>/task/{id}/delete - delete - deletes the entry at id </li>
	<li>/task/{id}/status/{status} - patch - updates where the task has been compleated</li>
</ul>	


</ul>

<h2>4. Testing</h2>
This 