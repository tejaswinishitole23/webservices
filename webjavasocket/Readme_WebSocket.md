# WebSocket



## Before web socket

Techniques that were used before websocket were:

### Cross-frame communication

1. Load data into a frame
2. Copy data from the frame to the current page
3. Appears data loaded to the current page

### Polling

1. Periodic request to server
2. New connection for each request
3. Very inefficient

### Long polling

1. Holds open connection to server.
2. Only sends new data to client.
3. More efficient than simple polling
4. latency issues at high volumes.

### Ajax (Asynchronous JavaScript And XML)

1. Connects to server only when required 
2. Non-blocking on client
3. Data passed via callback method
4. Issue with handling large amounts of data

### Problems before WebSocket

​	Solutions before WebSocket had  same issues

- Resource intensive
- Use HTTP protocol
- Just workarounds
- Not Fully Duplex - means one end has to wait for other end to finish before it can act.

### WebSocket is

- Lightweight
- Bidirectional
- Fully duplexed communication

## WebSocket Usage

### Telemetry Data Streams

Data Views, Live Streams, Very Efficient

### Highly Responsive UI

Responsive, Asynchronous, Fluid UX

### Reactive APIs

Async Behavior, Responsive

### Others

Chat Apps, Online games, Push notification, Real-time dashboards

## WebSocket Life Cycle and Topology

- It is initiated by Client, never the server
- Once client connects, communication is bi-directional
- Session is created on the server. Can be in millions, billions, depending upon website.
- Either side can close the connection.

## Functionality

- Session models client/server interactions
- One session per client
- contains reference to RemoteEndPoint
- contains message send capabilities
- Message handlers for incoming and outgoing messages
- Text, Binary and pong
- One message handler per type
- Multiple end points permissible
- Relative URIs
- Web Container security model
- Builds on servlet security
- HTTP authentication - basic or form based

## Annotations

- Session stored for later use

- Annotation based end points

- lifecycle - OnOpen, OnMesage, OnClose, onError

- Message encoder/decoder

  - formats - JSON, binary, pong

  - ```java
    @OnMesage
    public void onMessage(Message message, Session session){}
    @OnMesage
    public void onBinaryMessage(ByteBuffer message, Session session){}
    @OnMesage
    public void onPongMessage(Pong message, Session session){}
    ```

  - 