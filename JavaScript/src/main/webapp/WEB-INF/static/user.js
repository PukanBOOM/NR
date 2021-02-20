const requestURL = 'user'
function sendRequest(method,url,body = null) {
    const headers = {
        'Content-Type': 'application/json'
    }
    return fetch(url, {
        method: method,
        headers: headers
    }).then(response => response.json())
        .then(response => fillTable(response))
}
sendRequest('GET',requestURL)
function fillTable(data){
    document.querySelector('#id').textContent =data.id
    document.querySelector('#username').textContent =data.username
    document.querySelector('#email').textContent =data.email
    document.querySelector('#password').textContent =data.password
}

