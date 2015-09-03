#!/usr/bin/jjs -fv

//# jjs.exe d:\sandbox\NetBeansProjects\stopWildflyAndUndeploy.js -- "d:/containers/wildfly-8.2.0.Final" "payments-app.war"

var jbossHome = "${arguments[0]}";

// DEPLOY WAR
exec("${jbossHome}/bin/jboss-cli.bat --connect --command=\"undeploy ${arguments[1]}\"");
exec("${jbossHome}/bin/jboss-cli.bat --connect --command=\"shutdown\"");

function exec(cmd) {
    print("> Executing ${cmd}\n");
    $EXEC(cmd);
    print($OUT);
    print($ERR);
    print("> ${cmd} executed\n");
}
