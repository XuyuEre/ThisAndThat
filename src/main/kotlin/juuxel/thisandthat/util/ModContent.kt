/* This file is a part of the This & That project
 * by Juuxel, licensed under the MIT license.
 * Full code and license: https://github.com/Juuxel/ThisAndThat
 */
package juuxel.thisandthat.util

import net.minecraft.block.Block
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.item.Item
import net.shadowfacts.simplemultipart.multipart.Multipart

interface ModContent<out T> {
    val name: String

    @Suppress("UNCHECKED_CAST")
    fun unwrap(): T = this as T
}

interface BlockLikeContent<out T> : ModContent<T> {
    val registerItem: Boolean get() = true
    val hasDescription: Boolean get() = false
    val descriptionKey: String get() = "%TranslationKey.desc"
}

interface ModBlock : BlockLikeContent<Block> {
    val itemSettings: Item.Settings?
    val blockEntityType: BlockEntityType<*>? get() = null
}

interface ModMultipart : BlockLikeContent<Multipart>
