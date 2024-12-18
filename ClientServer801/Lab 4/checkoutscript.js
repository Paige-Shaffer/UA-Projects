
//Create data based on local storage
const orderItems = JSON.parse(localStorage.getItem('orderItems')) || [];
const orderBody = document.getElementById('orderBody');
let total = 0;

orderItems.forEach(item => {
    const row = document.createElement('tr');
    row.innerHTML = `<td>${item.name}</td><td>${item.price.toFixed(2)}</td><td>${item.quantity}</td>`;
    orderBody.appendChild(row);
    total += item.price * item.quantity;
});

document.getElementById('orderTotal').textContent = `$${total.toFixed(2)}`;

const now = new Date();
document.getElementById('dateTime').textContent = now.toLocaleString();

//Returns to previous page 
function goBackToOrder() {
    window.location.href = "home.html"; 
}