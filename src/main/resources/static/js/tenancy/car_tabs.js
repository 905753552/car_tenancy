//当前套餐ID
var currentPackageId = "carDay";


const filter_carInfoTab = function () {
    evenMenus:evenMenus()
}


const carInfoTab_methods = {
    doSort: doSort,
    doCarOrder: doCarOrder,
    doSeachByPackage: doSeachByPackage
}


const init_carInfoTab = function () {
    loadCarInfoTabData()//加载数据
}


const carInfoTab_app = new Vue({
    el: "#carInfoTab-app",
    data: {
        menu: [],
        items: []
    },
    created: init_carInfoTab(),
    methods: carInfoTab_methods,
    computed: {
        evenMenus: function () {
            return this.menu.filter(function (mm) {
                return mm.mname.indexOf("日租自驾") < 0
            })
        }
    }
})



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


function loadCarInfoTabData() {

    $.ajax({
        url: "/car_menu_data.json",
        success: function (data) {
            if (data.carMenu.code == 500) {

                console.log(data.carMenu.carmenu)
                //console.log(data.cars.carData)
                carInfoTab_app.menu = data.carMenu.carmenu
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
        return mm.mname.indexOf("日租自驾") < 0
    })
}

//下单
function doCarOrder() {

}

//按类型查车
/**
 *
 * @param pid 套餐id
 */
function doSeachByPackage(pid) {
    currentPackageId = pid
    //console.log(nowPackageId)
    doSeachByAllType()
}

function doSeachByAllType() {

    // var seachCondition = new Map();
    // seachCondition.set("carPackage",currentPackageId)//套餐类型
    // seachCondition.set("carModel",$("#xxx").val() )//车型
    // seachCondition.set("carBrand",$("#xxx").val() )//品牌
    // seachCondition.set("carPrice",$("#xxx").val() )//价格
    // seachCondition.set("carLocation",$("#xxx").val() )//位置
    // seachCondition.set("carDate",$("#xxx").val() )//日期

    $.ajax({
        url: "/car_menu_data.json",
        // data:seachCondition ,
        success: function (data) {
            if (data.cars.code == 500) {
                console.log(data.cars.carData)
                doHandelCarInfo(data.cars.carData)
            } else {
                handleAjax(data.cars);
            }

        }
    })


    console.log(currentPackageId)

}

function doHandelCarInfo(carData) {
    let days = 3;//获取租车天数
    carData.forEach(function (cdata, index) {

        cdata.carTotalPrice = cdata.carPrice * days
        cdata.carTotalPackagePrice = cdata.carPackagePrice * days

        console.log("------" + cdata.carTotalPackagePrice)

    })
    carInfoTab_app.items = carData;
}


// var carInfoTab_app = new Vue({
//     el:"#carInfoTab-app",
//     data:{
//         menu:[],
//         items:[]
//     },
//     created:function(){
//         loadCarInfoTabData()
//     },
//     methods:{
//         //订单事件
//         doCarOrder:function (data) {
//             console.log("doCarOrder")
//             console.log(data)
//         }
//     },
//     computed: {
//     evenMenus: function () {
//         return this.menu.filter(function (mm) {
//             return mm.mname.indexOf("日租自驾")<0
//         })
//     }
//     }
// });