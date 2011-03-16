<%@page import="io.FileIOSet"%>
<%@page import="common.DateSets"%>
<%@page contentType="text/html;charset=UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<LINK REL="stylesheet" href="css/bo.main.css" type="text/css">
</head>

<body>
<br>
<table width="98%" align="center" cellpadding="3" cellspacing="2">
<tr><td width="48%"><b>Bid Time</b></td><td width="25%"><b>Bidder</b></td><td width="25%"><b>Bid Amount</b></td></tr>
<tr><td colspan="3" style="border-top: 1px solid #444">&nbsp;</td></tr>
<%
	String lotId = request.getParameter("lotId");
        String fileName = lotId+"/bidhistory.dat";
	FileIOSet fio = new FileIOSet();
        if (fio.fileExists(fileName)) {
            String[] allStrings = fio.readStringsFromFile(fileName);
            int biddingRecords = allStrings.length;
            if (biddingRecords > 0) {
                DateSets dt = new DateSets();
                String parts[];
                for (int i = biddingRecords; i > 0; i--) {
                    parts = allStrings[i-1].split(";");
                    out.println("<tr><td>"+dt.convMillsToDate(Long.parseLong(parts[0]), "MMM dd, yyyy  HH:mm:ss")+"</td><td>#"+parts[1]+"</td><td>"+parts[2]+"</td></tr>");
                }
            }
        }
        else {
            out.println("<tr><tdcolspan='3'>No Bids Found</td></tr>");
        }
%>
</table>
</body>
</html>