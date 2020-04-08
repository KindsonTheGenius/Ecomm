$('document').ready(function(){
    $('.table #orderButton').on('click', function(event){
        event.preventDefault();
        var href = $(this).attr('href');
        $('#orderModel #orderRef').attr('href', href);
        $('#orderModal').modal();
    });
});