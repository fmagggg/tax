$(function(){
    $('#exportBtn').click(function(){
        var file = $('#file-1')[0].files[0];
        if(file == undefined){
            swal({
                title: "主人，选一个文件吧",
                type: "error"
            });
            return false;
        }
        var size = Math.floor(file.size / 1024);
        if (size > 5*1024) {
            swal({
                title: "大于5M的文件小东哥都不让过",
                type: "error"
            });
            return false;
        }
        var filterColNum = $('#filterColNum').val();
        var filterContent = $('#filterContent').val();
        var formData = new FormData();
        formData.append('file', file);
        formData.append('filterColNum', filterColNum);
        formData.append('filterContent', filterContent);
        $('#loaderModal').modal('show');
        $('#exportBtn').fadeOut();
        $.ajax({
            type: "POST",
            processData:false,
            contentType:false,
            data:formData,
            timeout:6000,
            url: ctxPath + 'api/v1/excel/sheet-filter/upload',
            success: function(res){
                $('#loaderModal').modal('hide');
                $('#exportBtn').fadeIn();
                window.open('https://pany2020.oss-cn-beijing.aliyuncs.com/'+res, 'download');
            },
            error:function(res){
                console.log(res)
            }
        });
    });
})