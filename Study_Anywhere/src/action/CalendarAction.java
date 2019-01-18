package action;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.ActionForward;
import service.BoardListService;

public class CalendarAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		response.setContentType("text/html;charset=UTF-8");

		ActionForward actionForward = null;
		//==========================================================
/*		

		Calendar cal = Calendar.getInstance();
		
		
			
		
		
		
		
		
			
			yearnum = Integer.toString(year);
			monthnum = Integer.toString(month);
			
				
				cal.set(Calendar.YEAR, year);
				cal.set(Calendar.MONTH, month-1);
				cal.set(Calendar.DATE, 1);
				
				
				
				
				//2차 배열에 날짜 입력
				int row = 0;
				for(int i = 1 ; inputDate <= lastDay ; i++) {
					
					//시작 요일이 오기전에는 공백 입력
					if(i<startDay) {
						calDate[row][i-1]="  ";
					}else {//날짜 배열에 입력
						
						String indate = Integer.toString(inputDate);
						if(inputDate <10) {
							indate = " " + indate;
						}
						calDate[row][(i-1)%width]=indate;
						inputDate++;
						
						if(i%width == 0) {
							row++;
						}//가로 마지막 열에 오면 행 바꿈
						
					}
					
				}// for end
				
				
				
				
			
			
		
		
		public void printhismonth(int year, int month) {

			Calendar cal = Calendar.getInstance();
			
			cal.set(year, month, 1);
			
			int ryear = cal.get(Calendar.YEAR);
			int rmonth = cal.get(Calendar.MONTH);
			
			if(rmonth== 0) {
				rmonth = 12;
				ryear = ryear-1;
			}
			
			System.out.println("      "+ryear+"년 "+rmonth+"월");
			
			cal.set(2017, 3, 1);
			System.out.println(cal.getTime());
			System.out.println(sdf.format(cal.getTime()));
			
			
		}
		
		
		
		public void printCal() {
			
			
			//========================================================================================
			//헤더 출력 일월화수목금토
			
			for(int i = 0 ; i<width ; i++) {
				System.out.print(calHead[i]+"    ");
			}

			int row = 0;
			for(int j = 1 ; j < lastDay+startDay ; j++) {
				
				System.out.print(calDate[row][(j-1)%width]+" ");
				
				if((j-1)%width == width-1) {
					System.out.println();
					row++;
				}
				
			}

			
			
		}//printCal method end
		
		
		*/
		System.out.println("캘린더 액션에서 액션포워드 앞");
		
		actionForward = new ActionForward();
		actionForward.setPath("calendar.jsp");
		return actionForward;
	}

}
