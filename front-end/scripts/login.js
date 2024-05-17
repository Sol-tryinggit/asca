document.addEventListener("DOMContentLoaded", function() {
    // Function to handle form submission
    function handleSubmit(event) {
        event.preventDefault(); // Prevent default form submission

        // Get form data
        var username = document.getElementById("username").value;
        var password = document.getElementById("password").value;

        // Create an object with the login credentials
        var loginData = {
            username: username,
            password: password
        };

        // Send POST request to the server
        fetch("http://localhost:9001/auth/login", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(loginData)
        })
        .then(response => {
            if (!response.ok) {
                throw new Error("Failed to login");
            }
            return response.json();
        })
        .then(data => {
            // Handle successful login response
            console.log("Login response:", data);
            if (data.status === "OK") {
                console.log("Login successful:", data.message);
                // You can redirect the user or perform any other action here
                // For example, if there's a token in the response, you can store it in localStorage
                if (data.code) {
                    localStorage.setItem("token", data.code);
                    // Make another call to the token URL
                    fetch("http://localhost:9001/auth/token", {
                        method: "POST",
                        headers: {
                            "Content-Type": "application/json"
                        },
                        body: JSON.stringify({ code: data.code })
                    })
                    .then(tokenResponse => {
                        if (!tokenResponse.ok) {
                            throw new Error("Failed to get token");
                        }
                        return tokenResponse.json();
                    })
                    .then(tokenData => {
                        // Handle successful token response
                        console.log("Token response:", tokenData);
                        // After receiving the JWT token, make a validation call
                        fetch("http://localhost:9001/auth/validate?token=" + tokenData.code)
                        .then(validationResponse => {
                            if (!validationResponse.ok) {
                                throw new Error("Failed to validate token");
                            }
                            return validationResponse.json();
                        })
                        .then(validationData => {
                            // Handle successful validation response
                            console.log("Validation response:", validationData);
                            // Redirect to another page upon successful validation
                            window.location.href = "../html/welcome.html";
                        })
                        .catch(validationError => {
                            // Handle errors in validation
                            console.error("Error validating token:", validationError.message);
                        });
                    })
                    .catch(tokenError => {
                        // Handle errors in getting token
                        console.error("Error getting token:", tokenError.message);
                    });
                }
                // Redirect to the dashboard or any other page
                // window.location.href = "dashboard.html";
            } else {
                console.error("Login failed:", data.message);
                // Display error message to the user
                // For example, show an alert or update a message div
            }
        })
        .catch(error => {
            // Handle errors
            console.error("Error:", error.message);
        });
    }

    // Attach form submission handler to the form
    var form = document.getElementById("loginForm");
    form.addEventListener("submit", handleSubmit);
});
