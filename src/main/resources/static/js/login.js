var username;
var password;

$("#email").change(function () {
    if ($(this).val() === "") {
        document.getElementById("emailEmpty").style.display = "inline";
    }
    username = $(this).val();
});

$("#password").change(function () {
    if ($(this).val() === "") {
        document.getElementById("passwordEmpty").style.display = "inline";
    }
    password = $(this).val();
});

function loginSubmit() {
    let req = ({
        username,
        password
    })
    if (validate()) {
        return false;
    }
    loginAction(req);
}

const loginAction = async (req) => {
    try {
        const response = await fetch('http://10.33.60.12:8088/authenticate', {
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
                localStorage.setItem("item", myJson.data.jwtToken);
                localStorage.setItem("role", (myJson.data.role.length == 0) ? "" : myJson.data.role[0].authority);
                window.location.href = "C:/Users/sonvsh/Documents/Hara-Affiliates/public/collaborators.html";
                break;
            case 1:
                $("#myModal").modal();
                break;
        }
    } catch (error) {
        console.error(error);
    }
}

function hiddenEmailEmpty() {
    // username = this.username;
    if (username !== "") {
        document.getElementById("emailEmpty").style.display = "none";
    }
}

function hiddenPasswordEmpty() {
    if (password !== "") {
        document.getElementById("passwordEmpty").style.display = "none";
    }
}

function validate() {
    if (username == null || username == undefined || username == "") {
        document.getElementById("emailEmpty").style.display = "inline";
        //document.getElementById("emailEmpty").style.color = "red";
        return true;
    }

    if (password == null || password == undefined || password == "") {
        document.getElementById("passwordEmpty").style.display = "inline";
        //document.getElementById("passwordEmpty").style.color = "red";
        return true;
    }
    return false;
}