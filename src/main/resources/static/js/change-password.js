var newPassword;
var confirmPassword;

$("#newPassword").change(function () {
    newPassword = $(this).val();
});

$("#confirmPassword").change(function () {
    confirmPassword = $(this).val();
});

function changePassword() {
    var url_string = window.location.href;
    var url = new URL(url_string);
    var token = url.searchParams.get("token");
    let req = ({
        newPassword,
        token
    });

}

const resetAction = async (req) => {
    try {
        const response = await fetch('http://10.33.60.12:8088/api/v1/ctv-vcare/email/change-password', {
            method: 'POST',
            body: JSON.stringify(req),
            headers: {
                'Content-Type': 'application/json'
            },
            dataType: "json"
        });
        const myJson = await response.json();
    } catch (error) {
        console.error(error);
    }
}