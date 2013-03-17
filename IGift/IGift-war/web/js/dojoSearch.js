
function search(start) {
	var query = dojo.byId('query');
	var params = "q=" + query.value + "&start-index=" + 1;
	searchHelp(params);
}

function next(start) {
	var nextURL = dojo.byId('nextURL');
	searchHelp(nextURL.value);
}

function previous(start) {
	var previousURL = dojo.byId('previousURL');
	searchHelp(previousURL.value);
}

function searchHelp(params) {
	dojo.xhrGet( {
		url : 'SearchServlet?'+params,
		load : callback,
		error : callError
		
	});
}

function callError(data, ioArgs) {
	//problem:
	dojo.byId("search_results").innerHTML = "research failed";
}

function callback(data, ioArgs) {
	dojo.byId("search_results").innerHTML = data;
	//it works:   
	//div.innerHTML = req.responseText;

	var nextURL = dojo.byId('nextURL');
	var previousURL = dojo.byId('previousURL');

	var nextButton = dojo.byId('next');
	var previousButton = dojo.byId('previous');


	if (nextURL != null) {
		nextButton.style.display = "";
	} else {
		nextButton.style.display = "none";
	}

	if (previousURL != null) {
		previousButton.style.display = "";
	} else {
		previousButton.style.display = "none";
	}
}