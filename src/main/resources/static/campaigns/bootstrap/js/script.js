$("#sum").css("display", "block");
$("#percent").css("display", "none");
let val = $("#campaignType").val();

function change(){
    val = $("#campaignType").val();

    if(val=="topup" || val=="discountTopup" ){
        $("#sum").css("display", "block");
    }else {
        $("#sum").css("display", "none");
    }
    if(val=="percentTopup"||val=="discountPercentTopup"){
        $("#percent").css("display", "block");
    }else {
        $("#percent").css("display", "none");
    }
}