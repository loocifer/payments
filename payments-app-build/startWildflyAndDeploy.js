#!/usr/bin/jjs -fv
//# jjs.exe d:\sandbox\NetBeansProjects\startWildflyAndDeploy.js -- "d:/containers/wildfly-8.2.0.Final" "d:\payments-app.war"

var jbossHome = "${arguments[0]}";

print("#> WAR = ${arguments[1]}");

// START SERVER
//exec("${jbossHome}/start-standalone-ci.bat");

print("#> UN-DEPLOY 'payments-app.war'");
exec("${jbossHome}/bin/jboss-cli.bat --connect --command=\"undeploy payments-app.war\"");

print("#> DEPLOY 'payments-app.war'");
exec("${jbossHome}/bin/jboss-cli.bat --connect --command=\"deploy ${arguments[1]}\"");

// WAIT DEPLOY
try {
    java.lang.Thread.sleep(5000);
} catch(e) {
}

function exec(cmd) {
    print("> Executing ${cmd}\n");
    $EXEC(cmd);
    print($OUT);
    print($ERR);
    print("> ${cmd} executed\n");
}
