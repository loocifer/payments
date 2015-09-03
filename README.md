# payments
This is a JEE 7 template project with a simple JAXRS rest service and several testing projects (Systemtest, UI-Test, Acceptancetest).
The project shows basic principles of JavaEE development on base of a virtual checkout/eCommerce service.
Furthermore the repo contains projects for different testing approaches which then can be added to separate build pipelines in jenkins.

**payments-app:**

Is the JavaEE 7 web application which contains a JAXRS Endpoint with methods for a simulated eCommerce checkout process.
Storage is implemented with stateless EJB and JPA entity. 
Also a simple JSF/HTML5 page for checkout is given. The project also provides IT tests which need NO running container (as per definition of IT tests).

**payments-st:**

Provides system tests which in fact needs a running JavaEE 7 container (wildfly, glassfish, ...).
The CheckoutResourceIT.java tests the running JAXRS endpoint.

**payments-uit:**

That project defines UI tests for the JSF pages by using Arquillian/Drone framework which is based on Selenium under the hood.

**payments-accept:**

Here we have some BDD (Behaviour Driven Dev.) tests based on the robotframework.
It uses the gherkin style for the tests and also tests the UI.

**payments-build:**

The project contains scripts which are needed for a Jenkins build pipeline setu.
