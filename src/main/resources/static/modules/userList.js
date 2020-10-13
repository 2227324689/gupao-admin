;layui.define(["table", "form","tree","treeSelect","eleTree"], function (t) {
    var e = layui.$, table = layui.table, n = layui.form,admin=layui.admin,eleTree=layui.eleTree,treeSelect=layui.treeSelect;
    table.render({
        elem: "#LAY-user-content-list",
        url: "/sys/user",
        even: true,
        toolbar:"#toolbar-operation-btn",
        skin:'row',
        cols: [[
            {type: "checkbox", fixed: "left"},
            {field: "id", width: 100, title: "用户ID", sort: !0},
            {field: "username",title: "用户名",minWidth: 100},
            {field: "phone", title: "电话"},
            {field: "email", title: "邮箱"},
            {field: "realName",title: "真实姓名",sort: !0},
            {field:"createTime",title:"注册日期",templet:"<div>{{layui.util.toDateString(d.createTime, 'yyyy年MM月dd日 HH:mm:ss')}}</div>"},
            {field: "status", title: "状态", templet: "#buttonTpl", minWidth: 80, align: "center"},
            {title: "操作",minWidth: 150,align: "center",fixed: "right",toolbar: "#table-content-list"}
            ]],
        page: !0,
        limit: 10,
        limits: [10, 15, 20, 25, 30],
        text: {
            none: "该部门下暂无相关人员！"
        }
    });
    table.on('toolbar(LAY-app-content-list)',function(obj){
        var checkStatus = table.checkStatus(obj.config.id)
            ,checkData = checkStatus.data; //获取选中的数据
        switch(obj.event){
            case 'add':
                //获得当前选择的deptId
                var deptId=e("#deptId").val();
                layer.open({
                    type: 2
                    ,title: '添加用户'
                    ,content: '/sys/user/form/'+deptId+"/0"
                    ,maxmin: true
                    ,scrollbar:false
                    ,area: ['750px', '580px']
                    ,btn: ['确定', '取消']
                    ,yes: function(e, idx){
                        var l = window["layui-layer-iframe" + e],
                            a = idx.find("iframe").contents().find("#layuiadmin-app-form-submit");
                        l.layui.form.on("submit(layuiadmin-app-form-submit)", function (res) {
                            var field = res.field; //获取提交的字段
                            admin.req({
                                type:'post',
                                url: "/sys/user/saveOrUpdate",
                                data:field,
                                cache: false,
                                done:function(res){
                                    layer.msg('保存成功！', {
                                        time: 1000 //20s后自动关闭
                                    },function(){
                                        table.reload("LAY-user-content-list",{
                                            curr:1
                                        });
                                        layer.close(e); //再执行关闭
                                    });
                                }
                            });
                        }), a.trigger("click");
                    }
                });
                break
            case 'batchdel':
                if(checkData.length === 0){
                    return layer.msg('请选择数据');
                }
                var ids=[];
                for(var i=0;i<checkData.length;i++){
                    var row=checkData[i];
                    ids.push(row.id);
                }
                layer.confirm('确定删除吗？', function(index) {
                    admin.req({
                        type: "POST",
                        cache:false,
                        url: "/sys/user/batchDelete",
                        data:{ids:ids},
                        done:function(res){
                            layer.msg('删除成功！', {
                                time: 2000 //20s后自动关闭
                            });
                            layer.close(index);
                            table.reload("LAY-user-content-list");
                        }
                    });
                });
                break;
            default:
                break;
        }
    });
    table.on("tool(LAY-app-content-list)", function (t) {
        var e = t.data;
        "del" === t.event ? layer.confirm("确定删除该用户？", function (cf) {
            admin.req({
                type: "DELETE",
                cache:false,
                url: "/sys/user/delete/"+e.id,
                done:function(res){
                    layer.msg('删除成功！', {
                        time: 1000 //20s后自动关闭
                    });
                    table.reload("LAY-user-content-list",{
                        curr:1
                    });
                    layer.close(cf);
                }
            });
        }) : "edit" === t.event && layer.open({
            type: 2,
            title: "编辑用户信息",
            content: "/sys/user/form/"+e.deptId+"/"+e.id,
            maxmin: !0,
            scrollbar:false,
            area: ["750px", "580px"],
            btn: ["确定", "取消"],
            yes: function (e, idx) {
                var l = window["layui-layer-iframe" + e],
                    a = idx.find("iframe").contents().find("#layuiadmin-app-form-edit");
                l.layui.form.on("submit(layuiadmin-app-form-edit)", function (res) {
                    var data=res.field;
                    admin.req({
                        type:'post',
                        url: "/sys/user/saveOrUpdate",
                        data:data,
                        cache: false,
                        done:function(res){
                            layer.msg('保存成功！', {
                                time: 1000 //20s后自动关闭
                            },function(){
                                table.reload("LAY-user-content-list",{
                                    curr:1
                                });
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
                    url: "/sys/user/view/"+e.id,
                    done:function(res){
                        var data=res.data;
                        var div = layero.find('iframe').contents().find('#layuiadmin-app-form-list');
                        for (var fd in data){
                            div.find("input[name=\'"+fd+"\']").not("input:radio").not("input[name='roleIds']").val(data[fd]);
                        }
                        var checked=(data.status==1)?"checked":"";
                        div.find("input[name='status']").prop("checked",checked);
                        div.find("input[name='sex'][value=\'"+data.sex+"\']").prop("checked",true);
                        div.find("select[name='postId']").find("option[value='"+data.postId+"']").attr("selected",true);
                    }
                });
            }
        })
    });
    t("userList", {});
    eleTree.render({
        elem:"#departTreeMenu",
        data:getTreeData(),
        onlyIconControl:true,
        defaultExpandAll:true,
        highlightCurrent:true,
        id:"deptTreeId"
    });
    eleTree.on("nodeClick",function(obj){
        var currentData=obj.data.currentData;
        e(".layui-dept-define").text(currentData.label);
        e("#deptId").val(currentData.id);
        var deptId=currentData.id;
        table.reload("LAY-user-content-list",{
            where: {
                deptId:deptId
            }
        });
    });
    function getTreeData(){
        var data;
        admin.req({
            type: "GET",
            cache:false,
            async:false,
            url: "/sys/dept/treeList",
            done:function(res){
                data=res.data;
            }
        });
        return data;
    }
});