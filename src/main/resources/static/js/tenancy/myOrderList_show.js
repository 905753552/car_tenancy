function getCustomer() {
    $.ajax({
        url: '../../myorderlist.json',
        success: function (data) {
            if (data.myorders.code == 200) {
                orderlist_app.orders = data.myorders.orders;
            } else {
                console.log("gg")
                //handleAjax(data.customer);
            }
        }
    })
}

const orderlist_data = {
    orders: [{
        "name":"雪佛兰",//品牌名
        "series":"新科鲁兹",//车型号
        "box_quantity":3,//车厢数
        "displacement":1485,//车排量
        "transmission_type":"AT",//变速箱类型
        "seat_quantity":5,//座位数
        "id":"201524133244",//订单号
        "get_store_name":"北京 - 首都机场T1/T2店",//取车门店名
        "start_date":"2018-09-12 13:00",//开始时间
        "return_store_name":"北京 - 首都机场T1/T2店",//还车门店名
        "return_date":"2018-09-16 13:00",//还车时间
        "total_amount":215,//订单总价
        "status":1,//订单状态
        "img":"https://fimg.zuchecdn.com/upload/web/modepic/848.jpg",
        "mymix":"",
        "orderstate":""
    }]
};

const orderlist_methods = {
    ggg:function (index) {
        var displacement = (this.orders[index].displacement/1000.0).toFixed(1);
        var transmission_type="";
        if (this.orders[index].transmission_type=="MT"||this.orders[index].transmission_type=="mt") transmission_type="手动";
        if (this.orders[index].transmission_type=="AT"||this.orders[index].transmission_type=="at") transmission_type="自动";
        var mix = displacement+"L"+transmission_type;
        var state = "";
        if(this.orders[index].status==1) state="已失效";if(this.orders[index].status==2) state="预定成功";if(this.orders[index].status==0) state="等待付款";
        if(this.orders[index].status==4) state="已完成";if(this.orders[index].status==3) state="已取消";
        this.orders[index].mymix=mix;
        this.orders[index].orderstate=state;
    },
    gggg:function(index){
        var color="";
        if(this.orders[index].status==1) color="s-gray";if(this.orders[index].status==2) color="s-green";if(this.orders[index].status==0) color="s-orange";
        if(this.orders[index].status==4) color="s-yellow";if(this.orders[index].status==3) color="s-gray";
        return color;
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
    }
};


const orderlist_app = new Vue({
    el: '#orderlist_app',
    data: orderlist_data,
    methods: orderlist_methods,
    computed: getname
    //created: init_header()
});
