<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>咕泡云课堂 - 角色成员列表</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" href="/static/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="/static/style/admin.css" media="all">
</head>
<body>

<div class="layui-fluid">
    <div class="layui-row layui-col-space10">
        <div class="layui-col-md12">
            <div class="layui-card">
                <div class="layui-form layui-card-header layuiadmin-card-header-auto">
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label class="layui-form-label" style="width:85px">关键字查询：</label>
                            <div class="layui-input-inline" style="width:290px">
                                <input type="text" name="keyword" placeholder="可根据用户名/真实姓名/邮箱/手机号查询" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-inline">
                            <button class="layui-btn layuiadmin-btn-list layui-btn-sm" lay-submit lay-filter="LAY-app-contlist-search">
                                <i class="layui-icon layui-icon-search layuiadmin-button-btn"> 搜索</i>
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="layui-col-md12">
            <div class="layui-card">
                <div class="layui-card-body">
                    <table id="LAY-app-content-list" lay-filter="LAY-app-content-list"></table>
                    <script type="text/html" id="buttonTpl">
                        {{#  if(d.status){ }}
                        <button class="layui-btn layui-btn-xs ">正常</button>
                        {{#  } else { }}
                        <button class="layui-btn layui-btn-xs layui-btn-danger">停用</button>
                        {{#  } }}
                    </script>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="/static/layui/layui.js"></script>
<script>
    layui.config({
        base: '/static/' //静态资源所在路径
    }).extend({
        index: 'lib/index' //主入口模块
    }).use(['index', 'roleList', 'table'], function(){
        var table = layui.table
                ,form = layui.form,admin=layui.admin;
        //监听搜索
        form.on('submit(LAY-app-contlist-search)', function(data){
            var field = data.field;
            //执行重载
            table.reload('LAY-app-content-list', {
                where: field
            });
        });
        table.render({
            elem: "#LAY-app-content-list",
            url: "/sys/user/userlist/"+#{roleId},
            even: true,
            skin:'row',
            cols: [[
                {field: "username",title: "用户名"},
                {field: "phone", title: "电话"},
                {field: "email", title: "邮箱"},
                {field: "realName",title: "真实姓名",sort: !0},
                {field: "status", title: "状态", templet: "#buttonTpl", minWidth: 80, align: "center"}
            ]],
            page: !0,
            limit: 5,
            limits: [5, 10, 15, 20, 25],
            text: {
                none: "该角色下未关联用户！"
            }
        });
    });
</script>
</body>
</html>