<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script type="text/javascript">
	$(function(){
		
		const loca = $(location).attr("href");
        let count = 0;
		console.log(history.state);
		$(".move_btn").click(function(e){
			e.preventDefault();
			let param;
			const url = $(this).attr("href");
			if($(this).data("value") != null){
				param = $(this).data("value");
				console.log(param);
			}
			console.log(url);
			if($(this).data("value") != null){
				$.ajax({
					url: url + "?param=" + param,
					type:"get",
					success:function(resp){
				        console.log("현재 내 count1 = " + count);
						if(count == 0){
							history.replaceState({view : loca},null,loca);
							count++;
						}
				        console.log("현재 내 count2 = " + count);
						console.log(history.state);
						//console.log(resp);
						$(".viewport").html(resp);
						
						const data = {
								view : url,
								param : param
						}
						
						history.pushState(data, url, url+"/"+param);
						console.log(history.state);
					},
					error:function(e){
						console.log(e);
					}
				})
			}else{
				$.ajax({
					url: url,
					type:"get",
					success:function(resp){
				        console.log("현재 내 count1 = " + count);
						if(count == 0){
							history.replaceState({view : loca},null,loca);
							count++;
						}
				        console.log("현재 내 count2 = " + count);
						console.log(history.state);
						//console.log(resp);
						$(".viewport").html(resp);
						
						const data = {
								view : url
								}
						
						history.pushState(data, url, url);
						console.log(history.state);
					},
					error:function(e){
						console.log(e);
					}
				})
			}
		});
		
		
		$(window).on("popstate", function(){
            const lastHistoryState = history.state;
            console.log("lastHistoryState = "+lastHistoryState.view);
            if(lastHistoryState) {
            	if(lastHistoryState.param != null){
            		$.ajax({
	                    url:lastHistoryState.view + "?param=" + lastHistoryState.param,
	                    type:"get",
	                    success:function(resp){
	                        console.log("loca = " + loca);
	                            $(".viewport").html(resp);
	                            //$(window).scrollTop(lastHistoryState.scrollPosition);
	                            //setTimeout(function(){
	                                //}, 100);
	                                console.log(history.state);
	                        }
	                });
            	}else{
	                $.ajax({
	                    url:lastHistoryState.view,
	                    type:"get",
	                    success:function(resp){
	                        console.log("loca = " + loca);
	                            $(".viewport").html(resp);
	                            //$(window).scrollTop(lastHistoryState.scrollPosition);
	                            //setTimeout(function(){
	                                //}, 100);
	                                console.log(history.state);
	                        }
	              	});
            	}
            }
        });
	});
	
	
</script>
<h1>메인페이지</h1>

<div>
	아이디 : ${memberDto}
</div>
<div>
	<a href="${pageContext.request.contextPath }/member/my_page" class="move_btn">내페이지</a>
</div>
<div>
	<a href="${pageContext.request.contextPath }/dm/dm" class="move_btn">디엠</a>
</div>
<div>
	<a href="${pageContext.request.contextPath }/dm/dm" data-value="2" class="move_btn">2번이랑 디엠하기</a>
</div>

<div>
	알림
</div>


<div class="viewport"><h1>메인</h1></div>