<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>咕泡云课堂 - 菜单列表</title>
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
                            <label class="layui-form-label" style="width:85px">关键字：</label>
                            <div class="layui-input-inline" style="width:290px">
                                <input type="text" name="keyword" placeholder="输入关键字查询" autocomplete="off" class="layui-input">
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
        </div>
        <div class="layui-col-md12">
            <div class="layui-card">
                <div class="layui-card-body">
                    <table id="LAY-app-content-list" lay-filter="LAY-app-content-list"></table>
                    <script type="text/html" id="toolbar-operation-btn">
                        <button class="layui-btn layuiadmin-btn-list layui-btn-sm layui-icon layui-icon-add-1" lay-event="add"> 添加菜单</button>
                        <button class="layui-btn layuiadmin-btn-list layui-btn-primary layui-btn-sm layui-icon layui-icon-down" lay-event="expand" > 全部展开</button>
                        <button class="layui-btn layuiadmin-btn-list layui-btn-primary layui-btn-sm layui-icon layui-icon-up" lay-event="fold"> 全部折叠</button>
                    </script>
                    <script type="text/html" id="buttonTpl">
                        {{#  if(d.isShow){ }}
                        <button class="layui-btn layui-btn-xs layui-btn-danger">隐藏</button>
                        {{#  } else { }}
                        <button class="layui-btn layui-btn-xs ">显示</button>
                        {{#  } }}
                    </script>
                    <script type="text/html" id="table-content-list">
                        {{# if(d.menuType!='api'){ }}
                        <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="addChild"><i class="layui-icon layui-icon-edit"></i>添加子节点</a>
                        {{# } else { }}
                        <a class="layui-btn layui-btn-primary layui-btn-xs layui-btn-disabled" ><i class="layui-icon layui-icon-edit"></i>添加子节点</a>
                        {{#  } }}
                        <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="edit"><i class="layui-icon layui-icon-edit"></i>编辑</a>
                        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del"><i class="layui-icon layui-icon-delete"></i>删除</a>
                    </script>
                    <script type="text/html" id="table-menuType-btn">
                        {{# if(d.menuType=='api'){ }}
                            <button class="layui-btn layui-btn-xs layui-btn-danger">接口</button>
                        {{# } else if(d.menuType=='menu') { }}
                            <button class="layui-btn layui-btn-xs">菜单</button>
                        {{#  } else { }}
                            <button class="layui-btn layui-btn-xs layui-btn-warm">目录</button>
                        {{# } }}
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
        treeTable:'treetable/treeTable',
        menuList:'sys/menu/menuList'
    }).use(['index', 'menuList', 'table','treeTable'], function(){
        var table = layui.table
                ,form = layui.form,admin=layui.admin,treeTable=layui.treeTable;
    });
</script>
</body>
</html>
