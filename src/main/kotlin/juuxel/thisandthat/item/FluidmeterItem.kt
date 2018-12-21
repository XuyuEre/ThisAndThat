/* This file is a part of the This & That project
 * by Juuxel, licensed under the MIT license.
 * Full code and license: https://github.com/Juuxel/ThisAndThat
 */
package juuxel.thisandthat.item

import juuxel.thisandthat.api.FluidContainer
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemStack
import net.minecraft.text.StringTextComponent
import net.minecraft.util.Hand
import net.minecraft.util.HitResult
import net.minecraft.util.TypedActionResult
import net.minecraft.util.registry.Registry
import net.minecraft.world.World

class FluidmeterItem : ModItem("fluidmeter", Item.Settings().itemGroup(ItemGroup.TOOLS)) {
    override fun use(world: World, player: PlayerEntity, hand: Hand): TypedActionResult<ItemStack> {
        val hitResult = getHitResult(world, player, false) ?: return super.use(world, player, hand)

        if (!world.isClient && hitResult.type == HitResult.Type.BLOCK) {
            val pos = hitResult.blockPos
            val state = world.getBlockState(pos)
            val block = state.block

            if (block is FluidContainer) {
                block.getContents(world, pos, state).forEach { (fluid, amount) ->
                    player.addChatMessage(StringTextComponent("${Registry.FLUID.getId(fluid)}: $amount"), false)
                }
            }
        }

        return super.use(world, player, hand)
    }
}
