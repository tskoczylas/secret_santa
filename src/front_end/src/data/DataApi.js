export default function callPostApi(event) {
    const requestOptions = {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify(event),
    };

    fetch("/api/user/", requestOptions)
        .then((response) => response.json())
        .then((data) => {
            if (data.userid === 0) return true;
            else return true;
        })

        .catch((err) => {
            return false;
        });
}
