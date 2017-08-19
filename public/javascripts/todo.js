$(document).ready(function() {

    // チェックボックスをクリックしたらチェックが入る
    $('input[type="checkbox"]').click(function() {
        var c= $(this)
        if(c.prop('checked'))
            c.prop('checked', '');
        else
            c.prop('checked', 'checked');
    });

    // テーブルの列をクリックしてもチェックが入る
    $('table tr').click(function() {
      var c = $(this).children('td').children('input[type=checkbox]');
        if(c.prop('checked'))
            c.prop('checked', '');
        else
            c.prop('checked', 'checked');
    });

    context.init({preventDoubleContext: false});

    // 右クリックメニュー
    context.attach("#todo_body",[
        {header: 'Status'},
        {text: 'mark as UNDONE', action: function(e){
            console.log(getSelectedIds())
        }},
        {text: 'mark as DOING', action: function(e){
            console.log(getSelectedIds())
        }},
        {text: 'mark as DONE', action: function(e){
            console.log(getSelectedIds())
        }}
    ])

    // チェックの入っている列のIDを取得する
    function getSelectedIds() {
        var ids = [];
        var rows = $('input[type=checkbox]:checked').parents("tr")
        $.each(rows, function(){
            ids.push($(this).children('td[class=id]').text());
        });
        return ids;
    }

});