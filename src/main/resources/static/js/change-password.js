var newPassword;
var confirmPassword;

$("#newPassword").change(function () {
    if ($(this).val() === "") {
        document.getElementById("aEmpty").style.display = "inline";
    }
    newPassword = $(this).val();
});

$("#confirmPassword").change(function () {
    if ($(this).val() === "") {
        document.getElementById("bEmpty").style.display = "inline";
    }
    confirmPassword = $(this).val();
});

async function changePassword() {
    const urlParams = new URLSearchParams(window.location.search);
    const token = urlParams.get('token');
    let req = ({
        newPassword,
        token
    });
    await resetAction(req)
}

const resetAction = async (req) => {
    let val = validate();
    if (val !== undefined) {
        document.getElementsByClassName('modal-body')[0].innerText = val;
        $("#myModal").modal();
        return false;
    }
    document.getElementsByClassName('modal-body')[0].innerText = "Đổi mật khẩu thành công !";
    try {
        const response = await fetch('http://10.33.60.12/api/v1/ctv-vcare/email/change-password', {
            method: 'POST',
            body: JSON.stringify(req),
            headers: {
                'Content-Type': 'application/json'
            },
            dataType: "json"
        });
        const myJson = await response.json();
        switch (myJson.errorCode) {
            case 0:
                $("#myModal").modal();
                break;
            case 1:
                document.getElementsByClassName('modal-body')[0].innerText = myJson.errorMessage;
                $("#myModal").modal();
                break;
        }
    } catch (error) {
        console.error(error);
    }
}

function validate() {
    if (newPassword == null || newPassword == undefined || newPassword == "") {
        return "Mật khẩu không được để trống !";
    }

    if (confirmPassword == null || confirmPassword == undefined || confirmPassword == "") {
        return "Bạn chưa xác nhận mật khẩu !";
    } else {
        if (newPassword !== confirmPassword) {
            return "Xác nhận mật khẩu chưa đúng !";
        }
    }
}

function hiddenNewPasswordEmpty() {
    // username = this.username;
    if (newPassword !== "") {
        document.getElementById("aEmpty").style.display = "none";
    }
}

function hiddenConfirmPasswordEmpty() {
    if (confirmPassword !== "") {
        document.getElementById("bEmpty").style.display = "none";
    }
}
