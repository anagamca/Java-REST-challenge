# Interview-Java

### Description:
Take the data from test-resources/people.csv and import it to a singleton in PersonService.java upon initialization.
Finish as many of the TODOs as you can to complete the rest stack/application.
There are a couple of bonus TODOs lying around so kudos if you get to them. Feel free to add or remove dependencies as you see fit.
You may also attach other technology or applications that you would like to this application.

### Installing Dependencies
- Install java 1.8
- Install Apache Maven 3.3.9
- Any editor of your choice.
- After this a `maven clean install` will download and install any necessary dependencies (defined in the pom.xml) to your local .m2 repository
    This can generally be found in <core drive>:\Users\<username>\.m2 for windows. For unix it would reside in ~/.m2 where home is your person root directory

### Setting up database
- There is not one here right now but kudos if you can add one! (There is hibernate support for a database layer already added)

### Running Local Server
- Clean build and run tests: `mvn clean install`
- Clean build and ignore tests: `mvn clean install -DskipTests`
- Run local jetty: `mvn jetty:run`
- REST should be running on :8080 by default specify -Djetty.http.port=9999 to change port

### Environment variables
- There are none right now. Feel free to add some.

### Generating Documentation
- Generate documentation (Can be found in target/site/javadoc/index.html): `mvn javadoc:javadoc`
- Generate dependency map (Can be found in target/site/index.html): `mvn clean site`

### Project Structure Breakdown:
- All documentation is set in doc and all java docs can be found in **doc.javadoc**
- All REST endpoints should reside in **com.interview.core.rest**
- All Models (not DAOs) should reside in **com.interview.core.models** for all your service and REST needs
- All DAOs should reside in **com.interview.core.persist.dao** for all your database modelling needs
- All services should reside in **com.interview.core.services.***
- All resources including json configuration and external configuration files should reside in **src.main.resources**
- All web application configuration files should be in **src.main.webapp.WEB-INF**
- All Tests should be in **src.test** in the same structure as the tested services would appear in **src.main** to prevent confusion and increase searchability
