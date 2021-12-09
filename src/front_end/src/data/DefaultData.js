export default  function santaUserDefault() 
{ return(

    

 {
    userid: 0,
    firstName: null,
    lastName: null,
    phoneNumber: "Ładuje numer telefonu...",
    token: 0,
    admin: {
      adminId: 1,
      firstName: "Tomasz",
      secondName: "Skoczylas",
      login: "alex",
      password: "1234",
      usersAreCompleted: false,
      percentageCompleteUsers: 10,
      firstChosenPrice: 20,
      secondChosenPrice: 50,
      thirdChosenPrice: 60,
      correctAdmin: false,
    },
    giftDesc: null,
    complete: false,
    userCreate: true,
    userComplete: false,
    firstMessageSendCorrectly: false,
    secondMessageSendCorrectly: false,
 })}

 export  const dataTranslate = (data) =>{
   return({
  token:  data.get("token"),
  phoneNumber: data.get("phoneNumber"),
  firstName: data.get("firstName"),
  lastName: data.get("lastName"),
  giftDesc: data.get("giftDesc"),
  chosenPrice: data.get("gift"),
})}

export const isComplete = (data) =>{ 
return(
  data.firstName === "" ||
  data.lastName === "" ||
  data.giftDesc ==="" )}