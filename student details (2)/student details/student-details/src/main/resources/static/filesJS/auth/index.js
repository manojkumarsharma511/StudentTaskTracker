 function navigateToPage() {
        const userEmail = document.getElementById("email").value.trim();
        const password = document.getElementById("pass_word").value;


        const adminEmail = "@";
        const adminPassword = "@";

        if (userEmail === adminEmail && password === adminPassword) {


        window.location.href = "http://127.0.0.1:5501/Admin/adminDashboard.html";

        } else {

        window.location.href = "http://127.0.0.1:5501/student/studentDashboard.html";

        }

        //console.log("Logged in USer",localStorage.getItem("user"));

}


function forgotPassword() {
        const email = document.getElementById("email").value.trim();
        const user = JSON.parse(localStorage.getItem("user"));

        if (!email) {
        alert("Please enter your email first.");
        return;
        }

        if (email === "@" || (user && user.email === email)) {
        alert("A password reset link has been sent to your email address.");
        } else {
        alert("No account found with this email.");
        }
}