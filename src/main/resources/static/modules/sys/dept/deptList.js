;layui.define(["table", "form","eleTree","treeSelect"], function (t) {
    var e = layui.$, i = layui.table, n = layui.form,admin=layui.admin,eleTree=layui.eleTree,
    treeSelect=layui.treeSelect;
    t("deptList", {});
    treeSelect.render({
        elem:"#tree",
        data: "/sys/dept/treeSelectList",
        type:"get",
        placeholder:'选择父级部门',
        search:true,
        click:function(d){
            e("#parentId").val(d.current.id);
            treeSelect.checkNode('tree',d.current.id);
        },
        success: function(d){
        }
    });
    eleTree.render({
        elem:"#departTreeMenu",
        data:getTreeData(),
        defaultExpandAll:true,
        highlightCurrent:true,
    });
    eleTree.on("nodeClick",function(obj){
        var data=obj.data.currentData;
        admin.req({
            type: "GET",
            url: "/sys/dept/getDeptById/"+data.id,
            done:function(res){
                var rsData=res.data;
                for (var fd in rsData){
                    e("input[name=\'"+fd+"\']").not("input:radio").val(rsData[fd]);
                }
                treeSelect.checkNode("tree",rsData.parentId);
            }
        });
    })

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