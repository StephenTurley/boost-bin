(function(){
	function logId(){
		return window.location.hash.substring(1);
	}

	function  fetchDataLog(id){
		return $.get('/api/datalog/' + id);
	}

	function renderChart(data){
		var ctx = $('#datalog');
		var afLearning = data['AF-Learning-1-'];
		var throttle = data['Throttle-Pos-'];

		var datalog = new Chart(ctx, {
            type: 'line',
            data: {
            	labels: _.range(afLearning.length),
                datasets: [{
                	label: 'Throttle Pos',
                	data: throttle
                }]
            },
            options: {
                    scales: {
                        xAxes: [{
                            ticks: {
                            	max: 500,
                            	min: 0,
                                fixedStepSize: 10
                            }
                        }]
                    }
                }
        });
	}

	fetchDataLog(logId())
		.then(renderChart);
})()