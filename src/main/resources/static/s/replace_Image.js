jQuery(document).ready(function($){

    $('.black-button').on({
        'click': function(){
            $('#change-image').attr('src','/images/photo-long-2.jpg');
        }
    });

    $('.red-button').on({
        'click': function(){
            $('#change-image').attr('src','/images/photo-long-4.jpg');
        }
    });

    $('.blue-button').on({
        'click': function(){
            $('#change-image').attr('src','/images/photo-long-3.jpg');
        }
    });

    $('.yellow-button').on({
        'click': function(){
            $('#change-image').attr('src','/images/photo-long-1.jpg');
        }
    });
});
