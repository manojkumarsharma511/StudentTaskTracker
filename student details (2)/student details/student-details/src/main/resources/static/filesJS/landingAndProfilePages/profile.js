// Load user data from localStorage
    const user = JSON.parse(localStorage.getItem('user'));
    if(window.location.href == "http://127.0.0.1:5501/Admin/adminDashboard.html" ){
        document.getElementById('profile-name').textContent= 'Chandana Kumawat';
        document.getElementById('profile-email').textContent= 'chandanakumawat2147@gmail.com';
        document.getElementById('profile-contact').textContent= '9876540000';
        document.getElementById('profile-dob').textContent= '2004-03-09';
    }
    else if (user) {
        document.getElementById('profile-name').textContent = user.name;
        document.getElementById('profile-email').textContent = user.email;
        document.getElementById('profile-contact').textContent = user.contact;
        document.getElementById('profile-dob').textContent = user.dob;

    } else {
        alert("No user found! Redirecting to sign up.");
        window.location.href = "signup.html";
    }

    // Change password form handler
    document.getElementById('password-form').addEventListener('submit', function (e) {
        e.preventDefault();
        const newPassword = document.getElementById('newPassword').value;
        const confirmPassword = document.getElementById('confirmPassword').value;
        const message = document.getElementById('passwordMessage');
        const error = document.getElementById('passwordError');

        if (newPassword === confirmPassword) {
            user.password = newPassword;
            localStorage.setItem('user', JSON.stringify(user));
            message.style.display = 'block';
            error.style.display = 'none';
            this.reset(); // Clear inputs
        } else {
            message.style.display = 'none';
            error.style.display = 'block';
        }
    });