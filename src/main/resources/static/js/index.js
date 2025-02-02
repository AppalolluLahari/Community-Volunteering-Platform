//document.querySelectorAll('.toggle-edit').forEach((btn) => {
//    btn.addEventListener('click', (e) => {
//        const input = e.target.previousElementSibling;
//        input.readOnly = !input.readOnly;
//        e.target.textContent = input.readOnly ? 'Edit' : 'save';
//    });
//});
document.addEventListener("DOMContentLoaded", function () {
    document.querySelectorAll(".toggle-edit").forEach(button => {
        button.addEventListener("click", function () {
            let inputField = this.previousElementSibling; // Target input field
            inputField.readOnly = !inputField.readOnly; // Toggle readonly

            if (!inputField.readOnly) {
                this.textContent = "Save";
                this.classList.add("btn-success-outline");
            } else {
                this.textContent = "Edit";
                this.classList.remove("btn-success-outline");

                // Collect all user details from the form
                let updatedUser = {
                    userId: document.getElementById("userId").value,
                    name: document.getElementById("userName").value,
                    phone: document.getElementById("userPhone").value,
                    location: document.getElementById("userLocation").value,
                    ratingScore: 0,  // Assuming rating remains unchanged
                    volunteerSkills: [] // Send an empty array for now
                };

                // Send updated data to backend
                fetch("/auth/updateVolunteer", {
                    method: "POST",
                    headers: {
                        "Content-Type": "application/json"
                    },
                    body: JSON.stringify(updatedUser)
                })
                .then(response => {
                    if (response.ok) {
                        alert("Profile updated successfully!");
                    } else {
                        alert("Updation failed.");
                    }
                })
                .catch(error => console.error("Error:", error));
            }
        });
    });
});





