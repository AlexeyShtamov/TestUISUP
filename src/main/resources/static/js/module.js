$(document).ready(function () {
    $(".table .updateButton").on('click', function (event){
        event.preventDefault();
        var href = $(this).attr('href');
        $.get(href, function (module, status){
            $('#updateId').val(module.uuid);
            $('#updateName').val(module.title);
            $('#typeId').val(module.type);
        })
        $('#updateModal').modal('show');
    });

    $('.table .deleteButton').on('click', function (event){
        event.preventDefault();
        var href = $(this).attr('href');
        $('#deleteModal #deleteId').attr('href', href)
        $('#deleteModal').modal("show");
    });
    (() => {
        'use strict'

        const forms = document.querySelectorAll('.needs-validation')

        Array.from(forms).forEach(form => {
            form.addEventListener('submit', event => {
                if (!form.checkValidity()) {
                    event.preventDefault()
                    event.stopPropagation()
                }

                form.classList.add('was-validated')
            }, false)
        })
    })()
});