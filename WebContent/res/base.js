/***
 * 创建一个匿名函数，立即调用
 ***/
(function(){
   var foo = 10;
   var bar = 2;
   alert(foo*bar);
})();
/***
 * 创建一个带参数的匿名函数，传参调用
 **/
(function(a,b){
	alert(a*b);
})(25,4);
/**
 * 定义类
 **/
var Anim = function(){
	
};
Anim.prototype.start = function(){
	
};
Anim.prototype.stop = function(){
	
};
var myAnim = new Anim();
myAnim.start();
myAnim.stop();
