Set WshShell = CreateObject("WScript.Shell" )
WshShell.Run chr(34) & "d:/containers/wildfly-8.2.0.Final/start-standalone-ci.bat" & Chr(34), 0
Set WshShell = Nothing
