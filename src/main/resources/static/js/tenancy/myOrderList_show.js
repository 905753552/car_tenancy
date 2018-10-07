layui.use('laydate', function() {
    var laydate = layui.laydate;

    //常规用法
    laydate.render({
        elem: '#fromDate'
    });
    laydate.render({
        elem: '#toDate'
    });
})


function getCustomer() {
    $.ajax({
        url: '/api/orderList/getOrderList',
        dataType:'json',
        success: function(res) {
            if(res.code == 0){
                orderlist_data.orders = res.order;
                 console.log(orderlist_data.orders);
            }else{
                handleAjax(res);
            }
        }
    })
}
//查看订单详情
function godetail(id) {
    //console.log(id);
    $.ajax({
        // url:"/api/orderList/orderData?id="+id,
        url:"/api/orderList/getOrderData?id="+id,
        type:"get",
        success:function (res) {
            if(res.code == 0){
                console.log(res);
                var data = {
                    order_detail:res.order_detail,
                    index:'myOrderList_show'
                }
                var order = encodeURIComponent(JSON.stringify(data));
                window.location.href = '/tenancy/p/myOrder?'+order;
            } else {
                handleAjax(res);
            }
        }
        // success:function (res) {
        //     if(res.code == 0){
        //         let orderData = res.orderData;
        //         let cData = encodeURIComponent(JSON.stringify(orderData))
        //         // let url = "/tenancy/p/annualRentOrder?"+cData;
        //         let url = "/tenancy/p/beginReserve?"+cData;
        //         window.location.href = url
        //     }else{
        //             handleAjax(res);
        //     }
        // }
    })
}
const orderlist_data = {
    orders:[]
    // orders: [{
    //     "name":"雪佛兰",//品牌名
    //     "series":"新科鲁兹",//车型号
    //     "box_quantity":3,//车厢数
    //     "displacement":1485,//车排量
    //     "transmission_type":"AT",//变速箱类型
    //     "seat_quantity":5,//座位数
    //     "id":"201524133244",//订单号
    //     "get_store_name":"北京 - 首都机场T1/T2店",//取车门店名
    //     "start_date":"2018-09-12 13:00",//开始时间
    //     "return_store_name":"北京 - 首都机场T1/T2店",//还车门店名
    //     "return_date":"2018-09-16 13:00",//还车时间
    //     "total_amount":215,//订单总价
    //     "status":1,//订单状态
    //     "img":"https://fimg.zuchecdn.com/upload/web/modepic/848.jpg",
    //     "mymix":"",
    //     "orderstate":""
    // }]
};

const orderlist_methods = {
    ggg:function (index) {
        var displacement = (this.orders[index].carVo.displacement/1000.0).toFixed(1);
        var transmission_type="";
        if (this.orders[index].carVo.transmissionType=="MT"||this.orders[index].carVo.transmissionType=="mt") transmission_type="手动";
        if (this.orders[index].carVo.transmissionType=="AT"||this.orders[index].carVo.transmissionType=="at") transmission_type="自动";
        var mix = displacement+"L"+transmission_type;
        var state = "";
        if(this.orders[index].status==1) state="租赁中";if(this.orders[index].status==2) state="预定成功";if(this.orders[index].status==0) state="等待付款";
        if(this.orders[index].status==4) state="已完成";if(this.orders[index].status==3) state="已取消";if(this.orders[index].status==5) state="处理中";
        this.orders[index].mymix=mix;
        this.orders[index].orderstate=state;
    },
    gggg:function(index){
        var color="";
        if(this.orders[index].status==1) color="s-blue";if(this.orders[index].status==2) color="s-green";if(this.orders[index].status==0) color="s-orange";
        if(this.orders[index].status==4) color="s-yellow";if(this.orders[index].status==3) color="s-gray";if(this.orders[index].status==5) color="s-blue";
        return color;
    },
    godetail:function (index) {
        godetail(this.orders[index].id);
    }
};
const getname = {
   order_0 : function () {
       return this.orders.filter(function (data) {
         return data.status === 0
       })
   },
    order_1 : function () {
        return this.orders.filter(function (data) {
            return data.status === 1
        })
    },
    order_2 : function () {
        return this.orders.filter(function (data) {
            return data.status === 2
        })
    },
    order_3 : function () {
        return this.orders.filter(function (data) {
            return data.status === 3
        })
    },
    order_4 : function () {
        return this.orders.filter(function (data) {
            return data.status === 4
        })
    },
    order_5 : function () {
        return this.orders.filter(function (data) {
            return data.status === 5
        })
    }
};


const orderlist_app = new Vue({
    el: '#orderlist_app',
    data: orderlist_data,
    methods: orderlist_methods,
    computed: getname,
    created: getCustomer()
});
