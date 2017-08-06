$(document).ready(function() {
         $('input[type="checkbox"]').click(function() {
             var c= $(this)
             if(c.prop('checked'))
                 c.prop('checked', '');
             else
                  c.prop('checked', 'checked');
         });

         $('table tr').click(function() {
           var c = $(this).children('td').children('input[type=checkbox]');
             if(c.prop('checked'))
                 c.prop('checked', '');
             else
                 c.prop('checked', 'checked');
         });
     });