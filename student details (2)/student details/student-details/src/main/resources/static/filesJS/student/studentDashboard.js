 function taskDetails( serialno,name, deadline, taskName, status){
        const url = `taskDetail.html?serialno=${encodeURIComponent(serialno)}&name=${encodeURIComponent(name)}&deadline=${encodeURIComponent(deadline)}&taskName=${encodeURIComponent(taskName)}&status=${encodeURIComponent(status)}`;
        window.location.href = url;
    }

// Section switcher
function showSection(sectionId) {
  document.querySelectorAll("section").forEach(section => {
    section.style.display = "none";
  });
  const active = document.getElementById(sectionId);
  if (active) active.style.display = "block";
}

// Apply 20-char truncation to static labels
function truncateStaticTaskLabels() {
  const taskLabels = document.querySelectorAll(".task-label");
  taskLabels.forEach(label => {
    const fullText = label.textContent.trim();
    if (fullText.length > 20) {
      label.setAttribute("title", fullText);
      label.textContent = fullText.slice(0, 20) + "...";
    }
  });
}

/* Logout handler
document.getElementById("logoutBtn").addEventListener("click", () => {
  if (confirm("Are you sure you want to log out?")) {
    window.location.href = "login.html";
  }
});*/

// Kebab menu dropdown toggle
const kebabBtn = document.getElementById("kebabMenuBtn");
const dropdown = document.getElementById("kebabDropdown");

kebabBtn.addEventListener("click", e => {
  e.preventDefault();
  dropdown.classList.toggle("show");
});

document.addEventListener("click", e => {
  if (!kebabBtn.contains(e.target) && !dropdown.contains(e.target)) {
    dropdown.classList.remove("show");
  }
});

// On load
window.onload = () => {
  showSection("toDoTask");
  truncateStaticTaskLabels();
};


//logout

document.getElementById("logoutBtn").addEventListener("click", () => {
  if (confirm("Are you sure you want to log out?")) {

    localStorage.clear();

    window.location.href = "http://127.0.0.1:5501/loginSignup/index.html";
  }
});
