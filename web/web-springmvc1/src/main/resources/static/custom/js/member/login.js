$(function () {
    MembershipLogin.init();
});

var MembershipLogin = {
    init: function () {
        LoginMVC.View.initControl();
        LoginMVC.View.bindEvent();
        LoginMVC.View.bindValidate();
    }
};

var LoginMVC = {
    URLs: {
        login: {
            url: AppCtx.ctxPath + '/member/authenticate',
            method: 'GET'
        },
        success: {
            url: AppCtx.ctxPath + '/home/index',
            method: 'POST'
        }
    },
    View: {
        initControl: function () {
            $("#account").focus();
        },
        bindEvent: function () {
            document.onkeydown = function (e) {
                var evt = e ? e : (window.event ? window.event : null)
                if (evt.keyCode == 13) {
                    LoginMVC.Controller.login();
                }
            };
            $('#btn_login').click(LoginMVC.Controller.login);
        },
        bindValidate: function () {
            $("#login_form").validate({
                rules: {
                    account: {
                        required: true,
                    },
                    password: {
                        required: true,
                        minlength: 3,
                        maxlength: 20
                    }
                },
                messages: {
                    account: {
                        required: ''
                    },
                    password: {
                        required: '',
                        minlength: 6
                    }
                },
                errorPlacement: function (error, element) {
                    error.insertAfter(element.parent());
                }
            });
        }
    },
    Controller: {
        showTips: function (type, content) {
            var tipsContainer = $('#login-message-tips');
            tipsContainer.html(content);
            tipsContainer.show();
        },
        login: function () {
            if ($('#login_form').validate().form()) {
                var data = {
                    "account": $("#account").val(),
                    "password": $("#password").val(),
                    "rememberMe": $("#remember_me").prop("checked")
                };
                $.post(LoginMVC.URLs.login.url, data, function (result) {
                    if (!result.success) {
                        //LoginMVC.Controller.showTips('error', result.msg);
                    } else {
                        //LoginMVC.Controller.showTips('success', '登录成功，正在跳转...');
                        window.location = LoginMVC.URLs.success.url;
                    }
                }, 'json');
            }
        }
    }
};