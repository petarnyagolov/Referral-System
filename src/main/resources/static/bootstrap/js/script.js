 $(".col-sm-6").hide();


$(".row").click(function(){
    $(".col-sm-6").dropdown(1)
    $(this).children().show(100);
});