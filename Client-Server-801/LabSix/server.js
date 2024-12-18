const express = require('express');
const fs = require('fs');
const path = require('path');
const _ = require('lodash');

const app = express();
const PORT = 8080;

// Views setup
app.set('view engine', 'ejs');
app.set('views', path.join(__dirname, 'views'));

// Static files 
app.use(express.static(path.join(__dirname, 'public')));

// Parse form data
app.use(express.urlencoded({ extended: true }));
app.use(express.json());

// Route for new customers
app.get('/customers/new', (req, res) => {
    res.render('new_customer');  
});

// Display all customers
app.get('/customers', (req, res) => {
    fs.readFile(path.join(__dirname, 'customers.json'), 'utf-8', (err, data) => {
        if (err) {
            return res.status(500).send('Error reading customers data');
        }
        const customers = JSON.parse(data).customers;
        res.render('customers', { customers }); 
    });
});

// Display a specific customer by ID
app.get('/customers/:id', (req, res) => {
    const customerId = parseInt(req.params.id);  

    fs.readFile(path.join(__dirname, 'customers.json'), 'utf-8', (err, data) => {
        if (err) {
            return res.status(500).send('Error reading customers data');
        }

        const customers = JSON.parse(data).customers;
        
        // Lodash
        const customer = _.filter(customers, { id: customerId })[0];
        
        if (!customer) {
            return res.status(404).send('Customer not found');
        }

        // Render customer detail page
        res.render('customer_detail', { customer });
    });
});

// Route to handle the form submission for new customer
app.post('/customers/new', (req, res) => {
    const { first_name, last_name, occupation } = req.body;

    if (!first_name || !last_name || !occupation) {
        return res.status(400).send('All fields are required!');
    }

    // Read and write to customers.json
    fs.readFile(path.join(__dirname, 'customers.json'), 'utf-8', (err, data) => {
        if (err) {
            return res.status(500).send('Error reading customers data');
        }

        const customersData = JSON.parse(data);
        const customers = customersData.customers;

        // Generate new ID
        const newId = customers.length > 0 
            ? Math.max(...customers.map(c => c.id)) + 1  
            : 1;  
            
        // Create new customer 
        const newCustomer = {
            id: newId,
            first_name,
            last_name,
            occupation
        };

        // Add the new customer 
        customers.push(newCustomer);

        // Write to customers.json
        fs.writeFile(path.join(__dirname, 'customers.json'), JSON.stringify(customersData, null, 2), 'utf-8', (err) => {
            if (err) {
                return res.status(500).send('Error saving new customer data');
            }
            // Redirect
            res.redirect('/customers');
        });
    });
});

// Start the server
app.listen(8080, () => {
    console.log(`Server is running at http://localhost:8080`);
});
