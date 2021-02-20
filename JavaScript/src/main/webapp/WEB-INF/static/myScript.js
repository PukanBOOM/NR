//const heading = document.querySelector('#username')
// console.log(heading)
const requestURL = 'http://localhost:8080/user/user'
function sendRequest(method,url,body = null) {
    const headers = {
        'Content-Type':'application/json'
    }
    return fetch(url, {
        method: method,
        headers: headers
    }).then(response =>{
        return response.json()
    })
    // return new Promise((resolve) => {
    //     const xhr = new XMLHttpRequest()
    //     xhr.open(method, requestURL)
    //     xhr.responseType = 'json'
    //     xhr.setRequestHeader('Content-Type','application/json')
    //     xhr.onload = () => {
    //         resolve(xhr.response)
    //     }
    //     xhr.send(JSON.stringify(body))
    //})
}
const body = {
    name:'Andrew',
    age: '24'
}
// sendRequest('POST',requestURL,body)
//     .then(data => console.log(data))
//     .catch(err => console.log(err))
sendRequest('GET',requestURL)
    .then(data => console.log(data))
    .catch(err => console.log(err))
// var request = new XMLHttpRequest()
// request.open('GET',requestURL)
// request.responseType = 'json';
// request.send();
// request.onload = function (){
//     var user = request.response
//     populate(user)
// }
// function populate(jsonObj){
//     heading.textContent = jsonObj['username'];
// }
// let user = fetch('http://localhost:8080/user/user')
// heading.textContent = user.text()
