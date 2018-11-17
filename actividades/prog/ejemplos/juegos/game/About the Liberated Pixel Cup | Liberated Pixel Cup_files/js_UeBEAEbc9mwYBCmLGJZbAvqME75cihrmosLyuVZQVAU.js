(function($) { 
    $(function() {
        var bar_width = $('#progress_bar').width();
        var total = $('#progress_bar').attr('data-total');
        var progress = $('#progress_bar').attr('data-progress');
        var width = Math.floor(progress / total * 100);
        
        $('#progress_bar .progress').animate({'width': width + "%"}, 1000); 
    });
})(jQuery);
;
