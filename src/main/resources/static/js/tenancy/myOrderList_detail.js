var orderDetail = new Vue({
    el: '#orderDetails',
    data:{
        base:{},
        order_detail:{},
        carVo:{},
        getStore:{},
        returnStore:{},
        order:{},
        price:{},
        description:{},
        index:''
    },
    computed:{
        days : function () {
            // var diff=this.order.returnDate.getTime() - this.order.startDate.getTime();
            // //计算出相差天数
            // console.log(diff);
            // var days=Math.floor(diff/(24*3600*1000));
            return "5";
        }
    },
    created:init(),
    // created:function(){
    //     var self = this;
    //     self.order_detail = JSON.parse(decodeURIComponent(window.location.search.slice(1))).order_detail;
    //     self.index = JSON.parse(decodeURIComponent(window.location.search.slice(1))).index;
    //     self.description = readDesc(self.order_detail.description);
    //     console.log(self.order_detail.order_detail);
    //     self.initialize();
    //     self.showModel();
    // },
    methods:{
        goToPay:function(){
            goToPay();
        },
        initialize:function(){
            initialize(this);
        },
        showModel:function(){
            showModel(this);
        },
        cancelOrder:function(){
            cancelOrder();
        },
        gotoOrderCenter:function(){
            gotoOrderCenter();
        },
        updateOrder:function(){
            updateOrder();
        }
    }
});
function init() {
    var id = window.location.search.slice(9);
    console.log(id);
    $.ajax({
        url:"/api/orderList/detail?id="+id,
        type:"get",
        success: function(res) {
            if(res.code == 0){
                orderDetail.base = res.base;
                orderDetail.carVo = res.base.carVo;
                orderDetail.returnStore = res.base.returnStore;
                orderDetail.getStore = res.base.getStore;
                orderDetail.order = res.order;
                orderDetail.price = res.price;
            }else{
                handleAjax(res);
            }
        }
    })
}