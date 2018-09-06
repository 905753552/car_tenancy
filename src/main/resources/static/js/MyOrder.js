checkMemberFormName = function() {
        $("#nameInfo").parents(".order-errorbox").hide(),
        $("#nameInfo").html("");
        var flag = !0
          , xname = $("#xname").val()
          , defaultValue = $("#xname").attr("default-value");
        if (xname && $.trim(xname) && xname != defaultValue) {
            if (isInteger(trim(xname)))
                $("#nameInfo").html("姓名不能是数字！"),
                $("#nameInfo").parents(".order-errorbox").show(),
				$(function() {
					$(".order-errorbox .zc-close").click(function() {
						$(this).parents(".order-errorbox").hide()
					}),
					$(".order-errorbox").focus(function() {
						$(this).hide()
					})
				}),
                flag = !1;
            else if (getBytesLength(xname) > 30)
                $("#nameInfo").html("姓名长度不能超过30个字符！"),
                $("#nameInfo").parents(".order-errorbox").show(),
				$(function() {
					$(".order-errorbox .zc-close").click(function() {
						$(this).parents(".order-errorbox").hide()
					}),
					$(".order-errorbox").focus(function() {
						$(this).hide()
					})
				}),
                flag = !1;
            else if (checkStr(trim(xname)))
                $("#nameInfo").html("不能含有特殊字符！"),
                $("#nameInfo").parents(".order-errorbox").show(),
				$(function() {
					$(".order-errorbox .zc-close").click(function() {
						$(this).parents(".order-errorbox").hide()
					}),
					$(".order-errorbox").focus(function() {
						$(this).hide()
					})
				}),
                flag = !1;
            else
                for (var j = 0; j < trim(xname).length; j++)
                    if (" " == $.trim(xname).substring(j, j + 1)) {
                        $("#nameInfo").html("姓名中不能有空格！"),
                        $("#nameInfo").parents(".order-errorbox").show(),
						$(function() {
							$(".order-errorbox .zc-close").click(function() {
								$(this).parents(".order-errorbox").hide()
							}),
							$(".order-errorbox").focus(function() {
								$(this).hide()
							})
						}),
                        flag = !1;
                        break
                    }
        } else
            $("#nameInfo").html("请输入真实姓名！"),
            $("#nameInfo").parents(".order-errorbox").show(),
			$(function() {
					$(".order-errorbox .zc-close").click(function() {
						$(this).parents(".order-errorbox").hide()
					}),
					$(".order-errorbox").focus(function() {
						$(this).hide()
					})
				}),
            flag = !1;
        return flag
    },
checkMemberIdentitycard = function() {
        $("#identitycardInfo").parents(".order-errorbox1").hide(),
        $("#identitycardInfo").html("");
        var flag = !0
          , xcardtype = $("#op").attr("type-id")
          , xidentitycard = $("#xidentitycard").val()
          , defaultValue = $("#xidentitycard").attr("default-value");
        if (xidentitycard && defaultValue != xidentitycard)
            if (xidentitycard.length > 20)
                flag = !1,
                $("#identitycardInfo").html("您输入的证件号码太长！"),
                $("#identitycardInfo").parents(".order-errorbox1").show(),
				$(function() {
					$(".order-errorbox1 .zc-close").click(function() {
						$(this).parents(".order-errorbox1").hide()
					})
				});
            else if (/^[a-zA-Z0-9]+$/.test(xidentitycard)) {
                if (10 == xcardtype)
                    if (1 == checkIDCard(trim(xidentitycard)))
                        $("#identitycardInfo").html("您输入的证件号码有误，请认真核实！"),
                        $("#identitycardInfo").parents(".order-errorbox1").show(),
						$(function() {
							$(".order-errorbox1 .zc-close").click(function() {
								$(this).parents(".order-errorbox1").hide()
							})
						}),
                        flag = !1;
                    else {
                        var date = $("#currentDate").val()
                          , converted = new Date(parseInt(date.split("-")[0]) - 18,parseInt(date.split("-")[1]) - 1,parseInt(date.split("-")[2]))
                          , birthday = new Date(parseInt(xidentitycard.substring(6, 10)),parseInt(xidentitycard.substring(10, 12)) - 1,parseInt(xidentitycard.substring(12, 14)));
                        18 == xidentitycard.length && birthday.getTime() - converted.getTime() > 0 && ($("#identitycardInfo").html("您输入的证件号码有误，请认真核实！"),
                        $("#identitycardInfo").parents(".order-errorbox1").show(),
						$(function() {
							$(".order-errorbox1 .zc-close").click(function() {
								$(this).parents(".order-errorbox1").hide()
							})
						}),
                        flag = !1)
                    }
            } else
                $("#identitycardInfo").html("您输入的证件号码有误，请认真核实！"),
                $("#identitycardInfo").parents(".order-errorbox1").show(),
				$(function() {
					$(".order-errorbox1 .zc-close").click(function() {
						$(this).parents(".order-errorbox1").hide()
					})
				}),
                flag = !1;
        else
            $("#identitycardInfo").html("请输入证件号码！"),
            $("#identitycardInfo").parents(".order-errorbox1").show(),
			$(function() {
				$(".order-errorbox1 .zc-close").click(function() {
					$(this).parents(".order-errorbox1").hide()
				})
			}),
            flag = !1;
        return flag
    },
checkMemberEmail = function() {
        $("#emailInfo").parents(".order-errorbox2").hide(),
        $("#emailInfo").html("");
        var flag = !0
          , xemail = $("#memberEmail").val()
          , defaultValue = $("#memberEmail").attr("default-value");
        return $.trim(xemail) && xemail != defaultValue && (isEmail($.trim(xemail)) ? $.trim(xemail).length > 50 ? (flag = !1,
        $("#emailInfo").html("您输入的邮箱地址过长！"),
        $("#emailInfo").parents(".order-errorbox2").show()) : flag = 1 : (flag = !1,
        $("#emailInfo").html("您输入的邮箱地址有误，请认真核实！"),
        $("#emailInfo").parents(".order-errorbox2").show()
		)),
        flag,
		$(function() {
			$(".order-errorbox2 .zc-close").click(function() {
				$(this).parents(".order-errorbox2").hide()
		})
		})
    },
	trim = function(str) {
        var strReturn;
        return strReturn = leftTrim(str),
        strReturn = rightTrim(strReturn)
    },
    leftTrim = function(strValue) {
        if (!strValue)
            return "";
        var re = /^\s*/;
        return strValue.replace(re, "")
    },
    rightTrim = function(strValue) {
        if (!strValue)
            return "";
        var re = /\s*$/;
        return strValue.replace(re, "")
    },
    
	isInteger = function(strValue) {
        return regExpTest(strValue, /\d+/)
    },
	isEmail = function(val) {
        var pass = 0;
        if (window.RegExp) {
            var tempS = "a";
            new RegExp(tempS).test(tempS) && (pass = 1)
        }
        if (!pass)
            return val.indexOf(".") > 2 && val.indexOf("@") > 0;
        var r1 = new RegExp("(@.*@)|(\\.\\.)|(@\\.)|(^\\.)")
          , r2 = new RegExp("^[a-zA-Z0-9\\.\\!\\#\\$\\%\\&\\??\\*\\+\\-\\/\\=\\?\\^\\_\\`\\{\\}\\~]*[a-zA-Z0-9\\!\\#\\$\\%\\&\\??\\*\\+\\-\\/\\=\\?\\^\\_\\`\\{\\}\\~]\\@(\\[?)[a-zA-Z0-9\\-\\.]+\\.([a-zA-Z]{2,3})(\\]?)$");
        return !r1.test(val) && r2.test(val)
    },
	regExpTest = function(source, re) {
        var result = !1;
        return !!source && (source == re.exec(source) && (result = !0),
        result)
    },
    
	getBytesLength = function(str) {
        return str.replace(/[^\x00-\xff]/g, "xx").length
    },
	checkStr = function(str) {
        for (var flag = !1, checkString = "`~!@#$%^&*()+-=[]{}\\|;':\",./<>?", j = 0; j < checkString.length; j++)
            if (-1 != str.indexOf(checkString.substring(j, j + 1))) {
                flag = !0;
                break
            }
        return flag
    },
	checkIDCard = function(idcard) {
        idcard = idcard.toUpperCase();
        var Y, JYM, S, M, area = {
            11: "北京",
            12: "天津",
            13: "河北",
            14: "山西",
            15: "内蒙古",
            21: "辽宁",
            22: "吉林",
            23: "黑龙江",
            31: "上海",
            32: "江苏",
            33: "浙江",
            34: "安徽",
            35: "福建",
            36: "江西",
            37: "山东",
            41: "河南",
            42: "湖北",
            43: "湖南",
            44: "广东",
            45: "广西",
            46: "海南",
            50: "重庆",
            51: "四川",
            52: "贵州",
            53: "云南",
            54: "西藏",
            61: "陕西",
            62: "甘肃",
            63: "青海",
            64: "宁夏",
            65: "新疆",
            71: "台湾",
            81: "香港",
            82: "澳门"
        }, idcard_array = [];
        if (idcard_array = idcard.split(""),
        !area[parseInt(idcard.substr(0, 2))])
            return 1;
        switch (idcard.length) {
        case 15:
            return (parseInt(idcard.substr(6, 2)) + 1900) % 4 == "0" || (parseInt(idcard.substr(6, 2)) + 1900) % 100 == "0" && (parseInt(idcard.substr(6, 2)) + 1900) % 4 == "0" ? ereg = /^[1-9][0-9]{5}[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}$/ : ereg = /^[1-9][0-9]{5}[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))[0-9]{3}$/,
            ereg.test(idcard) ? 0 : 1;
        case 18:
            return parseInt(idcard.substr(6, 4)) % 4 == "0" || parseInt(idcard.substr(6, 4)) % 100 == "0" && parseInt(idcard.substr(6, 4)) % 4 == "0" ? ereg = /^[1-9][0-9]{5}19[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}[0-9Xx]$/ : ereg = /^[1-9][0-9]{5}19[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))[0-9]{3}[0-9Xx]$/,
            ereg.test(idcard) ? (S = 7 * (parseInt(idcard_array[0]) + parseInt(idcard_array[10])) + 9 * (parseInt(idcard_array[1]) + parseInt(idcard_array[11])) + 10 * (parseInt(idcard_array[2]) + parseInt(idcard_array[12])) + 5 * (parseInt(idcard_array[3]) + parseInt(idcard_array[13])) + 8 * (parseInt(idcard_array[4]) + parseInt(idcard_array[14])) + 4 * (parseInt(idcard_array[5]) + parseInt(idcard_array[15])) + 2 * (parseInt(idcard_array[6]) + parseInt(idcard_array[16])) + 1 * parseInt(idcard_array[7]) + 6 * parseInt(idcard_array[8]) + 3 * parseInt(idcard_array[9]),
            Y = S % 11,
            M = "F",
            JYM = "10X98765432",
            M = JYM.substr(Y, 1),
            M == idcard_array[17] ? 0 : 1) : 1;
        default:
            return 1
        }
    }