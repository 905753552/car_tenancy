var car_day = new Vue({
    el:"#carInfoList",
    data:{

        menu:[
            {
                mid:"carWorkday",
                mname:"工作日套餐"
            },{
                mid:"carWeek",
                mname:"周套餐"
            },{
                mid:"carMon",
                mname:"月套餐"
            },{
                mid:"carYear",
                mname:"年套餐"
            }
        ],
        items: [
            {
                carId:15,
                carImg:"https://fimg.zuchecdn.com/upload/web/modepic/480.jpg",
                carName:"奥迪",
                carVer:"A8",
                carVan:"三厢",
                carPower:"1.8自动",
                carSeat:"6",
                carPrice:"11",
                carOriginalPrice:"999",
                carSavePrice:"111",
                carHot:"777"
            },{
                carId:19,
                carImg:"https://fimg.zuchecdn.com/upload/web/modepic/480.jpg",
                carName:"奔驰",
                carVer:"A8",
                carVan:"三厢",
                carPower:"1.8自动",
                carSeat:"6",
                carPrice:"113",
                carOriginalPrice:"999",
                carSavePrice:"111",
                carHot:"777"

            },{
                carId:16,
                carImg:"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1536655028&di=f4827a5065d70760bb21b4c1929c12fc&imgtype=jpg&er=1&src=http%3A%2F%2Fimg4.cache.netease.com%2Fphoto%2F0008%2F2014-10-28%2FA9LNB533294V0008.550x.0.jpg",
                carName:"宝马",
                carVer:"5系",
                carVan:"三厢",
                carPower:"1.8自动",
                carSeat:"6",
                carPrice:"110",
                carOriginalPrice:"1000",
                carSavePrice:"111",
                carHot:"77756"
            }
        ]
    },
    methods:{
        //订单事件
        doCarOrder:function (data) {
            console.log("doCarOrder")
            console.log(data)
        }
    },
    computed: {
        evenMenus: function () {
            return this.menu.filter(function (mm) {
                return mm.mname.indexOf("日租自驾")<0
            })
        }
    }
});


//排序
function doSort() {

    if ($("#dev_sort_button").text().indexOf("↓") >=0) {
        $("#dev_sort_button").html('<span class="dev_od_btn_a">↑</span> 租金由低到高')
        var sortData = car_day.items;
        function sortprice(a,b){
            return a.carPrice > b.carPrice
        }
        sortData.sort(sortprice)
        console.log(sortData)
    } else{
        $("#dev_sort_button").html('<span class="dev_od_btn_a">↓</span> 租金由高到低')
        var sortData = car_day.items;
        function sortprice(a,b){
            return a.carPrice < b.carPrice
        }
        sortData.sort(sortprice)
    }
}