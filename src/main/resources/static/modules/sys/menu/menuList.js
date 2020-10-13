;layui.define(["table", "treeTable", "form"], function (t) {
    var e = layui.$,table = layui.table,treetable=layui.treeTable,form = layui.form,admin=layui.admin;
    var instb=treetable.render({
        elem: "#LAY-app-content-list",
        url: "/sys/menu",
        tree: {
            iconIndex: 2,
            isPidData: true,
            idName: 'id',
            pidName: 'parentId',
            onlyIconControl:true,
            arrowType:'arrow2',
            getIcon: 'ew-tree-icon-style2',
            openName:'open'
        },

        cols: [[{type: "checkbox", fixed: "left"},
            {field: "id", title: "菜单ID", width:100, sort: !0},
            {field: "menuName",title: "菜单名称"},
            {field: "url", title: "请求路径"},
            {field:"menuType",title:"菜单类型",witdh:80,templet:"#table-menuType-btn"},
            {field:"permission",title:"权限编码"},
            {
                field: "status",
                title: "状态",
                templet: "#buttonTpl",
                width: 80,
                align: "center"
            },
            {
                title: "操作",
                minWidth: 150,
                align: "center",
                fixed: "right",
                toolbar: "#table-content-list"
            }]],
        page: false,
        text: "对不起，加载出现异常！",
        toolbar: "#toolbar-operation-btn"
    });
    treetable.on("toolbar(LAY-app-content-list)",function(t){
        var checkData = t.data; //获取选中的数据
        switch(t.event) {
            case 'add':
                layer.open({
                    type: 2
                    ,title: '添加菜单'
                    ,content: '/sys/menu/form/add/-1'
                    ,maxmin: true
                    ,scrollbar:false
                    ,area: ['700px', '480px']
                    ,btn: ['确定', '取消']
                    ,yes: function(e, idx){
                        var l = window["layui-layer-iframe" + e],
                            a = idx.find("iframe").contents().find("#layuiadmin-app-form-submit");
                        l.layui.form.on("submit(layuiadmin-app-form-submit)", function (res) {
                            var field = res.field; //获取提交的字段
                            admin.req({
                                type:'post',
                                url: "/sys/menu/saveOrUpdate",
                                data:field,
                                cache: false,
                                done:function(res){
                                    layer.msg('保存成功！', {
                                        time: 1000 //20s后自动关闭
                                    },function(){
                                        instb.reload();
                                        layer.close(e); //再执行关闭
                                    });
                                }
                            });
                        }), a.trigger("click");
                    }
                });
                break;
            case 'expand':
                instb.expandAll();
                break;
            case 'fold':
                instb.foldAll();
                break;
        }
    });
    treetable.on("tool(LAY-app-content-list)", function (t) {
        var e = t.data;
        switch(t.event){
            case 'del':
                layer.confirm("确定删除该菜单？", function (cf) {
                    admin.req({
                        type: "DELETE",
                        cache:false,
                        url: "/sys/menu/delete/"+e.id,
                        done:function(res){
                            layer.msg('删除成功！', {
                                time: 1000 //20s后自动关闭
                            });
                            instb.reload();
                            layer.close(cf);
                        }
                    });
                });
                break;
            case 'edit':
                layer.open({
                    type: 2,
                    title: "编辑菜单信息",
                    content: "/sys/menu/form/edit/"+e.id,
                    maxmin: !0,
                    area: ['700px', '480px'],
                    btn: ["确定", "取消"],
                    yes: function (e, idx) {
                        var l = window["layui-layer-iframe" + e],
                            a = idx.find("iframe").contents().find("#layuiadmin-app-form-edit");
                        l.layui.form.on("submit(layuiadmin-app-form-edit)", function (res) {
                            var data=res.field;
                            var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                            admin.req({
                                type:'post',
                                url: "/sys/menu/saveOrUpdate",
                                data:data,
                                cache: false,
                                done:function(res){
                                    layer.msg('保存成功！', {
                                        time: 1000 //20s后自动关闭
                                    },function(){
                                        instb.reload();
                                        layer.close(e); //再执行关闭
                                    });
                                }
                            });
                        }), a.trigger("click");
                    },
                    success: function(layero, index){
                        admin.req({
                            type: "GET",
                            cache:false,
                            url: "/sys/menu/view/"+e.id,
                            done:function(res){
                                var data=res.data;
                                var div = layero.find('iframe').contents().find('#layuiadmin-app-form-list');
                                for (fd in data){
                                    div.find("input[name=\'"+fd+"\']").not("input:radio").val(data[fd]);
                                }
                                var checked=(data.isShow==0)?"checked":"";
                                div.find("input[name='isShow']").prop("checked",checked);
                                div.find("input[name='menuType'][value=\'"+data.menuType+"\']").prop("checked",true);
                            }
                        });
                    }
                });
                break;
            case 'addChild':
                layer.open({
                    type: 2
                    ,title: '添加子菜单'
                    ,content: '/sys/menu/form/addChild/'+e.id+'?type='+e.menuType
                    ,maxmin: true
                    ,scrollbar:false
                    ,area: ['700px', '480px']
                    ,btn: ['确定', '取消']
                    ,yes: function(e, idx){
                        var l = window["layui-layer-iframe" + e],
                            a = idx.find("iframe").contents().find("#layuiadmin-app-form-submit");
                        l.layui.form.on("submit(layuiadmin-app-form-submit)", function (res) {
                            var field = res.field; //获取提交的字段
                            admin.req({
                                type:'post',
                                url: "/sys/menu/saveOrUpdate",
                                data:field,
                                cache: false,
                                done:function(res){
                                    layer.msg('保存成功！', {
                                        time: 1000 //20s后自动关闭
                                    },function(){
                                        instb.reload();
                                        layer.close(e); //再执行关闭
                                    });
                                }
                            });
                        }), a.trigger("click");
                    }
                });
                break;
        }
    });
    t("menuList", {});
    form.on('submit(LAY-app-contlist-search)', function(data){
        var field = data.field;
        instb.filterData(field.keyword);
    });
});