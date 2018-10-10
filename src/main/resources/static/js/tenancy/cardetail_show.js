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
var pack = JSON.parse(decodeURIComponent(window.location.search.slice(1)));
function init() {

    console.log(pack);
    $.ajax({
        url:"/api/Car/detail?cid="+pack.carId,
        type:"get",
        success:function(data){
            console.log(data);
            cardetail_app.car = data.carDetail;
            var imgs = [];
            $.each(data.carPic,function () {
                imgs.push("/api/pic/item?imagePath="+this.path);
            })
            imgs = imgs.slice(1, 5);
            cardetail_app.Brand = data.carDetail.tncBrand.name;
            cardetail_app.imgs = imgs;
            //console.log(imgs);
        },error:function () {
            console.log("no");
        }
    })
}
function addorder() {
    let url = "/tenancy/p/beginReserve?"+window.location.search.slice(1);
    window.location.href = url
}
const car_data = {
    car: {
    },
    Brand:{},
    imgs:[]
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
        if (this.car.transmissionType=="MT"||this.car.transmissionType=="mt") transmission_type="手动";
        if (this.car.transmissionType=="AT"||this.car.transmissionType=="at") transmission_type="自动";
        var title = this.Brand+this.car.series+"/"+this.car.boxQuantity+"厢/"+displacement+"L"+transmission_type;
        return title
    }, skylight:function () {
        if(this.car.skylight=="0") return "无"; else return "有";
    },reversingRadar:function () {
        if(this.car.reversingRadar=="0") return "无"; else return "有";
    },gps:function () {
        if(this.car.gps=="0") return "无"; else return "有";
    }
};

const cardetail_app = new Vue({
    el: '#cardetail_app',
    data: car_data,
    methods: car_methods,
    computed: gettitle,
    created: init()
});
