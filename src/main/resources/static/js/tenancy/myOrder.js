
    $(document).ready(function() {
        var reqtype = $("#reqtype").val();
        reqtype && "" != reqtype && $(".order-success-alertbox").show(),
        $(".order-success-alertbox .close,.order-success-alertbox .btn-know").click(function() {
            $(".order-success-alertbox").hide()
        }),
        $(".onsttone").click(function() {
            var sttwrap = $(".sttwrap");
            "none" == sttwrap.css("display") ? (sttwrap.css("display", "block"),
            $(this).find(".down").show(),
            $(this).find(".up").hide()) : (sttwrap.css("display", "none"),
            $(this).find(".down").hide(),
            $(this).find(".up").show())
        }),
		$(".config-btn").click(function() {
			$(".cffade").show(),
			$(".configbox").show()
		}),
		$(".cfclose").click(function() {
			$(".configbox").hide(),
			$(".cffade").hide()
		})
    })

