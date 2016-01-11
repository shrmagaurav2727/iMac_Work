Call Email(val)

Function Email(Value)

Set objMessage = CreateObject("CDO.Message") 
objMessage.Subject = "Selenium Test Results" 
objMessage.From = "smoudgil@cpaglobal.com" 
objMessage.To = "prathore@cpaglobal.com"
objMessage.Cc = "jthakur@cpaglobal.com;rtanwar1@cpaglobal.com;smoudgil@cpaglobal.com"
objMessage.AddAttachment "C:\Users\smoudgil\workspace\Discover\src\cpa\qa\xls\Test Report.xlsx"
objMessage.TextBody = "Please check the execution results of selenium run "&Date

'==This section provides the configuration information for the remote SMTP server.
'==Normally you will only change the server name or IP.
objMessage.Configuration.Fields.Item _
("http://schemas.microsoft.com/cdo/configuration/sendusing") = 2 

'Name or IP of Remote SMTP Server
objMessage.Configuration.Fields.Item _
("http://schemas.microsoft.com/cdo/configuration/smtpserver") = "ind-domvp03"

'Server port (typically 25)
objMessage.Configuration.Fields.Item _
("http://schemas.microsoft.com/cdo/configuration/smtpserverport") = 25 

objMessage.Configuration.Fields.Update
'==End remote SMTP server configuration section==
objMessage.Send


End Function