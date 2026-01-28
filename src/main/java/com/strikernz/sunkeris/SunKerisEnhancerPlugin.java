package com.strikernz.sunkeris;


import com.google.inject.Provides;
import javax.inject.Inject;
import java.util.Random;

import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.api.Player;
import net.runelite.api.events.AnimationChanged;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

import java.util.Objects;

@PluginDescriptor(
		name = "Sun Keris Enhancer"
)
public class SunKerisEnhancerPlugin extends Plugin
{

	private static final int SUN_KERIS_SPECIAL_ANIMATION_ID = 9546;

	private static final int OVERHEAD_CYCLES = 100;

	@Inject
	private Client client;

	@Inject
	private SunKerisEnhancerConfig config;

	private final Random random = new Random();

	@Subscribe
	public void onAnimationChanged(AnimationChanged event)
	{
		if (!(event.getActor() instanceof Player))
		{
			return;
		}

		Player player = (Player) event.getActor();

		if (player.getAnimation() != SUN_KERIS_SPECIAL_ANIMATION_ID)
		{
			return;
		}

		String messagesConfig = config.messages();
		if (messagesConfig == null || messagesConfig.trim().isEmpty())
		{
			return;
		}

		// Split by comma and pick one at random
		String[] messages = messagesConfig.split("\\s*,\\s*"); // trim whitespace
		String message = messages[random.nextInt(messages.length)];

		// Overhead text
		player.setOverheadText(message);
		player.setOverheadCycle(OVERHEAD_CYCLES);

		// Optional chat message
		if (config.sendToChat())
		{
			client.addChatMessage(
					ChatMessageType.PUBLICCHAT,
                    Objects.requireNonNull(player.getName()),
					message,
					null
			);
		}
	}


	@Provides
	SunKerisEnhancerConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(SunKerisEnhancerConfig.class);
	}
}
