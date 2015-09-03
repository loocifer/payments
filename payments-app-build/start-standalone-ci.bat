del /S /Q standalone\log
del /S /Q standalone\tmp
cls

d:/containers/wildfly-8.2.0.Final/bin/standalone.bat --server-config=standalone-payments-app.xml -Djboss.bind.address=0.0.0.0 -Djboss.bind.address.management=0.0.0.0 -Dorg.apache.cxf.Logger=org.apache.cxf.common.logging.Slf4jLogger
