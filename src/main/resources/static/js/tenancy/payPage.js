$(document).ready(function(){
$(".paymentTabUl li").each(function(i) {
                $(this).click(function() {
					if(i==0){
						$(".paymentTabUl").find("li").eq(1).removeClass("checked"),
						$(".paymentTabUl").find("li").eq(0).addClass("checked")
					}else{
						$(".paymentTabUl").find("li").eq(0).removeClass("checked"),
						$(".paymentTabUl").find("li").eq(1).addClass("checked")
					}
        })
    })
})