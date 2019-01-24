<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
  <meta charset="UTF-8">
  <meta http-equiv="Content-Type" content="text/html;charset=utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <!-- <META HTTP-EQUIV="refresh" CONTENT="60"> -->
  <title>Calendar</title>
</head>

<link rel='stylesheet' href='./fullcalendar/fullcalendar.css' />
<script src='./fullcalendar/lib/jquery.min.js'></script>
<script src='./fullcalendar/lib/moment.min.js'></script>
<script src='./fullcalendar/fullcalendar.js'></script>

<!-- ì¸ì´ì¤ì  -->
<script src='./fullcalendar/lang/ko.js'></script>


<script type="text/javascript">

var roomname = '${roomname}';
var username = '${username}';
var chief = '${chief}';
console.log(roomname);
console.log(username);


var events_array = [];


//getAll =================================
$.ajax({
    type: 'POST',
    url: "./getAllEvent.do",
    data:{
    	"roomname": roomname
    },
    success: function(data) {
		var list = JSON.parse(data);
		//console.log(list);
		for(var i = 0 ; i<list.length; i++){
			var start = new Date(list[i].start);
			var end = new Date(list[i].end);
			
			
			var row = {
					calnum: list[i].calnum+"",
					username: list[i].username+"",
					title: list[i].title+"",
					start: moment(list[i].start),
					end: moment(list[i].end)
			};
			

			if((list[i].username+"") == username ){
				row.color = 'red';
			}
			
			if((end-start)%86400000 == 0){
				row.allDay = true
			}
			
					events_array.push(row); // set default event
			
		} //for end
		


  // Create calendar when document is ready
  $(document).ready(function() {

	  

	  
	  
    // We will refer to $calendar in future code
    var $calendar = $("#calendar").fullCalendar({ //start of options

        //weekends : false, // do not show saturday/sunday

        header: {
          left: 'prevYear,nextYear',
          center: 'title',
          right: 'listDay,today,month,agendaDay,agendaWeek prev,next'
        },
        // Make possible to respond to clicks and selections
        selectable: true,
        // allow "more" link when too many events
        eventLimit: true,
        navLinks: true,
        // Make events editable, globally
        editable: true,
        
        //defaultView: 'listWeek',
        
      //Drop =================================
        eventDrop: function(event, delta, revertFunc) {
        	

        		if (event.username == username || chief == username) {
        			
        			if(confirm("정말 변경하시겠습니까?")){
        				var data = {
                      		  calnum: event.calnum,
                                title: event.title,
                                start: event.start,
                                end: event.end
                              };
                      console.log(data);
                      $.ajax({
                          type: 'GET',
                          url: "./modifyEvent.do",
                          data: {
                            "event": JSON.stringify(data),
                          },
                          success: function(data) {
            					console.log(data);
                              // Call the "updateEvent" method
                              $calendar.fullCalendar("updateEvent", event);
                              
                              return;
                          }
                        });
        			}else{
            		      revertFunc();
            			  return;   
                      }
                      
                    
        	    }else{
        	    	
        			  alert("본인의 이벤트만 변경할 수 있습니다.")
        		      revertFunc();
        			  return;        	    	
        	    	
        	    }


        },
        
        eventResize: function(event, delta, revertFunc) {
        	
        
    		if (event.username == username || chief == username) {
    			
    			if(confirm("정말 변경하시겠습니까?")){
    				var data = {
                  		  calnum: event.calnum,
                            title: event.title,
                            start: event.start,
                            end: event.end
                          };
                  console.log(data);
                  $.ajax({
                      type: 'GET',
                      url: "./modifyEvent.do",
                      data: {
                        "event": JSON.stringify(data),
                      },
                      success: function(data) {
        					console.log(data);
                          // Call the "updateEvent" method
                          $calendar.fullCalendar("updateEvent", event);
                          
                          return;
                      }
                    });
    			}else{
        		      revertFunc();
        			  return;   
                  }
                  
                
    	    }else{
    	    	
    			  alert("본인의 이벤트만 변경할 수 있습니다.")
    		      revertFunc();
    			  return;        	    	
    	    	
    	    }

        },
        //This is the callback that will be triggered when a selection is made
        /*select: function(start, end, jsEvent, view) {
          alert(start.format("MM/DD/YYYY hh:mm a") + " to " + end.format("MM/DD/YYYY h\
          h:mm a") + " in view " + view.name);
        },*/


        eventRender: function(event, element, view) {
              	if (view.name == 'listDay') {
              		element.find(".fc-list-item-time").append("<span class='closeon'>X</span>");
              	} else {
              		element.find(".fc-content").prepend("<span class='closeon'>X</span>");
              	}
             		element.find(".closeon").on('click', function(e) {
             			
             			
                		if (event.username == username || chief == username) {
                			
                			if(confirm("정말 삭제하시겠습니까?")){
                				
                				var data = {
                                		calnum: event.calnum,
                                        title: event.title,
                                        start: event.start,
                                        end: event.end
                                      };
                                
                                $.ajax({
                                    type: 'POST',
                                    url: "./removeEvent.do",
                                    data: {
                                      "event": JSON.stringify(data),
                                    },
                                    success: function(data) {
                                  	  console.log(data);
                                  	$('#calendar').fullCalendar('removeEvents',event._id);

                                    }
                                  });
                              	e.stopPropagation();
                              	return;
                              
                			}else{
                				e.stopPropagation();
                      		  	return;   
                              }
                              
                            
                	    }else{
                	    	
                	    	e.stopPropagation();
                  		  	alert("본인의 이벤트만 삭제하실 수 있습니다.")
                  		  	return;        	    	
                	    	
                	    }		
             			
             			
              		});
              },


        select: function(start, end, jsEvent, view) {

          // Ask for a title. If empty it will default to "New event"
          var title = prompt("Enter a title for this event", "New event");
          // If did not pressed Cancel button
          if (title != null) {
            // Create event
            var data = {
              'title': title.trim() != "" ? title : "New event",
              'start': start,
              'end': end,
              'roomname': roomname,
              'username': username
              
            };

            $.ajax({
              type: 'POST',
              url: "./addEvent.do",
              data: {
                "event": JSON.stringify(data),
              },
              success: function(data) {
            	  console.log(data);
                  var event = {
                		  'calnum': data,
                          'title': title.trim() != "" ? title : "New event",
                          'start': start,
                          'end': end,
                          'username': username,
                          'color': 'red'
                        };
                  console.log(event);
                $calendar.fullCalendar("renderEvent", event, true);
              }
            });


            // Push event into fullCalendar's array of events
            // and displays it. The last argument is the
            // "stick" value. If set to true the event
            // will "stick" even after you move to other
            // year, month, day or week.

          };
          // Whatever happens, unselect selection
          $calendar.fullCalendar("unselect");
        }, // End select callback

        // Callback triggered when we click on an event
        eventClick: function(event, jsEvent, view) {
        	
        	
    		if (event.username == username || chief == username) {
    			
    			
    			// Ask for a title. If empty it will default to "New event"
    	          var newTitle = prompt("변경할 제목을 입력해 주세요", event.title);

    	          // If did not pressed Cancel button
    	          if (newTitle != null) {
    	            // Update event
    	            event.title = newTitle.trim() != "" ? newTitle : event.title;
    	            
    	              var data = {
    	            		  calnum: event.calnum,
    	                      title: event.title,
    	                      start: event.start,
    	                      end: event.end
    	                    };
    	            
    	            $.ajax({
    	                type: 'GET',
    	                url: "./modifyEvent.do",
    	                data: {
    	                  "event": JSON.stringify(data),
    	                },
    	                success: function(data) {
    						console.log(data);
    	                    // Call the "updateEvent" method
    	                    $calendar.fullCalendar("updateEvent", event);
    	                }
    	              });
    	          }else{
        		      revertFunc();
        			  return;    
    	          }
    			
    	    }else{
    			  alert("본인의 이벤트만 변경할 수 있습니다.");
    		      revertFunc();
    			  return;    
    	    }
    	
        	
        }, // End callback eventClick
        events: events_array
      } //End of options
    );
  });//$ready

	console.log(events_array);
    }
    
  });//ajax end

  
</script>

<style media="screen">
  #calendar {
    width: 90%;
    display: block;
    margin-left: auto;
    margin-right: auto;
  }

  .centered {
    text-align: center;
  }
</style>

<body>

  <!-- The calendar container -->
  <div id="calendar"></div>
  <!-- Calendar creation script -->
  <!-- <script src="fcbasic.js"></script> -->


</body>

</html>
