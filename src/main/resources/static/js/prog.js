$(document).ready(function () {
    $(".table .updateButton").on('click', function (event){
        event.preventDefault();
        var href = $(this).attr('href');
        $.get(href, function (program, status){
            $('#updateId').val(program.uuid);
            $('#updateName').val(program.title);
            $('#updateCypher').val(program.cypher);
            $('#updateLevel').val(program.level);
            $('#updateStandard').val(program.standard);
            $('#updateInstitute').val(program.instituteTitle);
            $('#updateHead').val(program.headName);
            $('#updateDate').val(program.accreditationTime);
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

