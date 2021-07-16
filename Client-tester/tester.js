var ws;

function connect() {
	//connect to stomp where stomp endpoint is exposed
	var socket = new WebSocket("http://localhost:8001/register-socket");
	ws = Stomp.over(socket);

	ws.connect({}, function(frame) {
	    
        ws.subscribe("/test/subs", function(message) {
			console.log(message.body);
		});

	}, function(error) {
		alert("STOMP error " + error);
	});

    console.log('CONNECTED');
}

function disconnect() {
	if (ws != null) {
		ws.close();
	}
	setConnected(false);
	console.log("Disconnected");
}

function sendName() {

	ws.send("/app/test", {}, "TEST DATA");
}

connect();

setTimeout(()=>{
    sendName();
},5000);



