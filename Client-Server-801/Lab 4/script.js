let orderItems = [];

//Adds items to order
function addToOrder(itemName, itemPrice) {
    const existingItem = orderItems.find(item => item.name === itemName);
    if (existingItem) {
        existingItem.quantity++;
    } else {
        orderItems.push({ name: itemName, price: itemPrice, quantity: 1 });
    }
    updateOrderDisplay();
}

//Calculates running total of orders 
function updateOrderDisplay() {
    const orderBody = document.getElementById('orderBody');
    orderBody.innerHTML = ''; 
    let total = 0;

    orderItems.forEach(item => {
        const row = document.createElement('tr');
        row.innerHTML = `<td>${item.name}</td><td>${item.price.toFixed(2)}</td><td>${item.quantity}</td>`;
        orderBody.appendChild(row);
        total += item.price * item.quantity;
    });

    document.getElementById('orderTotal').textContent = `$${total.toFixed(2)}`;
}
//Runs vaildation for empty orders
function placeOrder() {
    if (orderItems.length > 0) {
        localStorage.setItem('orderItems', JSON.stringify(orderItems)); 
        window.location.href = "checkout.html"; 
    } else {
        alert("Please add at least one item to your order.");
    }
}

//Clears order data for users
function clearOrder() {
    localStorage.removeItem('orderItems'); 
    orderItems = []; 
    updateOrderDisplay();
}
//Saves local storage value of order
window.onload = function() {
    const savedOrder = localStorage.getItem('orderItems');
    if (savedOrder) {
        orderItems = JSON.parse(savedOrder);
        updateOrderDisplay();
    }
};
