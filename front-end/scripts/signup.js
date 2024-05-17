document.addEventListener("DOMContentLoaded", function() {
    // Function to handle form submission
    function handleSubmit(event) {
        event.preventDefault(); // Prevent default form submission

        // Get form data
        var name = document.getElementById("name").value;
        var username = document.getElementById("username").value;
        var password = document.getElementById("password").value;
        var email = document.getElementById("email").value;
        var phone = document.getElementById("phone").value;

        // Create an object with the user's credentials
        var userData = {
            name: name,
            username: username,
            password: password,
            email: email,
            phone: phone
        };

        // Send POST request to the server
        fetch("http://localhost:9001/auth/register", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(userData)
        })
        .then(response => {
            if (!response.ok) {
                throw new Error("Failed to register user");
            }
            return response.json();
        })
        .then(data => {
            // Handle successful registration
            console.log("User registered successfully:", data);
            // You can redirect the user or perform any other action here
        })
        .catch(error => {
            // Handle errors
            console.error("Error:", error.message);
        });
    }

    // Attach form submission handler to the form
    var form = document.querySelector("form");
    form.addEventListener("submit", handleSubmit);
});
