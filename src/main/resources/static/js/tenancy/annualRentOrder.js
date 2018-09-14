$(document).click(function(){
	
$(".b-warp input[type='radio']").click(function() {
            $(".b-warp input[type='radio']").removeAttr("checked"),
            $(this).attr("checked", "checked"),
            $(this).parent().addClass("ltwcur").siblings().removeClass("ltwcur")
        })
})