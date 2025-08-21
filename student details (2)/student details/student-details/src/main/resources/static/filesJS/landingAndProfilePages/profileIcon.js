const menuToggle = document.querySelector(".menu-toggle");
    const menuDropdown = document.getElementById("menuDropdown");

    // Toggle menu
    menuToggle.addEventListener("click", (e) => {
      e.stopPropagation(); // prevent closing instantly
      menuDropdown.style.display = menuDropdown.style.display === "block" ? "none" : "block";
    });

    // Hide dropdown if clicked outside
    window.addEventListener("click", () => {
      menuDropdown.style.display = "none";
    });

    // Log out functionality
    document.getElementById("logoutBtn").addEventListener("click", () => {
      localStorage.removeItem("user");
      window.location.href = "loginSignup/login.html";
    });