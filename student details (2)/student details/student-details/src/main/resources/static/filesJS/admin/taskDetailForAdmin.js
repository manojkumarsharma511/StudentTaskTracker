 // Load task from localStorage
    const task = JSON.parse(localStorage.getItem('selectedTask'));

    if (task) {
      document.getElementById("taskSerialNo").textContent = task.serialno;
      document.getElementById("taskStuName").textContent = task.name;
      document.getElementById("taskName").textContent = task.taskName;
      document.getElementById("taskDeadline").textContent = task.deadline;
      document.getElementById("taskStatus").textContent = task.status;
    } else {
      document.body.innerHTML = '<div class="container my-5 text-center"><h4 class="text-danger">No task selected.</h4><a href="adminDashboard.html" class="btn btn-primary mt-3">Back to Dashboard</a></div>';
    }