$(document).ready(function () {
    $("#tnc_toggle_area").hover(function () {
        $("#tnc_toggle_menu").toggle();
    });
});

function getCustomer() {
    $.ajax({
        url: '/api/shiro/token?_t=' + new Date().getTime(),
        success: (data) => {
            if (data.code == 0) {
                if(data.token) {
                    header_app.customer = data.token;
                }
            } else {
                handleAjax(data.customer);
            }
        }
    })
}

function logout() {
    confirm("退出登录？", () => {
        $.ajax({
            url: '/api/shiro/logout',
            success: (data) => {
                if (data.code == 0) {
                    header_app.customer = {};
                    window.location.href='/index';
                } else {
                    handleAjax(data.logout);
                }
            }
        })
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
