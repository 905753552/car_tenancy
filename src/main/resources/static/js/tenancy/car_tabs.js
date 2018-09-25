var carHandleData
var lastCarHandleData
var carMenuData
//基础套餐ID
const basePackageId = "1";
//当前套餐ID
var currentPackageId = basePackageId;
//筛选车型
const carBaseType = "手动紧凑型,商务型,经济型,豪华型,SUV,6至15座商务,个性车,电动车"
var carType = carBaseType
var allCarBrand = ""
//筛选品牌
var carBrand =""
//汽车处理后事件

//默认筛选价格  0 - 9999
var carMinPrice =0;
var carMaxPrice =9999;

//data
const carInfoTab_data ={
    menu: [],
    items: [],
    carBrands:[],
    days:1
}
//filter
const filter_carInfoTab = {
    evenMenus:evenMenus
}
//methods
const carInfoTab_methods = {
    doSort: doSort,
    doCarOrder: doCarOrder,
    doSearchByPackage: doSearchByPackage,
    doCheckByType:doCheckByType,
    doSearchByAllType:doSearchByAllType
}
//Vue created 时触发的函数
const init_carInfoTab = ()=>{

    loadCarMenuData()//加载套餐数据
   // loadCarBrandData()//加载汽车品牌
}

//Vue app
const carInfoTab_app = new Vue({
    el: "#carInfoTab-app",
    data:carInfoTab_data,
    created: init_carInfoTab(),
    methods: carInfoTab_methods,
    computed: filter_carInfoTab

})

/*-------------------------function start------------------------------*/
//排序
function doSort() {

    if ($("#dev_sort_button").text().indexOf("↓") >= 0) {

        $("#dev_sort_button").html('<span class="dev_od_btn_a">↑</span> 租金由低到高')
        var sortData = carInfoTab_app.items

        function sortprice(a, b) {
            return a.carPrice > b.carPrice
        }

        sortData.sort(sortprice)

    } else {
        $("#dev_sort_button").html('<span class="dev_od_btn_a">↓</span> 租金由高到低')
        var sortData = carInfoTab_app.items

        function sortprice(a, b) {
            return a.carPrice < b.carPrice
        }
        sortData.sort(sortprice)
    }
}
//初始化套餐数据
function loadCarMenuData() {

    $.ajax({
        url: "/api/CarSelect/listP",
        type: "GET",
        contentType: "application/json",
        success: function (data) {
            if (data.code == 0) {
                console.log(data.TncPackageScheme)
                carMenuData = data.TncPackageScheme
                carInfoTab_app.menu = data.TncPackageScheme
                doHandelCarBrand(data.TncBrand)
            } else {
                handleAjax(data);
            }
        },
        fail: function () {
            console.log("fail");
        }
    })
}
//初始化汽车品牌
function loadCarBrandData(){
    $.ajax({
        url: "/car_menu_data.json",
        success: function (data) {
            if (data.carMenu.code == 0) {
                console.log(data.carBrand.carbrand)
                doHandelCarBrand(data.carBrand.carbrand)
            } else {
                handleAjax(data.carMenu);
            }
        },
        fail: function () {
            console.log("fail");
        }
    })
}
// 过滤菜单
function evenMenus() {
    return this.menu.filter(function (mm) {
        return mm.name.indexOf("日租自驾") < 0
    })
}
//下单
function doCarOrder(data) {
console.log("------------------")
    console.log(data)
    console.log("------------------")

    let orderData = {
         getCarPlace:"租车派",
         getCarPlaceId:"1",
         returnCarPlace:"租车派",
         returnCarPlaceId:"1",
         getCarTime:$("#fromDate").val(),
         returnCarTime:$("#toDate").val(),
         days:carInfoTab_app.days,
         packageId:currentPackageId,
         carId:1
    }
    let cData = encodeURIComponent(JSON.stringify(orderData))
    window.location.href="/tenancy/p/beginReserve?orderData="+cData
}
//处理汽车品牌
function doHandelCarBrand(data) {
    carInfoTab_app.carBrands = data
    let cb="";
    data.forEach(function (item) {
        cb+=item.name+","
    })
    allCarBrand = cb
    carBrand = cb
}
//按类型查车
/**
 *
 * @param pid 套餐id
 */
function doSearchByPackage(pid) {

    $("#ch_type li a").parent().siblings('li').children().removeClass('checked');
    $("#ch_brand li a").parent().siblings('li').children().removeClass('checked');  // 删除其他兄弟元素的样式
    $("#ch_num li a").parent().parent().parent().removeClass('price1');
    $("#ch_num li a").parent().parent().parent().removeClass('price2');
    $("#ch_num li a").parent().parent().parent().removeClass('price3');
    $("#ch_num li a").parent().parent().parent().removeClass('price4');
    $("#ch_num li a").parent().parent().parent().removeClass('price5');
    $("#ch_num li a").parent().siblings('li').children().removeClass('checked');
    $("#ch_num li a").addClass('checked');
    $("#ch_num li a").parent().parent().parent().addClass('price1');

    $("#cbx").addClass('checked');
    $('#typebx').addClass("checked")

    carType = carBaseType
    carMinPrice =0;
    carMaxPrice =9999;
    carBrand = allCarBrand
    currentPackageId = pid
    doHandelDate()
    doSearchByAllType()
}

//查询车辆信息
function doSearchByAllType() {
    /**
     * @param place 地点
     * @param currentPackageId 套餐id
     */
    let carPlace = "租车派"
    const data ={
        carPlace:carPlace,
        carPID:currentPackageId
    }
   // console.log(JSON.stringify(data))
    $.ajax({
        type:"POST",
        url:"/api/CarSelect/listCar",
        contentType: "application/json",
        data:JSON.stringify(data),
        success: function (data) {
            if (data.code == 0) {
                console.log(data.carData)
                console.log(data.carData.length)
                doHandelCarInfo(data.carData)
            } else {
                handleAjax(data);
            }
        }
    })

    // $.ajax({
    //     url: "/car_menu_data.json",
    //     // data:JSON.stringify(data) ,
    //     // contentType:"applicayion/json",
    //     success: function (data) {
    //         if (data.cars.code == 500) {
    //             console.log(data.cars.carData)
    //             console.log(data.cars.carData.length)
    //             doHandelCarInfo(data.cars.carData)
    //         } else {
    //             handleAjax(data.cars);
    //         }
    //     }
    // })
}

//数据加工
function doHandelCarInfo(carData) {
    let days = carInfoTab_app.days;//获取租车天数
    carData.forEach(function (cdata, index) {
        cdata.carTotalPrice = cdata.carPrice * days
        cdata.carTotalPackagePrice = cdata.carPackagePrice * days
    })
    carInfoTab_app.items = carData;
    carHandleData = carData;
}

//改变查询车型
function doChangeType(type) {

    switch (type) {
        case 'SD':
            carType = "手动紧凑型"
            break
        case 'JJ':
            carType = "经济型"
            break
        case 'SW':
            carType = "商务型"
            break
        case 'HH':
            carType = "豪华型"
            break
        case 'SUV':
            carType = "SUV"
            break
        case '6SW':
            carType = "6至15座商务"
            break
        case 'GX':
            carType = "个性车"
            break
        case 'DD':
            carType = "电动车"
            break
        case 'BX':
            carType = carBaseType
            break
    }
    console.log(carType)
    doCheckByType(carType)
}

//改变查询价格
function doChangePrice(price) {

    if(price==0){
        carMinPrice = 0;
        carMaxPrice = 9999;
    }
    if(price==150){
        carMinPrice = 0;
        carMaxPrice = 150;
    }
    if(price==300){
        carMinPrice = 151;
        carMaxPrice = 300;
    }
    if(price==500){
        carMinPrice = 301;
        carMaxPrice = 500;
    }
    if(price==501){
        carMinPrice = 501
        carMaxPrice = 9999
    }
    doCheckByType(carType)
}

//改变查询品牌
function doChangeBrand(brand) {
    if(brand=="CBX"){
        carBrand = allCarBrand;
    }else{
        carBrand = brand
    }

    doCheckByType(carType)
}

function doCheckByType(type) {

    let cart=""
    if(type ==null || type==""){
        cart = "手动紧凑型,商务型,经济型,豪华型,SUV,6至15座商务,个性车,电动车"
        carType = ""
        console.log(carMinPrice+'---------------'+carMaxPrice)
    }else{
         cart = type
    }

    let carn =""
    if(carBrand ==null || carBrand==""){
        carn = allCarBrand
    }else{
        carn = carBrand
    }


    carInfoTab_app.items = carHandleData
    var ad =  carInfoTab_app.items.filter(function (item) {
       if(currentPackageId != basePackageId){
           return (cart.indexOf(item.carType) >= 0 && item.carPackagePrice > carMinPrice && item.carPackagePrice < carMaxPrice && carn.indexOf(item.carName)>=0 )
       }
        return (cart.indexOf(item.carType) >= 0 &&  item.carPrice > carMinPrice && item.carPrice < carMaxPrice && carn.indexOf(item.carName)>=0 )

    })
    if(ad.length!=0) {
        lastCarHandleData = ad
    }
    //没找到符合条件的车
    if(ad.length==0){
        notFoundTips()
        carInfoTab_app.items = lastCarHandleData
    }

    carInfoTab_app.items = ad

}
//date formatDate
function formatDate(date) {
    let d = new Date(date),
        month = '' + (d.getMonth() + 1),
        day = '' + d.getDate(),
        year = d.getFullYear();
    if (month.length < 2) month = '0' + month;
    if (day.length < 2) day = '0' + day;
    return [year, month, day].join('-');
}

//处理套餐时间
function  doHandelDate() {
    let daysMin
    let daysMax
    if(currentPackageId == basePackageId ){
        daysMin = 1
        daysMax = 999
    }
    carMenuData.forEach(function (item) {
       if(currentPackageId == item.id){
           daysMin = item.daysMin
           daysMax = item.daysMax
       }
    })
    if(carInfoTab_app.days < daysMin){
        layer.msg("该套餐最低天数为"+daysMin+"天")
        //该套餐最小天数
        let oneDay = 86400000
        carInfoTab_app.days = daysMin
        let fromDate  = $("#fromDate").val()
        let fDate = (new Date(fromDate)).getTime()
        let toDate = formatDate(new Date(fDate+(daysMin)*oneDay))
        $("#toDate").val(toDate)
    }
    if(carInfoTab_app.days > daysMax){
        layer.msg("该套餐最大天数为"+daysMax+"天")
        //该套餐最大天数
        carInfoTab_app.days = daysMax
        let oneDay = 86400000
        let fromDate  = $("#fromDate").val()
        let fDate = (new Date(fromDate)).getTime()
        let toDate = formatDate(new Date(fDate+(daysMax)*oneDay))
        $("#toDate").val(toDate)
    }

}
//没结果提示
function notFoundTips() {
    layer.open({
        title: '温馨提示'
        ,content: '没找到符合条件的车型，换一种试试？'
        ,cancel: function () {
         //   doSearchByAllType();
            //carType = "";
            //carBrand =""
            // carMinPrice =0;
            // carMaxPrice =9999;
        }
        , yes: function(index, layero){
           // doSearchByAllType()
           // carType = "";
           // carBrand =""
            layer.close(index); //如果设定了yes回调，需进行手工关闭
        }
    });
}

/*-------------------------------------function end-------------------------------------*/