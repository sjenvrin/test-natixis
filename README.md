# Test technique Natixis

Restful API to manage a to-do list.

A task as the following properties :
- id : task identifiant
- label : description of the task
- complete : is the task done

## Config and install

**1. Clone the repository**

```bash
git clone https://github.com/sjenvrin/test-natixis.git
```

**2. Configure PostgreSQL**

PostgreSQL is the database used for this project. You need it installed. Then create a database named `postgres_demo`. 

Open `src/main/resources/application.properties` file and change the spring datasource username and password as per your PostgreSQL installation.

**3. Run the app**

Type the following command from the root directory of the project to run the App.

```bash
mvn spring-boot:run
```

## API endpoints

```
GET  /tasks?complete=true
```
**Description :** Get the list of all the task. To get only the task todo, use the 'complete' parameter in the query.

```
POST  /tasks
```
**Description :** Create, add a new task.

Example of body :
```json
{
	"label": "Test technique chez Natixis",
	"complete": true
}
```

```
GET  /tasks/{taskId}
```
**Description :** Get one task by its Id.

```
PUT  /tasks/{taskId}
```
**Description :** Update the status of a task.

```
DELETE  /tasks/{taskId}
```
**Description :** Delete one task.

## Unit Test

Execute the following command line to execute the tests.
```bash
mvnw clean test
```


