var email;

$("#email").change(function () {
    if ($(this).val() === "") {
        document.getElementById("emailEmpty").style.display = "inline";
    }
    email = $(this).val();
});


function resetPassword() {
    let req = ({
        email
    });
    resetAction(req);
}

const resetAction = async (req) => {
    let val = validate();
    if (val !== undefined) {
        document.getElementsByClassName('modal-body')[0].innerText = val;
        $("#myModal").modal();
        return false;
    }
    document.getElementsByClassName('modal-body')[0].innerText = "Bạn vui lòng check email để đổi mật khẩu !";
    try {
        const response = await fetch('http://localhost:8088/api/v1/ctv-vcare/email/reset-password', {
            method: 'POST',
            body: JSON.stringify(req),
            headers: {
                'Content-Type': 'application/json'
            },
            dataType: "json"
        });
        const myJson = await response.json();
        switch (myJson.errorCode) {
            case 1:
                document.getElementsByClassName('modal-body')[0].innerText = myJson.errorMessage;
                break;
            default:
                $("#myModal").modal();
                break;
        }
    } catch (error) {
        console.error(error);
    }
}


function validate() {
    if (email == null || email == undefined || email == "") {
        document.getElementById("emailEmpty").style.display = "inline";
        return "Email không được để trống !";
    }

    if (!email.match(EMAIL_PATTERN)) {
        return "Sai định dạng email !";
    }
}

function hiddenEmailEmpty() {
    if (email !== "") {
        document.getElementById("emailEmpty").style.display = "none";
    }
}