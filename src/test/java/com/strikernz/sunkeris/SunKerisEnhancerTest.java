package com.strikernz.sunkeris;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class SunKerisEnhancerTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(com.strikernz.sunkeris.SunKerisEnhancerPlugin.class);
		RuneLite.main(args);
	}
}