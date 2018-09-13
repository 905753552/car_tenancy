// $(function(){
//     $("#mybutton").click(function () {
//         $(".ndk").next().text("  "+"年代款");//年代款
//         // $("#ifocus_piclist ul li:eq(1) img").attr("src",pic)
//         // $("#ifocus_btn ul li:eq(1) img").attr("src",pic)
//         for(var i=0;i<4;i++){
//             var pic="/img/"+i+".jpg";
//             document.getElementById("ifocus_piclist").getElementsByTagName("li")[i].firstChild.setAttribute("src",pic);
//             document.getElementById("ifocus_btn").getElementsByTagName("li")[i].firstChild.setAttribute("src",pic);
//         }
//     })
// })
function getCar() {
    $.ajax({
        url: "../../cardetail.json",
        type: "GET",
        dataType: "json",//若数据不是json则直接进入error,json数据里面不能有注释
        success: function(data) {
            console.log("get data")
            if (data.cars.code==200) {
                console.log(data);
                cardetail_app.car = data.cars.carData;
            } else {
                console.log("gg")
                //handleAjax(data.cars);
            }
        },
        error:function () {
            console.log("no")
        }
    })
}

const car_data = {
    "car":{
        "box_quantity":"",//厢数
        "name":"",//品牌
        "series":"",//车系
        "year":"",//年代款
        "config_section":"",//配置款
        "seat_quantity":"",//座位数
        "door_quantity":"",//车门数
        "fuel_type":"",//燃油类型
        "transmission_type":"",//变速箱类型
        "displacement":"",//排量
        "octane_rating":"",//燃油标号
        "driven_method":"",//驱动方式
        "en_itk_form":"",//发动机进气形式
        "skylight":"",//天窗
        "tank_capacity":"",//油箱容量
        "speaker":"",//音响
        "seat":"",//座椅
        "reversing_radar":"",//倒车雷达
        "airbag":"",//气囊
        "dvd_cd":"",//DVD/CD
        "gps":"",//GPS导航
        "imgs":[
            "https://fimg.zuchecdn.com/upload/web/preview/842_a.jpg",
            "https://fimg.zuchecdn.com/upload/web/preview/842_b.jpg",
            "https://fimg.zuchecdn.com/upload/web/preview/842_c.jpg",
            "https://fimg.zuchecdn.com/upload/web/preview/842_d.jpg",
        ]


    }

};

const car_methods = {
    //logout: logout // 退出登录
};

const init_header = function () {
    getCar();
};
const gettitle = {
    mytitle:function () {
        var displacement = (this.car.displacement/1000.0).toFixed(1);
        //console.log(this.car.transmission_type);
        var transmission_type="";
        if (this.car.transmission_type=="MT"||this.car.transmission_type=="mt") transmission_type="手动";
        if (this.car.transmission_type=="AT"||this.car.transmission_type=="at") transmission_type="自动";
        var title = this.car.name+this.car.series+"/"+this.car.box_quantity+"厢/"+displacement+"L"+transmission_type;
        return title
    }
};

const cardetail_app = new Vue({
    el: '#cardetail_app',
    data: car_data,
    methods: car_methods,
    computed: gettitle
    //created: init_header()
});
