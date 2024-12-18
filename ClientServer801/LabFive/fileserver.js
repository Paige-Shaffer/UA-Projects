const http = require('http');
const url = require('url');
const fs = require('fs');
const path = require('path');
const querystring = require('querystring'); 

function getContentType(filePath) {
    const extname = path.extname(filePath);
    switch (extname) {
        case '.html':
            return 'text/html';
        case '.css':
            return 'text/css';
        case '.js':
            return 'application/javascript';
        case '.jpg':
        case '.jpeg':
            return 'image/jpeg';
    }
}

const server = http.createServer(function (request, response) {
    let requestedFile = url.parse(request.url).pathname;

    if (requestedFile === '/') {
        requestedFile = '/index.html';
    }

    let filename = path.join(process.cwd(), 'public', requestedFile);

    if (request.method === 'POST') {
        let body = '';

        request.on('data', chunk => {
            body += chunk;
        });

        request.on('end', () => {
            const parsedData = querystring.parse(body); 
            const echoMessage = `
                <h1>Echoing back your POST data:</h1>
                <pre>${JSON.stringify(parsedData, null, 2)}</pre>
            `;

            response.writeHead(200, { "Content-Type": "text/html" });
            response.end(echoMessage);
        });
    } else {
        fs.access(filename, fs.constants.F_OK, function (err) {
            if (err) {
                response.writeHead(404, { "Content-Type": "text/html" });
                response.write("<h1>404 Error</h1>\n");
                response.write("This file does not exist on this server\n");
                response.end();
                return;
            }

            fs.readFile(filename, "binary", function (err, file) {
                if (err) {
                    response.writeHead(500, { "Content-Type": "text/html" });
                    response.write("<h1>500 Error</h1>\n");
                    response.write(err + "\n");
                    response.end();
                    return;
                }

                const contentType = getContentType(filename);
                response.writeHead(200, { "Content-Type": contentType });
                response.write(file, "binary");
                response.end();
            });
        });
    }
});

server.listen(8080, () => {
    console.log('Server running at http://localhost:8080/');
});
