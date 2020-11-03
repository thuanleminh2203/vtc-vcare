
var email;

$("#email").change(function () {
    email = $(this).val();
});


function resetPassword() {
    let req = ({
        email
    });

    resetAction(req);

}


const resetAction = async (req) => {
    try {
        const response = await fetch('http://10.33.60.12:8088/api/v1/ctv-vcare/email/reset-password', {
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