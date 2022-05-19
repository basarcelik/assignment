package com.caocity.baraka.assignment;

import java.net.URI;

import javax.websocket.ClientEndpoint;
import javax.websocket.CloseReason;
import javax.websocket.ContainerProvider;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;


@ClientEndpoint
public class WebsocketClientEndpoint {

	private MessageHandler messageHandler;

	private boolean isWorking=false;
	private boolean isOpened=false;
	
	public MessageHandler getMessageHandler() {
		return messageHandler;
	}


	public boolean isWorking() {
		return isWorking;
	}


	public boolean isOpened() {
		return isOpened;
	}


	public WebsocketClientEndpoint(URI endpointURI) {
		try {
			WebSocketContainer container = ContainerProvider.getWebSocketContainer();
			container.connectToServer(this, endpointURI);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}


	@OnOpen
	public void onOpen(Session userSession) {
		isWorking=true;
		isOpened=true;
	}

	@OnClose
	public void onClose(Session userSession, CloseReason reason) {
		isWorking=false;
	}


	@OnMessage
	public void onMessage(String message) {
		if (this.messageHandler != null) {
			this.messageHandler.handleMessage(message);
		}
	}


	public void addMessageHandler(MessageHandler msgHandler) {
		this.messageHandler = msgHandler;
	}

	@OnError
	public void onError(Throwable cause) {
		isWorking=false;
		cause.printStackTrace();
	}
	
	public static interface MessageHandler {

		public void handleMessage(String message);
	}
}