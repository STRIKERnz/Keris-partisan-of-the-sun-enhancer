package com.strikernz.sunkeris;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;


@ConfigGroup("sunkeris")
public interface SunKerisEnhancerConfig extends Config
{

	@ConfigItem(
			keyName = "messages",
			name = "Sun Keris messages",
			description = "Comma-separated list of messages shown when using Sun Keris special"
	)
	default String messages()
	{
		return "Praise the Sun!, WOOOOO!, I NEED HEALING!";
	}

	@ConfigItem(
			keyName = "sendToChat",
			name = "Send message to chat",
			description = "Also send the message to the chat box"
	)
	default boolean sendToChat()
	{
		return true;
	}
}