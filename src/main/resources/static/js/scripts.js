
$( function() {
    $( "#dateBirth" ).datepicker({
        showOn: "button",
        buttonImage: "images/calendar.gif",
        buttonImageOnly: true,
        changeMonth: true,
        changeYear: true,
        buttonText: "Select date"
    });
} );


$( "#new" ).submit(function() {

    $('#message-box').html('').hide();

    if($("#name").val() == ''){
        $('#message-box').html('Name: Required field').show();
        return false;
    }

    if($("#ppsNumber").val() == ''){
        $('#message-box').html('PPS Number: Required field').show();
        return false;
    }

    if($("#dateBirth").val() == ''){
        $('#message-box').html('Date Of Birth: Required field').show();
        return false;
    }

    if($("#name").val().length > 25){
        $('#message-box').html('Name: Max 25 Characters').show();
        return false;
    }

    return true;

});