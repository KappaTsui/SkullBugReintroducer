package com.kappatsui.skullbug;

import com.mojang.authlib.GameProfile;
import net.minecraft.block.BlockSkull;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntitySkull;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import java.util.UUID;

public class Hook {
	public static boolean onItemUse(ItemStack p_77648_1_, EntityPlayer p_77648_2_, World p_77648_3_, int p_77648_4_, int p_77648_5_, int p_77648_6_, int p_77648_7_, float p_77648_8_, float p_77648_9_, float p_77648_10_)
	{
		if (p_77648_7_ == 0)
		{
			return false;
		}
		else if (!p_77648_3_.getBlock(p_77648_4_, p_77648_5_, p_77648_6_).getMaterial().isSolid())
		{
			return false;
		}
		else
		{
			if (p_77648_7_ == 1)
			{
				++p_77648_5_;
			}

			if (p_77648_7_ == 2)
			{
				--p_77648_6_;
			}

			if (p_77648_7_ == 3)
			{
				++p_77648_6_;
			}

			if (p_77648_7_ == 4)
			{
				--p_77648_4_;
			}

			if (p_77648_7_ == 5)
			{
				++p_77648_4_;
			}

			if (!p_77648_3_.isRemote)
			{
				p_77648_3_.setBlock(p_77648_4_, p_77648_5_, p_77648_6_, Blocks.skull, p_77648_7_, 2);
				int var11 = 0;

				if (p_77648_7_ == 1)
				{
					var11 = MathHelper.floor_double((double)(p_77648_2_.rotationYaw * 16.0F / 360.0F) + 0.5D) & 15;
				}

				TileEntity var12 = p_77648_3_.getTileEntity(p_77648_4_, p_77648_5_, p_77648_6_);

				if (var12 != null && var12 instanceof TileEntitySkull)
				{
					if (p_77648_1_.getItemDamage() == 3)
					{
						GameProfile var13 = null;

						if (p_77648_1_.hasTagCompound())
						{
							NBTTagCompound var14 = p_77648_1_.getTagCompound();

							if (var14.hasKey("SkullOwner", 10))
							{
								var13 = NBTUtil.func_152459_a(var14.getCompoundTag("SkullOwner"));
							}
							else if (var14.hasKey("SkullOwner", 8) && var14.getString("SkullOwner").length() > 0)
							{
								var13 = new GameProfile((UUID)null, var14.getString("SkullOwner"));
							}
						}

						((TileEntitySkull)var12).func_152106_a(var13);
					}
					else
					{
						((TileEntitySkull)var12).func_152107_a(p_77648_1_.getItemDamage());
					}

					((TileEntitySkull)var12).func_145903_a(var11);
					((BlockSkull)Blocks.skull).func_149965_a(p_77648_3_, p_77648_4_, p_77648_5_, p_77648_6_, (TileEntitySkull)var12);
				}

				--p_77648_1_.stackSize;
			}

			return true;
		}
	}

}
