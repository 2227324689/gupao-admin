
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>咕泡云课堂 - 菜单编辑</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" href="/static/layui/css/layui.css" media="all">
</head>
<body>
<div class="layui-form" lay-filter="layuiadmin-app-form-list" id="layuiadmin-app-form-list" style="padding: 20px 30px 0 0;">
    <div class="layui-form-item">
        <input type="text" name="id" class="form-control" hidden="hidden">
        <label class="layui-form-label">父级菜单：</label>
        <input type="hidden" id="parentId" value="${parentId}" name="parentId"/>
        <input type="hidden" id="icon" value="${defaultIcon!}" name="icon"/>
        <div class="layui-input-block">
            <input type="text" lay-filter="menu-tree" id="menu-tree" placeholder="请输入标题" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">菜单类型：</label>
        <div class="layui-input-block">
            <input type="radio" name="menuType" lay-filter="menuType-filter" value="direc" title="目录" checked>
            <input type="radio" name="menuType" lay-filter="menuType-filter" value="menu" title="菜单">
            <input type="radio" name="menuType" lay-filter="menuType-filter" value="api" title="按钮">
        </div>
    </div>
    <div id="menu-icon-div">
        <div class="layui-form-item" id="menu-icon">
            <label class="layui-form-label">菜单图标：</label>
            <div class="layui-input-block">
                <input type="text" id="iconPicker" lay-filter="iconPicker" class="hide">
            </div>
        </div>
    </div>
    <div class="layui-row layui-form-item">
        <div class="layui-col-xs6">
            <label class="layui-form-label">菜单名称：</label>
            <div class="layui-input-block">
                <input type="text" name="menuName" lay-verify="required" autocomplete="off" class="layui-input" placeholder="设置菜单名称">
            </div>
        </div>
        <div class="layui-col-xs6">
            <label class="layui-form-label">菜单排序：</label>
            <div class="layui-input-block">
                <input type="number" name="sort" lay-verify="required" autocomplete="off" class="layui-input" placeholder="设置菜单顺序">
            </div>
        </div>
    </div>
    <div id="type-of-operation-div">
        <div class="layui-row layui-form-item" id="type-of-operation">
            <div class="layui-col-xs6">
                <label class="layui-form-label">是否外链：</label>
                <div class="layui-input-block">
                    <input type="radio" name="isLink"  value="0" title="是">
                    <input type="radio" name="isLink" value="1" title="否" checked>
                </div>
            </div>
            <div class="layui-col-xs6">
                <label class="layui-form-label">显示状态：</label>
                <div class="layui-input-block">
                    <input type="checkbox" name="isShow" value="0" lay-filter="isShow" lay-skin="switch" checked lay-text="显示|隐藏">
                </div>
            </div>
        </div>
    </div>
    <div id="menu_operation">
        <div class="layui-row layui-form-item" id="access_auth_div">
            <div class="layui-col-xs6">
                <label class="layui-form-label">权限标识：</label>
                <div class="layui-input-block">
                    <input type="text" name="permission"  id="permission" class="layui-input" autocomplete="off" placeholder="设置权限标识">
                </div>
            </div>
            <div id="access_path_div">
                <div class="layui-col-xs6" id="access_path">
                    <label class="layui-form-label">访问路径：</label>
                    <div class="layui-input-block">
                        <input type="text" name="url" id="path" class="layui-input" autocomplete="off" placeholder="设置访问路径">
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="layui-form-item layui-hide">
        <input type="button" lay-submit lay-filter="layuiadmin-app-form-submit" id="layuiadmin-app-form-submit" value="确认添加">
        <input type="button" lay-submit lay-filter="layuiadmin-app-form-edit" id="layuiadmin-app-form-edit" value="确认编辑">
    </div>
</div>
<script src="/static/layui/layui.js"></script>
<script>
    layui.config({
        base: '/static/' //静态资源所在路径
    }).extend({
        //主入口模块
        index: 'lib/index',
        iconPicker: 'iconPicker/iconPicker',
        treeSelect:'treeSelect/treeSelect'
    }).use(['index', 'form','iconPicker',"treeSelect"], function(){
        var $ = layui.$,form = layui.form,admin=layui.admin,iconPicker=layui.iconPicker,treeSelect=layui.treeSelect;

        var menuHtml='<div class="layui-row layui-form-item" id="access_auth_div">\n' +
                '            <div class="layui-col-xs6">\n' +
                '                <label class="layui-form-label">权限标识：</label>\n' +
                '                <div class="layui-input-block">\n' +
                '                    <input type="text" name="permission" value="${permission!}" id="permission" class="layui-input" autocomplete="off" placeholder="设置权限标识">\n' +
                '                </div>\n' +
                '            </div>\n' +
                '            <div id="access_path_div">\n' +
                '                <div class="layui-col-xs6" id="access_path">\n' +
                '                    <label class="layui-form-label">访问路径：</label>\n' +
                '                    <div class="layui-input-block">\n' +
                '                        <input type="text" name="url" id="path" value="${path!}" class="layui-input" autocomplete="off" placeholder="设置访问路径">\n' +
                '                    </div>\n' +
                '                </div>\n' +
                '            </div>\n' +
                '        </div>';
        var typeOfOperationHtml='<div class="layui-row layui-form-item" id="type-of-operation">\n' +
                '            <div class="layui-col-xs6">\n' +
                '                <label class="layui-form-label">是否外链：</label>\n' +
                '                <div class="layui-input-block">\n' +
                '                    <input type="radio" name="isLink"  value="0" title="是">\n' +
                '                    <input type="radio" name="isLink" value="1" title="否" checked>\n' +
                '                </div>\n' +
                '            </div>\n' +
                '            <div class="layui-col-xs6">\n' +
                '                <label class="layui-form-label">显示状态：</label>\n' +
                '                <div class="layui-input-block">\n' +
                '                    <input type="checkbox" name="isShow" lay-filter="isShow" value="0" checked lay-skin="switch" lay-text="显示|隐藏">\n' +
                '                </div>\n' +
                '            </div>\n' +
                '        </div>';
        var iconHtml='<div class="layui-form-item" id="menu-icon">\n' +
                '            <label class="layui-form-label">菜单图标：</label>\n' +
                '            <div class="layui-input-block">\n' +
                '                <input type="text" id="iconPicker" lay-filter="iconPicker" class="hide">\n' +
                '            </div>\n' +
                '        </div>';
        var accessPathHtml='<div class="layui-col-xs6" id="access_path">\n' +
                '                    <label class="layui-form-label">访问路径：</label>\n' +
                '                    <div class="layui-input-block">\n' +
                '                        <input type="text" name="url" id="path" value="${path!}" class="layui-input" autocomplete="off" placeholder="设置访问路径">\n' +
                '                    </div>\n' +
                '                </div>';
        function direcType(){
            $("#access_auth_div").remove();
            $("#menu-icon-div").html(iconHtml);
            $("#type-of-operation-div").html(typeOfOperationHtml);
            $("#access_path_div").html(accessPathHtml);
        }
        function menuType(){
            $("#menu_operation").html(menuHtml);
            $("#menu-icon-div").html(iconHtml);
            $("#type-of-operation-div").html(typeOfOperationHtml);
            $("#access_path_div").html(accessPathHtml);
        }
        function apiType(){
            $("#menu_operation").html(menuHtml);
            $("#menu-icon").remove();
            $("#type-of-operation").remove();
            $("#access_path").remove();
        }
        function reloadIconPick(){
            iconPicker.render({
                // 选择器，推荐使用input
                elem: '#iconPicker',
                // 数据类型：fontClass/unicode，推荐使用fontClass
                type: 'fontClass',
                // 是否开启搜索：true/false，默认true
                search: true,
                // 是否开启分页：true/false，默认true
                page:true,
                click: function (data) {
                    $("#icon").val(data.icon);
                },
                // 渲染成功后的回调
                success: function(d) {
                    iconPicker.checkIcon('iconPicker', '${defaultIcon!}');
                }
            });
        }
        function reloadForm(){
            form.render();
        }
        function reloadSwitch(){
            form.on('switch(isShow)',function(data){
                var status=data.elem.checked?0:1;
                $(data.elem).attr('type', 'hidden').val(status);
            });
        }
        var type="${type}";
        switch(type){
            case 'direc':
                direcType();
                break;
            case 'menu':
                menuType();
                break;
            case 'api':
                apiType();
                break;
        }
        $("input[name='menuType'][value=\'"+type+"\']").prop("checked",true);
        reloadIconPick();
        var index = parent.layer.getFrameIndex(window.name);
        parent.layer.iframeAuto(index);
        var checked=(${isShow}==0)?"checked":"";
        $("input[name='isShow']").prop("checked",checked);
        reloadForm();
        reloadSwitch();
        form.on('radio(menuType-filter)',function(data){
            switch(data.value){
                case 'direc':
                    direcType();
                    reloadForm();
                    reloadIconPick();
                    reloadSwitch();
                    break;
                case 'menu':
                    menuType();
                    reloadForm();
                    reloadIconPick();
                    reloadSwitch();
                    break;
                case 'api':
                    apiType();
                    reloadSwitch();
                    break;
            }
            var index = parent.layer.getFrameIndex(window.name);
            parent.layer.iframeAuto(index);
        });

        treeSelect.render({
            elem:"#menu-tree",
            data: "/sys/menu/treeList",
            type:"get",
            placeholder:'默认为主目录',
            search:true,
            style: {
                folder: {
                    enable: true
                },
                line: {
                    enable: true
                }
            },
            click:function(d){
                $("#parentId").val(d.current.id);
                treeSelect.checkNode('menu-tree',d.current.id);
            },
            success: function(d){
                treeSelect.checkNode('menu-tree',${parentId!});
            }
        });
    })
</script>
</body>
</html>