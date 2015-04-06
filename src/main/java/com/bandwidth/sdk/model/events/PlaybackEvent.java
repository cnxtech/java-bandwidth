package com.bandwidth.sdk.model.events;

import org.json.simple.JSONObject;

public class PlaybackEvent extends EventBase {

	public PlaybackEvent(final JSONObject json) {
		super(json);
		// TODO Auto-generated constructor stub
	}

	public void execute(final Visitor visitor) {
		visitor.processEvent(this);
	}

}
