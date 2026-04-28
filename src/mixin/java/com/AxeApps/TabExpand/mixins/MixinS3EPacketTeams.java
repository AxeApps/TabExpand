package com.AxeApps.TabExpand.mixins;

import java.io.IOException;

import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.server.S3EPacketTeams;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import com.AxeApps.TabExpand.Config;

@Mixin(S3EPacketTeams.class)
public class MixinS3EPacketTeams {

    @Redirect(
        method = "readPacketData",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/network/PacketBuffer;readStringFromBuffer(I)Ljava/lang/String;"))
    private String expandPrefixSuffix(PacketBuffer buf, int maxLen) throws IOException {
        if (!Config.enableExpansion) {
            return buf.readStringFromBuffer(maxLen);
        }

        if (maxLen == 16) {
            return buf.readStringFromBuffer(Config.prefixSuffixReadLimit);
        }

        return buf.readStringFromBuffer(maxLen);
    }

    @Redirect(
        method = "writePacketData",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/network/PacketBuffer;writeStringToBuffer(Ljava/lang/String;)V"))
    private void writeExpanded(PacketBuffer buf, String s) throws IOException {
        if (s == null) {
            buf.writeStringToBuffer("");
            return;
        }

        if (!Config.enableExpansion) {
            buf.writeStringToBuffer(s);
            return;
        }

        if (s.length() > 16) {
            if (s.length() > Config.prefixSuffixWriteLimit) {
                s = s.substring(0, Config.prefixSuffixWriteLimit);
            }
        }

        buf.writeStringToBuffer(s);
    }
}
