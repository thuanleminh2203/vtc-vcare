var listExport = [];
var pageIndex = 1;
var pageSize = 10;
var currentPage = 1;
var listPage = [];
var totalPage = 0;
var tab_1 = [];
$(document).ready(function () {
    // Activate tooltip
    $('[data-toggle="tooltip"]').tooltip();

    // Select/Deselect checkboxes
    var checkbox = $('table tbody input[type="checkbox"]');
    $("#selectAll").click(function () {
        if (this.checked) {
            checkbox.each(function () {
                this.checked = true;
            });
        } else {
            checkbox.each(function () {
                this.checked = false;
            });
        }
    });
    checkbox.click(function () {
        if (!this.checked) {
            $("#selectAll").prop("checked", false);
        }
    });
});


function getCTV() {
    let req = ({
        pageIndex,
        pageSize
    })
    getCTVAction(req);
}


const getCTVAction = async (req) => {
    const myHeaders = new Headers({
        'Content-Type': 'application/json',
        'Authorization': 'Bearer ' + localStorage.getItem('item')
    });
    try {
        const response = await fetch('http://10.33.60.12:8088/api/v1/ctv-vcare/customer/search', {
            method: 'POST',
            body: JSON.stringify(req),
            headers: myHeaders,
        });
        const myJson = await response.json();
        drawTable(myJson.data.customerResponses);
        drawHintText(myJson.data.totalRecords);
        drawPagination(myJson.data.totalRecords);
        // drawPagination(0);
        showExportBtn();
    } catch (error) {
        console.error(error);
    }
}

const getCTVActionPagination = async (req) => {
    const myHeaders = new Headers({
        'Content-Type': 'application/json',
        'Authorization': 'Bearer ' + localStorage.getItem('item')
    });
    try {
        const response = await fetch('http://10.33.60.12:8088/api/v1/ctv-vcare/customer/search', {
            method: 'POST',
            body: JSON.stringify(req),
            headers: myHeaders,
        });
        const myJson = await response.json();
        drawTable(myJson.data.customerResponses);
        // drawPagination(0);
        showExportBtn();
    } catch (error) {
        console.error(error);
    }
}


const getCTVActionExportExcel = async (req) => {
    const myHeaders = new Headers({
        'Content-Type': 'application/json',
        'Authorization': 'Bearer ' + localStorage.getItem('item')
    });
    try {
        const response = await fetch('http://10.33.60.12:8088/api/v1/ctv-vcare/customer/search', {
            method: 'POST',
            body: JSON.stringify(req),
            headers: myHeaders,
        });
        const myJson = await response.json();
        listExport = myJson.data.customerResponses;
    } catch (error) {
        console.error(error);
    }
}

function changePagePrevious() {
    currentPage = currentPage - 1;
    if (currentPage == 1) {
        reDraw(1, 2, totalPage, totalPage - 1, totalPage, 0, 1);
        let req = ({
            pageIndex: currentPage,
            pageSize
        });
        getCTVActionPagination(req);
        return true;
    }
    reDraw(currentPage - 1, currentPage, totalPage, totalPage - 1, totalPage, 1, 1);
    let req = ({
        pageIndex: currentPage,
        pageSize
    });
    getCTVActionPagination(req);
}

function changePageNext() {
    currentPage = currentPage + 1;
    if (currentPage == totalPage) {
        reDraw(currentPage - 3, currentPage - 2, totalPage, currentPage - 1, currentPage, 1, 0);
        let req = ({
            pageIndex: currentPage,
            pageSize
        });
        getCTVActionPagination(req);
        return false;
    }

    if (currentPage == (totalPage - 1)) {
        reDraw(currentPage - 2, currentPage - 1, totalPage, currentPage, currentPage + 1, 1, 1);
        let req = ({
            pageIndex: currentPage,
            pageSize
        });
        getCTVActionPagination(req);
        return false;
    }

    reDraw(currentPage - 1, currentPage, totalPage, totalPage - 1, totalPage, 1, 1);
    let req = ({
        pageIndex: currentPage,
        pageSize
    });
    getCTVActionPagination(req);

}

function changePageStep(page) {
    if (page == 1) {
        currentPage = page;
        reDraw(currentPage, currentPage + 1, totalPage, totalPage - 1, totalPage, 0, 1);
        let req = ({
            pageIndex: currentPage,
            pageSize
        });
        getCTVActionPagination(req);
        return true;
    }

    if (page < 5) {
        currentPage = page;
        reDraw(currentPage - 1, currentPage, totalPage, totalPage - 1, totalPage, 1, 1);
        let req = ({
            pageIndex: currentPage,
            pageSize
        });
        getCTVActionPagination(req);
        return true;
    }

    if (page == (totalPage - 1)) {
        currentPage = page;
        reDraw(currentPage - 2, currentPage - 1, totalPage, currentPage, totalPage, 1, 1);
        let req = ({
            pageIndex: currentPage,
            pageSize
        });
        getCTVActionPagination(req);
        return true;
    }

    if (page == totalPage) {
        currentPage = totalPage;
        reDraw(currentPage - 3, currentPage - 2, totalPage, currentPage - 1, currentPage, 1, 0);
        let req = ({
            pageIndex: currentPage,
            pageSize
        });
        getCTVActionPagination(req);
        return true;
    }
    currentPage = page;
    reDraw(currentPage - 3, currentPage - 2, totalPage, currentPage - 1, currentPage, 1, 1);
    let req = ({
        pageIndex: currentPage,
        pageSize
    });
    getCTVActionPagination(req);
}

function drawPagination(total) {
    if (total % 10 > 0) {
        totalPage = Math.floor(total / 10) + 1;
    } else {
        totalPage = Math.floor(total / 10);
    }

    if (currentPage == 1) {
        var pagination = '<li id ="previous" class="page-item disabled"><a class="page-link href="#">Previous</a></li>';
    } else {
        var pagination = '<li onclick="changePagePrevious()" id ="previous" class="page-item active"><a class="page-link href="#">Previous</a></li>';
    }
    if (total == 0) {
        pagination += '<li class="page-item active"><a href="#" class="page-link">Empty</a></li>';
    }

    if (total > 5) {
        pagination += '<li onclick="changePageStep(1)" id="page1st" class="page-item active"><a href="#" class="page-link">1</a></li>';
        pagination += '<li onclick="changePageStep(2)" id="page2nd" class="page-item"><a href="#" class="page-link">2</a></li>';
        pagination += '<li class="page-item"><a href="#" class="page-link">...</a></li>';
        pagination += '<li onclick="changePageStep(' + (totalPage - 1) + ')" id ="last1st" class="page-item"><a href="#" class="page-link">' + (totalPage - 1) + '</a></li>';
        pagination += '<li onclick="changePageStep(' + totalPage + ')" id ="last2nd" class="page-item"><a href="#" class="page-link">' + totalPage + '</a></li>';
    }

    if (currentPage == totalPage) {
        pagination += '<li id="next" class="page-item disabled"><a href="#" class="page-link">Next</a></li>';
    } else {
        pagination += '<li onclick="changePageNext()" id="next" class="page-item active"><a href="#" class="page-link">Next</a></li>';
    }
    document.getElementById("pagination").innerHTML = pagination;
}

function reDraw(value1, value2, totalPage, value4, value5, pre, next) {
    if (pre == 0) {
        var pagination = '<li id ="previous" class="page-item disabled"><a class="page-link href="#">Previous</a></li>';
    } else {
        var pagination = '<li onclick="changePagePrevious()" id ="previous" class="page-item active"><a class="page-link href="#">Previous</a></li>';
    }

    if (currentPage == value1) {
        pagination += '<li onclick="changePageStep(' + value1 + ')" id="page1st" class="page-item active"><a href="#" class="page-link">' + value1 + '</a></li>';
    } else {
        pagination += '<li onclick="changePageStep(' + value1 + ')" id="page1st" class="page-item"><a href="#" class="page-link">' + value1 + '</a></li>';
    }

    if (currentPage == value2) {
        pagination += '<li onclick="changePageStep(' + value2 + ')" id="page2nd" class="page-item active"><a href="#" class="page-link">' + value2 + '</a></li>';
    } else {
        pagination += '<li onclick="changePageStep(' + value2 + ')" id="page2nd" class="page-item"><a href="#" class="page-link">' + value2 + '</a></li>';
    }

    pagination += '<li class="page-item"><a href="#" class="page-link">...</a></li>';

    if (currentPage == value4) {
        pagination += '<li onclick="changePageStep(' + value4 + ')" id ="last1st" class="page-item active"><a href="#" class="page-link">' + value4 + '</a></li>';
    } else {
        pagination += '<li onclick="changePageStep(' + value4 + ')" id ="last1st" class="page-item"><a href="#" class="page-link">' + value4 + '</a></li>';
    }

    if (currentPage == value5) {
        pagination += '<li onclick="changePageStep(' + value5 + ')" id ="last2nd" class="page-item active"><a href="#" class="page-link">' + value5 + '</a></li>';
    } else {
        pagination += '<li onclick="changePageStep(' + value5 + ')" id ="last2nd" class="page-item"><a href="#" class="page-link">' + value5 + '</a></li>';
    }

    if (next == 0) {
        pagination += '<li id="next" class="page-item disabled"><a href="#" class="page-link">Next</a></li>';
    } else {
        pagination += '<li onclick="changePageNext()" id="next" class="page-item active"><a href="#" class="page-link">Next</a></li>';
    }
    document.getElementById("pagination").innerHTML = pagination;
}

function drawHintText(total) {
    var hintText = "Showing <b>10</b> out of <b>" + total + "</b> entries"
    document.getElementById("hint-text").innerHTML = hintText;
}

function drawTable(listCTV) {
    var tbody = "<tbody>";
    listCTV.forEach(element => {
        tbody += "<tr><td class='first-row'>" + element.customerName + "</td>";
        tbody += "<td>" + element.address + "</td>";
        tbody += "<td>" + element.email + "</td>";
        tbody += "<td>" + element.phoneNumber + "</td>";
        tbody += "<td>" + element.introductionCode + "</td>";
        tbody += "<td>" + element.identifyCard + "</td>";
        tbody += "<td>" + element.bankName + "</td>";
        tbody += "<td>" + element.bankAccountName + "</td>";
        tbody += "<td>" + element.bankAccountNumber + "</td>";
        tbody += element.active ? "<td>" + 'Đang hoạt động' + "</td></tr>" : "<td>" + 'Vô hiệu hóa' + "</td></tr>";
    });
    tbody += "</tbody>"
    document.getElementById("tbody").innerHTML = tbody;
}

function showExportBtn() {
    var role = localStorage.getItem('role');
    if (role !== "ROLE_ADMIN") {
        document.getElementById("btnExport").style.display = "none";
    }
}

async function downloadDataExcel() {
    let req = ({
        pageIndex: 0,
        pageSize: 10
    });
    await getCTVActionExportExcel(req);
    exportExcel();
}

function exportExcel() {
    const myHeader = {
        customerName: "Họ và tên",
        address: "Địa chỉ",
        email: "Email",
        phoneNumber: "Số điện thoại",
        introductionCode: "Mã giới thiệu",
        identifyCard: "CMND",
        bankName: "Tên ngân hàng",
        bankAccountName: "Tên tài khoản",
        bankAccountNumber: "Số tài khoản",
        active: "Trạng thái"
    };

    listExport.forEach(element => {
        tab_1.push({
            customerName: element.customerName,
            address: element.address,
            email: element.email,
            phoneNumber: element.phoneNumber,
            introductionCode: element.introductionCode,
            identifyCard: element.identifyCard,
            bankName: element.bankName,
            bankAccountName: element.bankAccountName,
            bankAccountNumber: element.bankAccountNumber,
            active: element.active ? 'Đang hoạt động' : 'Vô hiệu hóa'
        });
    });

    // format header
    tab_1.unshift(myHeader);
    let excel = XlsxPopulate.fromBlankAsync()
        .then(workbook => {
            // Add sheet the workbook.
            let sheet1 = workbook.addSheet("Report_1");

            sheet1.cell("A1").value(convertArrayObjectToNestedArray(tab_1))

            sheet1 = setHeaderStyleAndAutoFillter(sheet1)

            // Activate the sheet
            sheet1.active(true);

            // Delete 'Sheet1'
            workbook.deleteSheet("Sheet1");

            // sheet to file.
            generateBlob(workbook);
        }).then(() => {
            setTimeout(function () {
                // location.reload()
                listExport.clear()
                    .draw();
            }, 2000);
        });

    // location.reload();
    // setTimeout(function () {
    //     location.reload()
    // }, 1000);
}


function generateBlob(workbook, type) {
    return workbook.outputAsync({type: type}).then(function (blob) {
        if (window.navigator && window.navigator.msSaveOrOpenBlob) {
            window.navigator.msSaveOrOpenBlob(blob, "Report.xlsx");
        } else {
            var url = window.URL.createObjectURL(blob);
            var a = document.createElement("a");
            document.body.appendChild(a);
            a.href = url;
            a.download = "Report.xlsx";
            a.click();
            window.URL.revokeObjectURL(url);
            document.body.removeChild(a);
        }
    })
        .catch(function (err) {
            alert(err.message || err);
            throw err;
        });
}

function convertArrayObjectToNestedArray(arrayObject) {
    return arrayObject.map(function (obj) {
        return convertObjectToArray(obj);
    });
}

function convertObjectToArray(obj) {
    return Object.keys(obj).map(function (key) {
        return obj[key];
    });
}

function setHeaderStyleAndAutoFillter(worksheet) {
    // bold header
    worksheet.range("A1:Y1").autoFilter().style("bold", true);
    // freeze panes (freeze first three column and first rows)
    worksheet.freezePanes(3, 1);
    return worksheet;
}

function setHeaderStyleAndAutoFillterTab_1(worksheet) {
    // bold header
    worksheet.range("A1:Y1").autoFilter().style("bold", true);
    // freeze panes (freeze first three column and first rows)
    const cell = worksheet[0];
    // cell._value = parseInt(cell._value);
    worksheet.freezePanes(3, 1);
    return worksheet;
}

function logOut() {
    window.location.href = "./login.html";
    localStorage.clear();
}



