$(document).ready(function () {
    $("#tnc_toggle_area").hover(function () {
        $("#tnc_toggle_menu").toggle();
    });
});

function getCustomer() {
    $.ajax({
        url: '/header_data.json',
        success: (data) => {
            if (data.customer.code == 200) {
                header_app.customer = data.customer.customer;
            } else {
                handleAjax(data.customer);
            }
        }
    })
}

function logout() {
    $.ajax({
        url: '/header_data.json',
        success: (data) => {
            if (data.logout.code == 200) {
                header_app.customer = {};
            } else {
                handleAjax(data.logout);
            }
        }
    })
}

const header_data = {
    customer: {}
};

const header_methods = {
    logout: logout // 退出登录
};

const init_header = () => {
    getCustomer(); // 获取用户信息，用于判断用户是否已登录
}

const header_app = new Vue({
    el: '#header-app',
    data: header_data,
    methods: header_methods,
    created: init_header()
});
