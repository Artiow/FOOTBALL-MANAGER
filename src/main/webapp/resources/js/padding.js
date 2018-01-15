let buffer = $(window).height() - $('body').height();
dataFooter.fHeight = (buffer > 0) ? buffer : 0;

$(function(){
    $(window).resize(function() {
        let buffer = $(window).height() - $('body').height();
        dataFooter.fHeight = (buffer > 0) ? buffer : 0;
    })
})