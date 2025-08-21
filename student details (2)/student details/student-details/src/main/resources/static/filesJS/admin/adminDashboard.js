 document.addEventListener("DOMContentLoaded", function () {
   const assignButton = document.getElementById("assign_button");
   const studentInput = document.getElementById("student_name_input");
   const taskInput = document.getElementById("task_name_input");
   const deadlineInput = document.getElementById("deadline_input");
   const progressSelect = document.getElementById("progress_input");
   const taskTableBody = document.querySelector(".tableClass tbody");

   const today = new Date().toISOString().split("T")[0];
   deadlineInput.setAttribute("min", today);

   function getStoredTasks() {
     return JSON.parse(localStorage.getItem("tasks")) || [];
   }

   function saveTasks(tasks) {
     localStorage.setItem("tasks", JSON.stringify(tasks));
   }

   function addTaskToTable(task) {
     const row = document.createElement("tr");
     const shortTask = task.task.length > 20 ? task.task.slice(0, 20) + "..." : task.task;

     row.innerHTML = `
       <td>${task.serialNo}</td>
       <td>${task.student}</td>
       <td title="${task.task}" onclick="taskDetails(${task.serialNo}, '${task.student}', '${task.deadline}', '${task.task}', '${task.progress}')">${shortTask}</td>
       <td>${task.progress}</td>
       <td>${task.deadline}</td>
       <td>
         <button class="edit_btn btn-action btn-edit" onclick="startEditTask(this)" data-bs-toggle="modal" data-bs-target="#editTask_modal">✏️</button>
         <button class="del_btn btn-action btn-delete" onclick="deleteTask(this)">🗑️</button>
       </td>
     `;

     const deadlineDate = new Date(task.deadline);
     const now = new Date();
     if (now > deadlineDate) {
       row.classList.add("expired-task");
       row.querySelector(".edit_btn").disabled = true;
       row.querySelector(".del_btn").disabled = true;
     }

     taskTableBody.appendChild(row);
   }

   function loadTasksFromStorage() {
     taskTableBody.innerHTML = "";
     const tasks = getStoredTasks();
     tasks.forEach(addTaskToTable);
   }

   assignButton.addEventListener("click", function (event) {
     event.preventDefault();

     const student = studentInput.value.trim();
     const task = taskInput.value.trim();
     const deadline = deadlineInput.value;
     const progress = progressSelect.value;

     if (!student || !task || !deadline) {
       alert("All fields are required!");
       return;
     }

     if (/[^a-zA-Z0-9 ]/.test(task)) {
       alert("Task Name should not contain special characters!");
       return;
     }

     const tasks = getStoredTasks();
     const serialNo = tasks.length + 1;
     const formattedDeadline = deadline.split("-").reverse().join("-"); // to dd-mm-yyyy

     const newTask = { serialNo, student, task, deadline: formattedDeadline, progress };
     tasks.push(newTask);
     saveTasks(tasks);
     addTaskToTable(newTask);

     // Clear form
     studentInput.value = "";
     taskInput.value = "";
     deadlineInput.value = "";
     progressSelect.selectedIndex = 0;

     // Close modal
     const assignModal = bootstrap.Modal.getInstance(document.getElementById("assignTask_modal"));
     assignModal.hide();
   });

   loadTasksFromStorage();
 });


 function taskDetails(serialno, name, deadline, taskName, status) {
   const taskData = {
     serialno,
     name,
     taskName,
     deadline,
     status,
   };

   // Save the task in localStorage
   localStorage.setItem("selectedTask", JSON.stringify(taskData));

   // Redirect to the detail page
   window.location.href = "taskDetail.html";
 }

 document.getElementById("logoutBtn").addEventListener("click", function () {
   // Clear localStorage/sessionStorage (based on how you handle login)
   //localStorage.clear();
   //sessionStorage.clear();

   // Redirect to login page
   window.location.href = "http://127.0.0.1:5501/loginSignup/index.html"; // change URL if needed
 });

 function deleteTask(button) {
   const row = button.closest("tr");
   const serialNo = parseInt(row.children[0].textContent);

   const confirmed = confirm("Are you sure you want to delete this task?");
   if (confirmed) {
     // Remove from localStorage
     let tasks = JSON.parse(localStorage.getItem("tasks")) || [];
     tasks = tasks.filter(task => task.serialNo !== serialNo);

     // Reassign serial numbers
     tasks.forEach((task, index) => task.serialNo = index + 1);

     // Save and reload
     localStorage.setItem("tasks", JSON.stringify(tasks));
     document.querySelector(".tableClass tbody").innerHTML = "";
     tasks.forEach(addTaskToTable);
   }
 }

 let currentEditSerialNo = null;

 function startEditTask(button) {
   const row = button.closest("tr");
   currentEditSerialNo = parseInt(row.children[0].textContent);

   const student = row.children[1].textContent;
   const task = row.children[2].getAttribute("title");
   const progress = row.children[3].textContent;
   const deadline = row.children[4].textContent.split("-").reverse().join("-"); // dd-mm-yyyy to yyyy-mm-dd

   // Fill modal inputs
   document.getElementById("edit_student_name_input").value = student;
   document.getElementById("taskNameInput").value = task;
   document.getElementById("edit_progress_input").value = progress;
   document.getElementById("edit_deadline_input").value = deadline;
 }

 document.getElementById("save_edit_button").addEventListener("click", function () {
   const student = document.getElementById("edit_student_name_input").value.trim();
   const task = document.getElementById("edit_task_name_input").value.trim();
   const deadline = document.getElementById("edit_deadline_input").value;
   const progress = document.getElementById("edit_progress_input").value;

   if (!student || !task || !deadline) {
     alert("All fields are required!");
     return;
   }

   if (/[^a-zA-Z0-9 ]/.test(task)) {
     alert("Task Name should not contain special characters!");
     return;
   }

   const tasks = JSON.parse(localStorage.getItem("tasks")) || [];

   const updatedTasks = tasks.map(taskObj => {
     if (parseInt(taskObj.serialNo) === parseInt(currentEditSerialNo)) {
       return {
         ...taskObj,
         student,
         task,
         deadline: deadline.split("-").reverse().join("-"),
         progress
       };
     }
     return taskObj;
   });

   localStorage.setItem("tasks", JSON.stringify(updatedTasks));
   document.querySelector(".tableClass tbody").innerHTML = "";
   updatedTasks.forEach(addTaskToTable);

   // Close modal
   const editModal = bootstrap.Modal.getInstance(document.getElementById("editTask_modal"));
   editModal.hide();
 });

 const kebabBtn = document.getElementById("kebabMenuBtn");
    const dropdown = document.getElementById("kebabDropdown");

    kebabBtn.addEventListener("click", function (e) {
        e.preventDefault();
        dropdown.classList.toggle("show");
    });

    document.addEventListener("click", function (e) {
        if (!kebabBtn.contains(e.target) && !dropdown.contains(e.target)) {
        dropdown.classList.remove("show");
        }
    });