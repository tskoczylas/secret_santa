import axios from "axios";

const saveUser = (email, password, firstName, lastName) => {
    axios
        .post("/api/signin/", "POST")
        .then((response) => response)
        .catch((e) => e);
};
export const itHasTokenAndUser = () => {
    return (
        localStorage.getItem("user") !== null ||
        localStorage.getItem("token") !== null
    );
};

export const login = (email, password) => {
    const params = new URLSearchParams();
    params.append("login", email);
    params.append("password", password);

    return axios
        .post("/api/login", params)
        .then((res) => {
            if (res.data.access_token) {
                localStorage.setItem("token", JSON.stringify(res.data));
                return "success";
            } else return "fail";
        })
        .catch((e) => {
            return "fail";
        });


};


export const tokenAndUserAvailable =
    localStorage.getItem("token") === null ||
    localStorage.getItem("adminName") === null;
