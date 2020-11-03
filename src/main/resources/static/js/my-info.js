function getMyInfo() {
    getMyInfoAction();
}

const getMyInfoAction = async (req) => {
    const myHeaders = new Headers({
        'Content-Type': 'application/json',
        'Authorization': 'Bearer ' + localStorage.getItem('item')
    });
    try {
        const response = await fetch('http://10.33.60.12:8088/api/v1/ctv-vcare/customer/my-info', {
            method: 'GET',
            headers: myHeaders,
        });
        const object = await response.json();
        const data = object.data;
        document.getElementById("name").value = data.customerName;
        document.getElementById("phone").value = data.phoneNumber;
        document.getElementById("address").value = data.address;
        document.getElementById("card").value = data.identifyCard;
        document.getElementById("inviteCode").value = data.introductionCode;
        document.getElementById("email").value = data.email;
        document.getElementById("bankName").value = data.bankName;
        document.getElementById("bankNumber").value = data.bankAccountNumber;
        document.getElementById("bankOwner").value = data.bankAccountName;
    } catch (error) {
        console.error(error);
    }
}