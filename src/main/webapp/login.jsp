<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
%>

<%
    HttpSession session111 = request.getSession();
    session111.invalidate();
%>


<!DOCTYPE html>
<html lang="en">
<head>
    <base href="<%=basePath%>">
    <meta charset="utf-8"/>
    <title>登录界面</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta content="Themesbrand" name="author"/>
    <!-- App favicon -->
    <link rel="shortcut icon" href="assets/images/favicon.ico">

    <!-- App css -->
    <link href="assets/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="assets/css/icons.css" rel="stylesheet" type="text/css"/>
    <link href="assets/css/metismenu.min.css" rel="stylesheet" type="text/css"/>
    <link href="assets/css/style.css" rel="stylesheet" type="text/css"/>

    <%--   <link href="jquery/bootstrap_3.3.0/css/bootstrap.min.css" type="text/css" rel="stylesheet"/>--%>
    <script type="text/javascript" src="jquery/jquery-1.11.1-min.js"></script>
    <script type="text/javascript" src="jquery/bootstrap_3.3.0/js/bootstrap.min.js"></script>

    <script>
        $(function () {

            if (window.top != window) {
                window.top.location = window.location;
            }
            $("#loginAct").val("");
            //登录页面第一时间，账号输入框获得焦点
            $("#loginAct").focus();

            $("#submitBtn").click(function () {
                login();
            });
            $(window).keydown(function (event) {
                if (event.keycode == 13) {
                    login();
                }
            });
        })

        function login() {

            //自定义的方法，要写在外面
            //去空可以用$.trim()
            //获得账号和密码
            var loginAct = $.trim($("#loginAct").val());
            var loginPwd = $.trim($("#loginPwd").val());

            if (loginAct == "" || loginPwd == "") {
                $("#msg").html("账号或者密码不能为空")
                return false;
            }

            //登录验证的ajax
            $.ajax({
                url: "settings/user/login.do",
                data: {
                    "loginAct": loginAct,
                    "loginPwd": loginPwd
                },
                type: "post",
                dataType: "json",
                success: function (data) {


                    if (data.success) {
                        window.location.href = "workbench/index.jsp";
                    } else {
                        $("#msg").html(data.msg);
                    }
                }

            })

        }

    </script>

</head>

<body class="bg-white">

<!-- Log In page -->
<div class="row">
    <div class="col-lg-3 pr-0">
        <div class="card mb-0 shadow-none">
            <div class="card-body">

                <h3 class="text-center m-0">
                    <a href="index.html" class="logo logo-admin"><img src="assets/images/logo-sm.png"
                                                                                height="60" alt="logo" class="my-3"></a>
                </h3>

                <div class="px-3">
                    <h4 class="text-muted font-18 mb-2 text-center">Welcome Back !</h4>
                    <p class="text-muted text-center">Sign in to continue to Amezia.</p>

                    <form class="form-horizontal my-4" action="workbench/index.html">

                        <!--用户名div-->
                        <div class="form-group">
                            <label for="username">用户名</label>
                            <div class="input-group mb-3">
                                <div class="input-group-prepend">
                                    <span class="input-group-text" id="basic-addon1"><i class="far fa-user"></i></span>
                                </div>
                                <input type="text" class="form-control" id="loginAct" placeholder="Enter username">
                            </div>
                        </div>

                        <!--密码div-->
                        <div class="form-group">
                            <label for="userpassword">密码</label>
                            <div class="input-group mb-3">
                                <div class="input-group-prepend">
                                    <span class="input-group-text" id="basic-addon2"><i class="fa fa-key"></i></span>
                                </div>
                                <input type="password" class="form-control" id="loginPwd"
                                       placeholder="Enter password">
                            </div>
                        </div>


                        <div class="form-group row mt-4">
                            <div class="col-sm-6">
                                <div class="custom-control custom-checkbox">
                                    <input type="checkbox" class="custom-control-input" id="customControlInline">
                                    <label class="custom-control-label" for="customControlInline">下次自动登录</label>
                                </div>
                            </div>
                            <div class="col-sm-6 text-right">
                                <a href="pages-recoverpw-2.html" class="text-muted font-13"><i class="mdi mdi-lock"></i>
                                    忘记密码</a>
                            </div>
                        </div>

                        <!--提示错误信息-->
                        <span id="msg" style="color: red">123</span>


                        <div class="form-group mb-0 row">
                            <div class="col-12 mt-2">
                                <button class="btn btn-primary btn-block waves-effect waves-light" type="button"
                                        id="submitBtn">登录
                                    <i class="fas fa-sign-in-alt ml-1"></i></button>
                            </div>
                        </div>
                    </form>
                </div>


                <div class="mt-4 text-center">
                    <p class="mb-0">© 2021 ED</p>
                </div>
            </div>
        </div>
    </div>
    <div class="col-lg-9 p-0 h-100vh d-flex justify-content-center">
        <div class="accountbg d-flex align-items-center">
            <div class="account-title text-center text-white">
                <h4 class="mt-3">Welcome To <span class="text-warning"> CRM</span></h4>
                <h1 class="">Let's Get Started</h1>
                <p class="font-14 mt-3">designed by ED</p>
                <div class="border w-25 mx-auto border-warning"></div>
            </div>
        </div>
    </div>
</div>
<!-- End Log In page -->

<!-- jQuery  -->
<script src="assets/js/jquery.min.js"></script>
<script src="assets/js/bootstrap.bundle.min.js"></script>
<script src="assets/js/metisMenu.min.js"></script>
<script src="assets/js/waves.min.js"></script>
<script src="assets/js/jquery.slimscroll.min.js"></script>

<!-- App js -->
<script src="assets/js/app.js"></script>

</body>
</html>