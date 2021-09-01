google.charts.load('current', {'packages':['sankey']});
//        google.charts.setOnLoadCallback(drawChart);

async function drawChart() {

    var inputTextField = document.getElementById('codefield');
    var serviceURL = "http://localhost:8080/prerequisites?dept=" + inputTextField.value;
    console.log(serviceURL);

    let request = new XMLHttpRequest();
    var jsondata;
    request.open("GET", serviceURL); // parameterize using contents of form field
    request.send();
    request.onload = () => {
        if (request.status == 200){
            jsondata = JSON.parse(request.response);
            var data = new google.visualization.DataTable();
            data.addColumn('string', 'From');
            data.addColumn('string', 'To');
            data.addColumn('number', 'Weight');

            console.log("Reading data");
            console.log(jsondata);
            for (let i = 0; i < jsondata.adjacencyList.length; i++){
                row = [jsondata.adjacencyList[i].from,jsondata.adjacencyList[i].to,parseInt(jsondata.adjacencyList[i].weight)];
                data.addRow(row);
            }
            // Sets chart options.
            var options = {
                width: 900,
                sankey: { node: { nodePadding: 80 } },
            };

            // Instantiates and draws our chart, passing in some options.
            var chart = new google.visualization.Sankey(document.getElementById('sankey_basic'));
            chart.draw(data, options);

        } else {
            console.log("Error");
        }
    }
}


function selectionChange(){
    var inputTextField = document.getElementById('codefield');
    var inputDropdown = document.getElementById('codemenu');

    inputTextField.value = inputDropdown.options[inputDropdown.selectedIndex].value;
}