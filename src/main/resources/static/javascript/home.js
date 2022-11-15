// cookie
const cookieArr = document.cookie.split("=")
const userId = cookieArr[1];

// DOM elements
const submitForm = document.getElementById("event-form")
const eventContainer = document.getElementById("event-container")

// modal elements
let eventBody = document.getElementById(`event-body`)
let updateEventBtn = document.getElementById('update-event-button')

const headers = {
    'Content-Type': 'application/json'
}

const baseUrl = "http://localhost:8080/api/events/"



function handleLogout() {
    let c = document.cookie.split(";");
    for(let i in c) {
        document.cookie = /^[^=]+/.exec(c[i])[0]+"=;expires=Thu, 01 Jan 1970 00:00:00 GMT"
    }
}



const handleSubmitting = async (e) => {
    e.preventDefault()
    let bodyObj = {
        body: document.getElementById("event-input").value
    }
    await addEvent(bodyObj);
    document.getElementById("event-input").value = ''
}

async function addEvent(obj) {
    const response = await fetch(`${baseUrl}user/${userId}`, {
        method: "POST",
        body: JSON.stringify(obj),
        headers: headers
    })
        .catch(err => console.error(err.message))
    if (response.status === 200) {
        return getEvents(userId);
    }
}



async function getEvents(userId) {
    await fetch(`${baseUrl}user/${userId}`, {
        method: "GET",
        headers: headers
    })
        .then(response => response.json())
        .then(data => createEventCards(data))
        .catch(err => console.error(err))
}



async function getEventById(eventId) {
    await fetch(baseUrl + eventId, {
        method: "GET",
        headers: headers
    })
        .then(res => res.json())
        .then(data => populateModal(data))
        .catch(err => console.error(err.message))
}

async function handleEventEdit(eventId) {
    let bodyObj = {
        id: eventId,
        body: eventBody.value
    }

    await fetch(baseUrl, {
        method: "PUT",
        body: JSON.stringify(bodyObj),
        headers: headers
    })
        .catch(err => console.error(err))

    return getEvents(userId);
}



async function handleDelete(eventId) {
    await fetch(baseUrl + eventId, {
        method: "DELETE",
        headers: headers
    })
        .catch(err => console.error(err))

    return getEvents(userId);
}



const createEventCards = (array) => {
    eventContainer.innerHTML = ''
    array.forEach(obj => {
        let eventCard = document.createElement("div")
        eventCard.classList.add("m-2")
        eventCard.innerHTML = `
            <div class="card d-flex" style="width: 18rem; height: 18rem;">
                <div class="card-body d-flex flex-column  justify-content-between" style="height: available">
                    <p class="card-text">${obj.body}</p>
                    <div class="d-flex justify-content-between">
                        <button class="btn btn-danger" onclick="handleDelete(${obj.id})">Delete</button>
                        <button onclick="getEventById(${obj.id})" type="button" class="btn btn-primary"
                        data-bs-toggle="modal" data-bs-target="#event-edit-modal">Edit</button>
                    </div>
                </div>
            </div>
        `
        eventContainer.append(eventCard);
    })
}



const populateModal = (obj) => {
    eventBody.innerText = ''
    eventBody.innerText = obj.body
    updateEventBtn.setAttribute('data-event-id', obj.id)
}



getEvents(userId);

submitForm.addEventListener("submit", handleSubmitting)

updateEventBtn.addEventListener("click", (e)=> {
    let eventId = e.target.getAttribute('data-event-id')
    handleEventEdit(eventId);
})