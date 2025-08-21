 function getParam(name){
        return new URLSearchParams(window.location.search).get(name);

    }
    document.getElementById("taskSerialNo").innerText = getParam('serialno');
    document.getElementById("taskStuName").innerText = getParam('name');
    document.getElementById("taskName").innerText = getParam('taskName');
    document.getElementById("taskDeadline").innerText = getParam('deadline');
    document.getElementById("taskStatus").innerText = getParam('status');

