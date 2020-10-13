<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>咕泡云课堂 - 用户列表</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" href="/static/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="/static/style/admin.css" media="all">
    <link rel="stylesheet" href="/static/modules/elemTree/eleTree.css" media="all">
</head>
<body>
<div class="layui-fluid">
    <div class="layui-row layui-col-space10">
        <div class="layui-col-md3">
            <div class="layui-card">
                <div class="layui-card-header">部门列表</div>
                <div class="layui-card-body">
                    <div id="departTreeMenu"></div>
                    <input type="hidden" value="1" id="deptId" name="deptId"/>
                </div>
            </div>
        </div>
        <div class="layui-col-md9">
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
                            <button class="layui-btn layuiadmin-btn-list" lay-submit lay-filter="LAY-app-contlist-search">
                                <i class="layui-icon layui-icon-search layuiadmin-button-btn"> 搜索</i>
                            </button>
                        </div>
                    </div>
                </div>
            </div>
            <div class="layui-card">
                <div class="layui-card-header">
                    <span class="layui-dept-define layui-bg-red" style="font-size:15px;font-weight:bold">总公司</span>&nbsp;下的用户列表</div>
                <div class="layui-card-body">
                    <table id="LAY-user-content-list" lay-filter="LAY-app-content-list"></table>
                    <script type="text/html" id="toolbar-operation-btn">
                        <button class="layui-btn layuiadmin-btn-list layui-btn-sm layui-icon layui-icon-add-1" lay-event="add"> 添加</button>
                        <button class="layui-btn layuiadmin-btn-list layui-btn-primary layui-btn-sm layui-icon layui-icon-delete" lay-event="batchdel"> 删除</button>
                    </script>
                    <script type="text/html" id="buttonTpl">
                        {{#  if(d.status){ }}
                        <button class="layui-btn layui-btn-xs ">正常</button>
                        {{#  } else { }}
                        <button class="layui-btn layui-btn-xs layui-btn-danger">停用</button>
                        {{#  } }}
                    </script>
                    <script type="text/html" id="table-content-list">
                        <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="edit"><i class="layui-icon layui-icon-edit"></i>编辑</a>
                        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del"><i class="layui-icon layui-icon-delete"></i>删除</a>
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
        index: 'lib/index', //主入口模块
        treeSelect:'treeSelect/treeSelect',
        eleTree:'elemTree/eleTree'
    }).use(['index', 'userList', 'table','treeSelect'], function(){
        var table = layui.table,form = layui.form,admin=layui.admin;
        //监听搜索
        form.on('submit(LAY-app-contlist-search)', function(data){
            var field = data.field;
            //执行重载
            table.reload('LAY-user-content-list', {
                where: field
            });
        });
    });
</script>
</body>
</html>
