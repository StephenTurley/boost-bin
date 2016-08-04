(function(){
	function logId(){
		return window.location.hash.substring(1);
	}

	function  fetchDataLog(id){
		return $.get('/api/datalog/' + id);
	}

	function dataAsArray(data){
	    var foo = [];
	    _.mapObject(data, function(val, key){
	        foo.push({
	            name: key,
	            data: _.map(val, function(num){ return Number(num); })
	        });
	    });

	    return foo;
	}

	function renderChart(data){
		$('#container').highcharts({
                title: {
                    text: 'Data Log',
                    x: -20 //center
                },
                xAxis: {

                },
                yAxis: {
                    title: {
                        text: 'The data'
                    }
                },
                legend: {
                    layout: 'vertical',
                    align: 'right',
                    verticalAlign: 'middle',
                    borderWidth: 0
                },
                series: dataAsArray(data)
            });
	}

	fetchDataLog(logId())
		.then(renderChart);
})()