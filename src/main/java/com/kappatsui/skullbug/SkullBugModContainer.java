package com.kappatsui.skullbug;

import com.google.common.eventbus.EventBus;
import cpw.mods.fml.common.DummyModContainer;
import cpw.mods.fml.common.LoadController;
import cpw.mods.fml.common.ModMetadata;

import java.util.Arrays;

public class SkullBugModContainer extends DummyModContainer {

	public SkullBugModContainer()
	{
		super(new ModMetadata());
		ModMetadata meta = getMetadata();
		meta.modId = Tags.MODID;
		meta.name = Tags.MODNAME;
		meta.description = "This mod reintroduces skull placement bug.";
		meta.version = Tags.VERSION;
		meta.authorList = Arrays.asList("KappaTsui");
	}

	@Override
	public boolean registerBus(EventBus bus, LoadController controller)
	{
		bus.register(this);
		return true;
	}

}
