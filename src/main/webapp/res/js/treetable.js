/**
 * 构造有循序的table
 */
function createTable(array){
	//数组
	this.tree = array||[];
	//对象
	this.groups={};
	//每一个对应散列表
	this.all={};
};
createTable.prototype={
		init:function(pid){
			this.group();
			return this.getDom(pid,this.groups[pid]);
		},
        //该函数根据父Id分组，数据结构{pId：[]}
        group:function(){
        	for(var i =0;i<this.tree.length;i++){
        		if(this.groups[this.tree[i].pid]){
        			this.groups[this.tree[i].pid].push(this.tree[i]);
        		}else{
        			this.groups[this.tree[i].pid] = [];
        			this.groups[this.tree[i].pid].push(this.tree[i]);
        		}
        		//每一个加入散列表
        		this.all[this.tree[i].id] = this.tree[i]; 
        	}
        },
        //拼接html代码
        getDom:function(pid,menuArray){
        	var trHtml = "";
        	if(pid=='rootMenu'){
	              trHtml=trHtml + "<tr data-tt-id=\""+this.all[pid].id+"\" data-tt-parent-id=\""+this.all[pid].pid+"\">"
		            + "<td>"+this.all[pid].name+"</td>"
		            + "<td>"+this.all[pid].title+"</td>"
		            + "<td></td>"
		            + "<td></td>"
		            + "<td style=\"text-align:center;\">"
		            + "<a title=\"添加子菜单\" style=\"cursor: pointer;\" onclick=\"addChildern('"+this.all[pid].id+"');\">【添加子菜单】</a>"
	                + "</td>"
	                + "</tr>";
        	}
        	if(typeof(menuArray)!='undefined'){
        		for(var i= 0;i<menuArray.length;i++){
            		//先构造根行
        			var add =  "<a title=\"添加子菜单\" style=\"cursor: pointer;\" onclick=\"\">【添加子菜单】</a>";
        			var dele = "<a title=\"删除菜单\"  style=\"cursor: pointer;\" onclick=\"\">【删除】</a>";
        			//没有url链接的可以添加子菜单
        			if(menuArray[i].url==null){//
        				 add =  "<a title=\"添加子菜单\" style=\"cursor: pointer;\" onclick=\"addChildern('"+menuArray[i].id+"','"+menuArray[i].name+"');\">【添加子菜单】</a>";
        			}
        			//没有子节点的可以删除
        			if(typeof(this.groups[menuArray[i].id])=='undefined'){
        				dele = "<a title=\"删除菜单\"  style=\"cursor: pointer;\" onclick=\"removeMenu('"+menuArray[i].id+"');\">【删除】</a>";	
        			}
            		trHtml =trHtml + "<tr data-tt-id=\""+menuArray[i].id+"\" data-tt-parent-id=\""+menuArray[i].pid+"\">"
            		            + "<td>"+menuArray[i].name+"</td>"
            		            + "<td>"+menuArray[i].title+"</td>"
            		            + "<td>"+(menuArray[i].mcode==null?'':menuArray[i].mcode)+"</td>"
            		            + "<td>"+(menuArray[i].url==null?'':menuArray[i].url)+"</td>"
            		            + "<td style=\"text-align:center;\">"
            		            +  add
            		        	+ "<a title=\"编辑菜单\"  style=\"cursor: pointer;\" onclick=\"menuEditNew('"+menuArray[i].id+"');\">【编辑】</a>"
    			                + dele
    			                + "</td>"
    			                + "</tr>";
    			      //构造根下的节点
    			                trHtml = trHtml + this.getDom(menuArray[i].id,this.groups[menuArray[i].id]);
            	}
        	}
        	return trHtml;
        	
        }
};