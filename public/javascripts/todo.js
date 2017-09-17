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

    $('#add-todo').click(function() {
        var todo_title = $('#new-todo').val()
        if(todo_title){
            $.get('todo/add',
            {
                title: todo_title
            },
            function(){
                $('#myModal').modal('hide');
                location.reload()
            })
        }
    });

    context.init({preventDoubleContext: false});

    // 右クリックメニュー
    context.attach("#todo_body",[
        {header: 'Status'},
        {text: 'mark as UNDONE', action: function(e){
            reqUpdateTodoStatus("UNDONE")
        }},
        {text: 'mark as DOING', action: function(e){
            reqUpdateTodoStatus("DOING")
        }},
        {text: 'mark as DONE', action: function(e){
            reqUpdateTodoStatus("DONE")
        }}
    ])

    function reqUpdateTodoStatus(status){
        $.get('/todo/update',
            {
                ids: getSelectedIds,
                status: status
            },
            function(){location.reload()}
        )
    }

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