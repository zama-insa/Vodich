/*
  function switchLang(lang){
	console.log("coucou");
	$("#langForm").submit();
	}
*/	


var switchLang = function(language) {
$.ajax({
   url: ctx + "/locale.do",
   data: "language=" + language,
   success: function(msg) {
     location.reload();
   }
});
};
