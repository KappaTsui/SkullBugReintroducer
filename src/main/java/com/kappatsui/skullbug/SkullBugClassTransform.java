package com.kappatsui.skullbug;

import net.minecraft.launchwrapper.IClassTransformer;
import org.apache.commons.io.FileUtils;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.*;
import static org.objectweb.asm.Opcodes.*;

import java.io.File;
import java.io.IOException;

public class SkullBugClassTransform implements IClassTransformer {


	@Override
	public byte[] transform(String name, String transformedName, byte[] basicClass) {
		if (transformedName.equals("net.minecraft.item.ItemSkull")){
			boolean isObfuscated = !name.equals(transformedName);

			try{
				ClassNode cn = new ClassNode();
				ClassReader cr = new ClassReader(basicClass);
				cr.accept(cn, 0);

				for (MethodNode method : cn.methods){
					if (method.name.equals(isObfuscated ? "a" : "onItemUse") && method.desc.equals(isObfuscated? "(Ladd;Lyz;Lahb;IIIIFFF)Z" : "(Lnet/minecraft/item/ItemStack;Lnet/minecraft/entity/player/EntityPlayer;Lnet/minecraft/world/World;IIIIFFF)Z")){
						InsnList toInsert = new InsnList();

						toInsert.add(new VarInsnNode(ALOAD, 1));
						toInsert.add(new VarInsnNode(ALOAD, 2));
						toInsert.add(new VarInsnNode(ALOAD, 3));
						toInsert.add(new VarInsnNode(ILOAD, 4));
						toInsert.add(new VarInsnNode(ILOAD, 5));
						toInsert.add(new VarInsnNode(ILOAD, 6));
						toInsert.add(new VarInsnNode(ILOAD, 7));
						toInsert.add(new VarInsnNode(FLOAD, 8));
						toInsert.add(new VarInsnNode(FLOAD, 9));
						toInsert.add(new VarInsnNode(FLOAD, 10));
						toInsert.add(new MethodInsnNode(INVOKESTATIC, Type.getInternalName(Hook.class), "onItemUse", isObfuscated? "(Ladd;Lyz;Lahb;IIIIFFF)Z" : "(Lnet/minecraft/item/ItemStack;Lnet/minecraft/entity/player/EntityPlayer;Lnet/minecraft/world/World;IIIIFFF)Z", false));
						toInsert.add(new InsnNode(IRETURN));

						method.instructions.insertBefore(method.instructions.getFirst(), toInsert);
					}
				}


				ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
				cn.accept(cw);
				return cw.toByteArray();
			}catch (Exception e){
				e.printStackTrace();
			}
		}
		return basicClass;
	}
}
