<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Calendar</title>
</head>
<body>
	
<%
String[] calHead = {"일","월","화","수","목","금","토"};
String[][] calDate = new String[6][7];//달력 틀

int width = calHead.length; // 가로 넓이
int startDay; //월 시작 일
int lastDay; // 월 마지막 일
int inputDate = 1; //입력 날짜

int newLine = 0;

Calendar cal = Calendar.getInstance();//캘린더 객체 서버에서 받아온다

//테스트용 세터
/* cal.set(Calendar.YEAR, year);
cal.set(Calendar.MONTH, month-1);
cal.set(Calendar.DATE, 1); */

int year = cal.get(Calendar.YEAR);
int month = cal.get(Calendar.MONTH);
int date = cal.get(Calendar.DATE);

startDay = cal.get(Calendar.DAY_OF_WEEK); // 월 시작 요일
lastDay = cal.getActualMaximum(Calendar.DATE); // 월 마지막 날짜




//테스트용
Calendar todayCal = Calendar.getInstance();
SimpleDateFormat sdf = new SimpleDateFormat("yyyMMdd");
int intToday = Integer.parseInt(sdf.format(todayCal.getTime()));


	
%>
	
	
	
	
	
<form name="calendarFrm" id="calendarFrm" action="" method="post">
<DIV id="content" style="width:712px;">

<!-- 오늘 버튼 -->
<table width="100%" border="0" cellspacing="1" cellpadding="1">
<tr>
	<td align ="right">
		<input type="button" onclick="javascript:location.href='<c:url value='/CalendarExam2.jsp' />'" value="오늘"/>
	</td>
</tr>
</table>


<!-- 상단 년 월 설정 ========================================================================================== -->
<table width="100%" border="0" cellspacing="1" cellpadding="1" id="KOO" bgcolor="#F3F9D7" style="border:1px solid #CED99C">

<tr>
	<td height="60">
	
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<!-- 공백 여분 -->
			<tr>
				<td height="10"></td>
			</tr>
			<tr>
			
				<td align="center" >
					<!-- 이전해<< -->
					<a href="<c:url value='/CalendarExam2.jsp' />?year=<%=year-1%>&amp;month=<%=month%>" target="_self">
					<b>&lt;&lt;</b>
					</a>

					<!-- 1월일 경우 -->
                    <%if(month > 0 ){ %>

                    <a href="<c:url value='/CalendarExam2.jsp' />?year=<%=year%>&amp;month=<%=month-1%>" target="_self">
					<b>&lt;</b><!-- 이전달< -->
					</a>

					<!-- 1월 아닌 달  -->
                    <%} else {%>

					<b>&lt;</b>

                    <%} %>

                    &nbsp;&nbsp;

                    <%=year%>년
                    <%=month+1%>월

                    &nbsp;&nbsp;

					<!-- 12월 아닐때ㅑ -->
                    <%if(month < 11 ){ %>

                    <a href="<c:url value='/CalendarExam2.jsp' />?year=<%=year%>&amp;month=<%=month+1%>" target="_self">
                    <b>&gt;</b><!-- 다음달> -->
                    </a>
					
					<!-- 12월 -->
                    <%}else{%>

					<b>&gt;</b>

                    <%} %>
					
					<!-- 다음해>> -->
                    <a href="<c:url value='/CalendarExam2.jsp' />?year=<%=year+1%>&amp;month=<%=month%>" target="_self">
                    <b>&gt;&gt;</b>
                    </a>
				</td>
			</tr>
		</table>
		</td>
	</tr>
</table>

 <!--  ========================================================================================== -->


<br>

<table border="0" cellspacing="1" cellpadding="1" bgcolor="#FFFFFF">
	
<!-- HEADER  ========================================= -->
	<THEAD>
		<TR bgcolor="#CECECE">
			<TD width='100px'>
				<DIV align="center"><font color="red">일</font></DIV>
			</TD>
			<TD width='100px'>
				<DIV align="center">월</DIV>
				lastDay<%= lastDay %>
				startday<%= startDay %>
			</TD>
			<TD width='100px'>
				<DIV align="center">화</DIV>
			</TD>
			<TD width='100px'>
				<DIV align="center">수</DIV>
			</TD>
			<TD width='100px'>
				<DIV align="center">목</DIV>
			</TD>
			<TD width='100px'>
				<DIV align="center">금</DIV>
			</TD>
			<TD width='100px'>
				<DIV align="center"><font color="#529dbc">토</font></DIV>
			</TD>
		</TR>
	</THEAD>
	
<!--  ========================================================================================== -->
	

<TBODY>
	<TR>

<%
//처음 빈공란 표시
for(int index = 1; index < startDay ; index++ ){

  out.println("<TD >&nbsp;</TD>");

  newLine++;

}

 

for(int index = 1; index <= lastDay; index++){

       String color = "";

       if(newLine == 0){          color = "RED";

       }else if(newLine == 6){    color = "#529dbc";

       }else{                     color = "BLACK"; };

 

       String sUseDate = Integer.toString(year);
       sUseDate += Integer.toString(month+1).length() == 1 ? "0" + Integer.toString(month+1) : Integer.toString(month+1);
       sUseDate += Integer.toString(index).length() == 1 ? "0" + Integer.toString(index) : Integer.toString(index);

       int iUseDate = Integer.parseInt(sUseDate);

      

      

       String backColor = "#EFEFEF";

       if(iUseDate == intToday ) {
             backColor = "#c9c9c9";
       }
       out.println("<TD valign='top' align='left' height='92px' bgcolor='"+backColor+"' nowrap>");

       %>

       <font color='<%=color%>'>

             <%=index %>

       </font>

 

       <%

      

       out.println("<BR>");

       out.println(iUseDate);

       out.println("<BR>");

      

      

       //기능 제거 

       out.println("</TD>");

       newLine++;

 

       if(newLine == 7){

         out.println("</TR>");

         if(index <= lastDay){
           out.println("<TR>");
         }

         newLine=0;

       }

}

//마지막 공란 LOOP
while(newLine > 0 && newLine < 7)
{
  out.println("<TD>&nbsp;</TD>");
  newLine++;
}
%>

</TR>

</TBODY>
</TABLE>
</DIV>
</form>
	
	
	
	
	
	
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
	
	<link href="./fullcalendar/fullcalendar.min.css" rel="stylesheet">
<script src="./fullcalendar/moment.min.js"></script>
<script src="./fullcalendar/fullcalendar.min.js"></script>
<script src="/fullcalendar/ko.js"></script>  
	
	
	<!-- 
	<button type="button" id="btn2">달력 소환</button>

<div class="modal fade" id="modal2">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span></button>
				<h4 class="modal-title">FullCalendar</h4>
			</div>
			<div class="modal-body">
				<div id="fullcalendar_div2"></div>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
var fc2 = null
var mm2 = null
var m_chk1 = 0

var start2 = moment().format('YYYY-MM-DD HH:')+'00'
var end2 = moment().add(2, 'hour').format('YYYY-MM-DD HH:')+'00'

var events2 =[
	{
		title: "일정2",
		start: start2,
		backgroundColor:'#ccc',
		end: end2
	},
]

function open_fc2(){
	fc2 = $('#fullcalendar_div2').fullCalendar({
		defaultView: 'agendaWeek',
		header: {
			left: 'prev,next today',
			center: 'title',
			right: 'month,agendaWeek,agendaDay,listWeek',
		},
		events:events2,
		displayEventEnd: true,
		slotDuration: '01:00:00'
	})
}

$(function(){
	$('#btn2').click(function(){
		mm2 = $('#modal2').modal()
		mm2.on('shown.bs.modal', function () {
			//shown.bs.modal이벤트가 다중으로 발생하는 걸 방지하기 위한 부분
			m_chk1++
			if( m_chk1 == 1){
				if(fc2){
					fc2.fullCalendar('rerenderEvents')
					$('#fullcalendar_div2').fullCalendar( 'addEventSource', events2 )
				}
				else {
					open_fc2(events2)
				}
			}
		})
	})
})
</script>
	
 -->
	
</body>
</html>