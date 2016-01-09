$(document).ready(function() {              
    $('#btnSubmit').click(function(event) { 
        var username=$('#txtName').val();
        $.get('ChangeLangageServlet',{user:username},function(responseText) {
            $('#welcometext').text(responseText);        
        });
    });
});