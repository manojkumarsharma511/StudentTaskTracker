function navigateToPage() {
      const name = document.getElementById('name').value.trim();
      const email = document.getElementById('email').value.trim();
      const contact = document.getElementById('contact_no').value.trim();
      const dob = document.getElementById('dob').value.trim();
      const password = document.getElementById('pass_word').value;

      if (!name || !email || !contact || !dob || !password) {
        alert("Please fill in all fields.");
        return;
      }

      // Basic email format check without regex
      if (!email.includes("@") || !email.includes(".") || email.indexOf("@") > email.lastIndexOf(".")) {
        alert("Please enter a valid email address.");
        return;
      }

      const user = {
        name,
        email,
        contact,
        dob,
        password
      };

      localStorage.setItem("user", JSON.stringify(user));
      window.location.href = "index.html"; // go to login page
    }
