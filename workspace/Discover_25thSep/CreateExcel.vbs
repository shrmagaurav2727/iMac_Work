CreateExcel FileName,Sheetname

Function CreateExcel(FileName,Sheetname)
FileName = WScript.Arguments(0)
Sheetname = WScript.Arguments(1)
'MsgBox "Push"
 Set fileSystemObject = CreateObject("Scripting.FileSystemObject")
 Call DeletePopupProcess()
Call DeleteExlProcess()
'Msgbox fileSystemObject.FileExists(FileName)
	If fileSystemObject.FileExists(FileName) Then
	  	   Set objExcel = CreateObject("Excel.Application")
	  	    'objExcel.Visible = True
			Set objWorkbook = objExcel.Workbooks.Open(FileName)
			Set objWorksheet = objWorkbook.Worksheets.Add
		    Not_Found=0
		    Duplicate=0
			For i=1 To objWorkbook.Sheets.Count-1 
			
						sheetnm= objWorkbook.Sheets(i).Name
						''Msgbox Left(sheetnm,5)
						If (sheetnm=Sheetname ) Then
							'	Set objExcel1 = CreateObject("Excel.Application")
							'	Set objWorkbook1 = objExcel.Workbooks.Open ("C:\Test2")				
								objExcel.DisplayAlerts = False  
							   ' MsgBox sheetnm
								objWorkbook.sheets(sheetnm).Delete
								objExcel.DisplayAlerts = True 
								objWorkbook.Save
								'objWorkbook.Close
								
								
							   	'Set objExcel = CreateObject("Excel.Application")
							   	'Set objWorkbook = objExcel.Workbooks.Open(FileName)
							   '	Set objWorksheet = objWorkbook.Worksheets.Add
							    objWorksheet.Name = Sheetname
							'	objExcel.Application.Visible = True
								objWorkbook.WorkSheets(Sheetname).Activate
							 objExcel.Cells(1,1).Interior.ColorIndex = 16
								objWorkbook.WorkSheets(Sheetname).Cells(1, 1).Value = "Test Suite"
								objExcel.Cells(1,2).Interior.ColorIndex = 16
								objWorkbook.WorkSheets(Sheetname).Cells(1, 2).Value ="Test Case"
								objExcel.Cells(1,3).Interior.ColorIndex = 16
								objWorkbook.WorkSheets(Sheetname).Cells(1, 3).Value = "Keyword"
								objExcel.Cells(1,4).Interior.ColorIndex = 16
								objWorkbook.WorkSheets(Sheetname).Cells(1, 4).Value = "Arg1"
								objExcel.Cells(1,5).Interior.ColorIndex = 16
								objWorkbook.WorkSheets(Sheetname).Cells(1, 5).Value = "Arg2"
								objExcel.Cells(1,6).Interior.ColorIndex = 16
								objWorkbook.WorkSheets(Sheetname).Cells(1, 6).Value = "Arg3"
								objExcel.Cells(1,7).Interior.ColorIndex = 16
								objWorkbook.WorkSheets(Sheetname).Cells(1, 7).Value = "Arg4"
								objExcel.Cells(1,8).Interior.ColorIndex = 16
								objWorkbook.WorkSheets(Sheetname).Cells(1, 8).Value = "Arg5"
								objExcel.Cells(1,9).Interior.ColorIndex = 16
					  			objWorkbook.WorkSheets(Sheetname).Cells(1, 9).Value = "Arg6"
					  			objExcel.Cells(1,10).Interior.ColorIndex = 16
								objWorkbook.WorkSheets(Sheetname).Cells(1, 10).Value = "Arg7"
								objExcel.Cells(1,11).Interior.ColorIndex = 16
								objWorkbook.WorkSheets(Sheetname).Cells(1, 11).Value = "Status"
								objExcel.Cells(1,12).Interior.ColorIndex = 16
								objWorkbook.WorkSheets(Sheetname).Cells(1, 12)="Execution date and time"
							    objWorkbook.Save
							    Duplicate=1
							'	Msgbox "1"
								

						Else
						Not_Found=Not_Found+1
					   End If
					   
					   
				Next
				
					'Msgbox "Not_Found"&Not_Found
					'Msgbox "objWorkbook.Sheets.Count"&objWorkbook.Sheets.Count
					'Msgbox "Duplicate"&Duplicate
                    'Msgbox ((Not_Found=objWorkbook.Sheets.Count)And(Duplicate=0))
					If ((Not_Found=objWorkbook.Sheets.Count-1)And(Duplicate=0)) Then
							'	Set objWorksheet = objWorkbook.Worksheets.Add
							'Msgbox "here"
							'	objWorksheet.Add
					
							    objWorksheet.Name = Sheetname
							'	objExcel.Application.Visible = True
							 	objExcel.Cells(1,1).Interior.ColorIndex = 16
								objWorkbook.WorkSheets(Sheetname).Cells(1, 1).Value = "Test Suite"
								objExcel.Cells(1,2).Interior.ColorIndex = 16
								objWorkbook.WorkSheets(Sheetname).Cells(1, 2).Value ="Test Case"
								objExcel.Cells(1,3).Interior.ColorIndex = 16
								objWorkbook.WorkSheets(Sheetname).Cells(1, 3).Value = "Keyword"
								objExcel.Cells(1,4).Interior.ColorIndex = 16
								objWorkbook.WorkSheets(Sheetname).Cells(1, 4).Value = "Arg1"
								objExcel.Cells(1,5).Interior.ColorIndex = 16
								objWorkbook.WorkSheets(Sheetname).Cells(1, 5).Value = "Arg2"
								objExcel.Cells(1,6).Interior.ColorIndex = 16
								objWorkbook.WorkSheets(Sheetname).Cells(1, 6).Value = "Arg3"
								objExcel.Cells(1,7).Interior.ColorIndex = 16
								objWorkbook.WorkSheets(Sheetname).Cells(1, 7).Value = "Arg4"
								objExcel.Cells(1,8).Interior.ColorIndex = 16
								objWorkbook.WorkSheets(Sheetname).Cells(1, 8).Value = "Arg5"
								objExcel.Cells(1,9).Interior.ColorIndex = 16
					  			objWorkbook.WorkSheets(Sheetname).Cells(1, 9).Value = "Arg6"
					  			objExcel.Cells(1,10).Interior.ColorIndex = 16
								objWorkbook.WorkSheets(Sheetname).Cells(1, 10).Value = "Arg7"
								objExcel.Cells(1,11).Interior.ColorIndex = 16
								objWorkbook.WorkSheets(Sheetname).Cells(1, 11).Value = "Status"
								objExcel.Cells(1,12).Interior.ColorIndex = 16
								objWorkbook.WorkSheets(Sheetname).Cells(1, 12)="Execution date and time"
							'	objExcel.Application.Visible = True
						
							    objExcel.DisplayAlerts = True 
								objWorkbook.Save
							'	Msgbox "2"
					End If
						
			objExcel.ActiveWorkbook.Close
		 	Set objWorksheet=Nothing
			Set objWorkbook = Nothing
			Set objExcel=Nothing

	
	else
								Set objExcel = CreateObject("Excel.Application")
								Set objWorkbook = objExcel.Workbooks.Add
								Set objWorksheet = objWorkbook.Worksheets.Add
								objWorksheet.Name = Sheetname
							'	objExcel.Application.Visible = True
								objWorkbook.WorkSheets(Sheetname).Activate
								objExcel.Cells(1,1).Interior.ColorIndex = 16
								objWorkbook.WorkSheets(Sheetname).Cells(1, 1).Value = "Test Suite"
								objExcel.Cells(1,2).Interior.ColorIndex = 16
								objWorkbook.WorkSheets(Sheetname).Cells(1, 2).Value ="Test Case"
								objExcel.Cells(1,3).Interior.ColorIndex = 16
								objWorkbook.WorkSheets(Sheetname).Cells(1, 3).Value = "Keyword"
								objExcel.Cells(1,4).Interior.ColorIndex = 16
								objWorkbook.WorkSheets(Sheetname).Cells(1, 4).Value = "Arg1"
								objExcel.Cells(1,5).Interior.ColorIndex = 16
								objWorkbook.WorkSheets(Sheetname).Cells(1, 5).Value = "Arg2"
								objExcel.Cells(1,6).Interior.ColorIndex = 16
								objWorkbook.WorkSheets(Sheetname).Cells(1, 6).Value = "Arg3"
								objExcel.Cells(1,7).Interior.ColorIndex = 16
								objWorkbook.WorkSheets(Sheetname).Cells(1, 7).Value = "Arg4"
								objExcel.Cells(1,8).Interior.ColorIndex = 16
								objWorkbook.WorkSheets(Sheetname).Cells(1, 8).Value = "Arg5"
								objExcel.Cells(1,9).Interior.ColorIndex = 16
					  			objWorkbook.WorkSheets(Sheetname).Cells(1, 9).Value = "Arg6"
					  			objExcel.Cells(1,10).Interior.ColorIndex = 16
								objWorkbook.WorkSheets(Sheetname).Cells(1, 10).Value = "Arg7"
								objExcel.Cells(1,11).Interior.ColorIndex = 16
								objWorkbook.WorkSheets(Sheetname).Cells(1, 11).Value = "Status"
								objExcel.Cells(1,12).Interior.ColorIndex = 16
								objWorkbook.WorkSheets(Sheetname).Cells(1, 12)="Execution date and time"
								objExcel.DisplayAlerts = True
								objWorkbook.SaveAs(FileName)
							'	objExcel.ActiveWorkbook.Close
							 	Set objWorksheet=Nothing
								Set objWorkbook = Nothing
								Set objExcel=Nothing
							'	Msgbox "3"
							
	End If	


		
	Set fileSystemObject=Nothing
	
	Call DeleteExlProcess()
    
    
    
End Function	


Function DeleteExlProcess()
Const strComputer = "." 
  Dim objWMIService, colProcessList
  Set objWMIService = GetObject("winmgmts:" & "{impersonationLevel=impersonate}!\\" & strComputer & "\root\cimv2")
  'Msgbox "SELECT * FROM Win32_Process WHERE Name = '"&ProcessExe&"'"
  Set colProcessList = objWMIService.ExecQuery("SELECT * FROM Win32_Process WHERE Name = 'EXCEL.EXE'")
   med=colProcessList.count
  
	  For Each objProcess in colProcessList 
		  If med>0 Then
		   objProcess.Terminate()
		   med=med-1
		  End If
	  Next
  
End Function 

Function DeletePopupProcess()
Const strComputer = "." 
  Dim objWMIService, colProcessList
  Set objWMIService = GetObject("winmgmts:" & "{impersonationLevel=impersonate}!\\" & strComputer & "\root\cimv2")
  'Msgbox "SELECT * FROM Win32_Process WHERE Name = '"&ProcessExe&"'"
  Set colProcessList = objWMIService.ExecQuery("SELECT * FROM Win32_Process WHERE Name = 'wscript.EXE'")
   med=colProcessList.count
  
	  For Each objProcess in colProcessList 
		  If med>0 Then
		   objProcess.Terminate()
		   med=med-1
		  End If
	  Next
  
End Function 