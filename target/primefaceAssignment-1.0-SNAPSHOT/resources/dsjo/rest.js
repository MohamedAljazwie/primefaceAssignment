$(document).ready(function () {
    var message = {};
    var employee = {};
    getAllEmployees();
    $('#btnAddEmployee').click(function () {
        message.title = 'SAVEORUPDATEEMPLOYEE';
        message.status = 'SAVEORUPDATE';
        message.message = employee;
        var messageJSON = JSON.stringify(message);
        $.ajax({
            url: 'http://localhost:9090/primefaceAssignment/restful/employeeManagement/employees',
            method: 'POST',
            data: messageJSON,
            contentType: "application/json; charset=utf-8",
            success: function () {
                alert('Saved successfully!');
                getAllEmployees();
            },
            error: function (error) {
                alert(error);
            }
        });

    });
});

function getAllEmployees() {
    var message = {};
    var employee = {};
    message.title = 'EMPLOYEES';
    message.status = 'EMPLOYEES';
    message.message = employee;
    var messageJSON = JSON.stringify(message);
    $.ajax({
        url: 'http://localhost:9090/primefaceAssignment/restful/employeeManagement/employees',
        type: 'POST',
        dataType: 'JSON',
        contentType: "application/json; charset=utf-8",
        data: messageJSON,
        success: function (data) {
            var employees = data.message;
            var tableBody = $('#tbl');
            var employees = data.message;
//            tableBody.val(JSON.stringify(employees));
            $("#tbl").find('tbody').empty();
            $(employees).each(function (index, element) {
                $("#tbl").find('tbody')
                        .append($('<tr>')
                                .append($('<td>').addClass("ui-column-title").text(element.empCode))
                                .append($('<td>').addClass("ui-column-title").text(element.empName))
                                .append($('<td>').addClass("ui-column-title").text(element.empEmail))
                                .append($('<td>').addClass("ui-column-title").text(element.empAddress))
                                .append($('<td>')
                                                 .append($('<button>').addClass("ui-button ui-widget ui-state-default ")
                                                                       .attr("onclick","#{mbEmployee.setUpdateemployee(employee)}")
                                                                       .attr("title","update")
                                                                       .attr("type","submit")
                                                                       .attr("role","button")
                                                                       .append($('<span>').addClass("ui-button-text ui-c")
                                                                                          .text("update"))
                                   ))
                               .append($('<td>')
                                                 .append($('<button>').addClass("ui-button ui-widget ui-state-default ")
                                                                       .attr("onclick","#{mbEmployee.setUpdateemployee(employee)}")
                                                                       .attr("title","Delete")
                                                                       .attr("type","submit")
                                                                       .attr("role","button")
                                                                       .append($('<span>').addClass("ui-button-text ui-c")
                                                                                          .text("Delete"))
                                   ))
                                );
                        
            });
        },
        error: function (msg) {
            alert('ERROR   ' + msg);
        }
    });
}