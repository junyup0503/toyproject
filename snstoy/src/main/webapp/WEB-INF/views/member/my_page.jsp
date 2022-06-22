<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script type="text/javascript">
	$(function(){
		
		var loca = $(location).attr("href");
        history.replaceState({view : loca},null,loca);
         
		$(".move_btn").click(function(e){
			e.preventDefault();
			
			var url = $(this).attr("href");
			console.log(url);
			$.ajax({
				url: url + "_copy",
				type:"get",
				success:function(resp){
					$(".viewport").html(resp);
					
					var data = {
							view : url
					}
					
					history.pushState(data, url, url);
				},
				error:function(e){
					console.log(e);
				}
			})
		});
		
		
		$(window).on("popstate", function(){
            const lastHistoryState = history.state;
            console.log("lastHistoryState = "+lastHistoryState.view);
            if(lastHistoryState) {
                $.ajax({
                    url:lastHistoryState.view+"_copy",
                    type:"get",
                    success:function(resp){
                        console.log("loca = " + loca);
                        if(lastHistoryState.view == loca){
                                var mainUrl = $(".hide").attr("href");
                                $.ajax({
                                    url:mainUrl,
                                    type:"get",
                                    success:function(resp){
                                        $(".viewport").html(resp);
                                    }
                                });
                        }else{
                            $(".viewport").html(resp);
                            //$(window).scrollTop(lastHistoryState.scrollPosition);
                            //setTimeout(function(){
                                //}, 100);
                                console.log(history.state);
                            }
                        return;
                    },
                });
            }
        });
	});
	
	
</script>
<h1>메인페이지</h1>

<div>
	아이디 : ${memberDto}
</div>
	<a href="${pageContext.request.contextPath }/member/my_page_copy" class="hide"></a>
<div>
	<a href="${pageContext.request.contextPath }/member/my_page" class="move_btn">내페이지</a>
</div>
<div>
	디엠
</div>
<div>
	알림
</div>


<div class="viewport">
	<div>
		<h1>마이페이지</h1>
	</div>
</div>