document.getElementById('eventForm').addEventListener('submit', function(event) {
    event.preventDefault(); // Prevent default form submission

    const title = document.getElementById('title').value;
    const location = document.getElementById('location').value;
    const coordinator = document.getElementById('coordinator').value;
    const date = document.getElementById('date').value;
    const time = document.getElementById('time').value;
    const email = document.getElementById('email').value;
    const phone = document.getElementById('phone').value;
    const description = document.getElementById('description').value;

    // Store data in local storage
    localStorage.setItem("eventTitle", title);
    localStorage.setItem("eventLocation", location);
    localStorage.setItem("eventCoordinator", coordinator);
    localStorage.setItem("eventDate", date);
    localStorage.setItem("eventTime", time);
    localStorage.setItem("eventEmail", email);
    localStorage.setItem("eventPhone", phone);
    localStorage.setItem("eventDescription", description);

    // Redirect to the results page
    window.open("results.html", "_self");
});
