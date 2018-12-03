/**
 * 全选checkBox
 */
function checkAll(obj,checkBoxName){
	var checkBoxs = [];
	checkBoxs = document.getElementsByName(checkBoxName);
	for(var i = 0;i<checkBoxs.length;i++){
		checkBoxs[i].checked = obj.checked;
	}
}