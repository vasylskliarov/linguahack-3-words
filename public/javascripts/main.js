$(document).ready(function() {
    $('a.page-scroll').bind('click', function(event) {
        $.ajax({
            type : 'GET',
            url: "/text",
            success: function(data) {
                initContent(data);
                console.log(data);
            }
        });

        var $anchor = $(this);
        $('html, body').stop().animate({
            scrollTop: $($anchor.attr('href')).offset().top
        }, 1500, 'easeInOutExpo');
        event.preventDefault();
    });

    $(".btn-next-text").click(function() {
        var unknownWords = [];
        $(".dont-know").each(function(index, el) {
            unknownWords.push(this.innerText.replace(/\W/g, ''));
        });


        $.ajax({
            type : 'POST',
            data: JSON.stringify({unknownWords: unknownWords}),
            url: "/text/next",
            dataType: "json",
            contentType : 'application/json',
            success: function(data) {
                initContent(data);
                console.log(data);
            }
        });
    });

    function initContent(data) {
        $(".text").html(data.content);

        var showed = [];
        var template = '<div class="popover" role="tooltip">' +
            '<div class="arrow"></div>' +
            '<h3 class="popover-title"></h3>' +
            '<div class="popover-content"></div>' +
            '<div class="text-center"><button id="do-not-know-$$INDEX$$" type="button" class="btn btn-danger">Не знаю</button></div></div>';
        $(".text").lettering('words');


        $(".text .word").each(function(index, element) {
            $(element).on("click", function() {
                if ($(element).hasClass("dont-know")) {
                    return;
                }

                $(showed).each(function (index, el) {
                    $(el).popover("hide");
                });
                showed = [];

                showed.push(this);

                $(element).popover({
                    title: "",
                    content: element.innerText.replace(/\W/g, ''),
                    trigger: 'manual',
                    placement: 'bottom',
                    template: template.replace("$$INDEX$$", index)
                }).on('shown.bs.popover', function() {
                    // set what happens when user clicks on the button
                    $(element).addClass("not-sure");
                    $("#do-not-know-" + index).on('click', function(){
                        $(element).addClass("dont-know");
                        $(element).popover("hide");
                    });
                }).on('hide.bs.popover', function() {
                    $(element).removeClass("not-sure");
                }).popover("show");

            });
        });
    }
});