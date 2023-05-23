let username = "LTX";
let div = $('div');
let websocket = null;
let main = () => {
    if('WebSocket' in window) {
        websocket = new WebSocket('ws://localhost:2222/websocket');
    }else{
        alert("当前浏览器不支持");
    }
    WebSocket.onmessage = (e) => {
        let data = JSON.parse(e.data);
        console.log(data);
    }
    websocket.onopen = () => {
        console.log("success");
        div.html("连接成功");
    }
    websocket.onclose = (e) => {
        div.html(e.code + " " + e.reason);
    }
}

export{
    main,
}