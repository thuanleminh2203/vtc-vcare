var introductionCode;
var phoneNumber;
var address;
var identifyCard;
var email;
var password;
var bankName;
var bankAccountName;
var bankAccountNumber;
var customerName;
var introduceCustomerCode;
var verifyPassword;

$("#name").change(function () {
    customerName = $(this).val();
});

$("#phone").change(function () {
    phoneNumber = $(this).val();

});

$("#address").change(function () {
    address = $(this).val();
});

$("#card").change(function () {
    identifyCard = $(this).val();
});

$("#inviteCode").change(function () {
    introduceCustomerCode = $(this).val();
});

////////////////
$("#email").change(function () {
    email = $(this).val();
});

$("#password").change(function () {
    password = $(this).val();
});


$("#bankName").change(function () {
    bankName = $(this).val();
});


$("#bankNumber").change(function () {
    bankAccountNumber = $(this).val();
});

$("#bankOwner").change(function () {
    bankAccountName = $(this).val();
});

$("#verifyPassword").change(function () {
    verifyPassword = $(this).val();
});


function registerSubmit() {
    let req = ({
        introductionCode,
        phoneNumber,
        address,
        identifyCard,
        email,
        password,
        bankName,
        bankAccountName,
        bankAccountNumber,
        customerName,
        introduceCustomerCode
    });
    registerAction(req);
}

const registerAction = async (req) => {
    let val = validate();
    if (val !== undefined) {
        document.getElementsByClassName('modal-body')[0].innerText = val;
        $("#myModal").modal();
        return false;
    }
    document.getElementsByClassName('modal-body')[0].innerText = "Đăng ký thành công !";
    try {
        const response = await fetch('http://10.33.60.12:8088/api/v1/ctv-vcare/customer/register', {
            method: 'POST',
            body: JSON.stringify(req),
            headers: {
                'Content-Type': 'application/json'
            },
            dataType: "json"
        });
        const myJson = await response.json();

        $("#myModal").modal();
        switch (myJson.errorCode) {
            case 1:
                document.getElementsByClassName('modal-body')[0].innerText = myJson.errorMessage;
                break;
            default:
                break;
        }
    } catch (error) {
        console.error(error);
    }
}

function validate() {
    if (customerName == null || customerName == undefined || customerName == "") {
        return "Họ và tên không được để trống !";
    }

    if (phoneNumber == null || phoneNumber == undefined || phoneNumber == "") {
        return "Số điện thoại không được để trống !";
    }

    if (!phoneNumber.match(PHONE_PATTERN)) {
        return "Sai định dạng số điện thoại !";
    }

    if (address == null || address == undefined || address == "") {
        return "Địa chỉ không được để trống !";
    }

    if (identifyCard == null || identifyCard == undefined || identifyCard == "") {
        return "CMND không được để trống !";
    }

    if (!(identifyCard.match(CMND_NEW) || identifyCard.match(CMND_OLD))) {
        return "Sai định dạng CMND !";
    }

    if (introduceCustomerCode == null || introduceCustomerCode == undefined || introduceCustomerCode == "") {
        return "Mã giới thiệu không được để trống !";
    }


    if (email == null || email == undefined || email == "") {
        return "Email không được để trống !";
    }

    if (!email.match(EMAIL_PATTERN)) {
        return "Sai định dạng email !";
    }

    if (password == null || password == undefined || password == "") {
        return "Mật khẩu không được để trống !";
    }

    if (verifyPassword == null || verifyPassword == undefined || verifyPassword == "") {
        return "Bạn chưa xác nhận mật khẩu !";
    } else {
        if (verifyPassword !== password) {
            return "Xác nhận mật khẩu chưa đúng !";
        }
    }

    if (bankName == null || bankName == undefined || bankName == "") {
        return "Tên ngân hàng không được để trống !";
    }

    if (bankAccountNumber == null || bankAccountNumber == undefined || bankAccountNumber == "") {
        return "Số tài khoản không được để trống !";
    }

    if (bankAccountName == null || bankAccountName == undefined || bankAccountName == "") {
        return "Tên chủ tài khoản không được để trống !";
    }
}

