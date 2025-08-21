function getParam(){
        return new URLSearchParams(window.location.search).get(name);

    }
    document.getElementById("taskSerialNo").innerText = set;
    document.getElementById("taskName").innerText = getParam('name');
    document.getElementById("taskDeadline").innerText = getParam('deadline');
    document.getElementById("taskStatus").innerText = getParam('status');